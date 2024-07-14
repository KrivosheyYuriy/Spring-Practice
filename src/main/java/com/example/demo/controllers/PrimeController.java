package com.example.demo.controllers;

import com.example.demo.controllers.java.Counter;
import com.example.demo.controllers.java.DataProcessor;
import com.example.demo.models.Person;
import com.example.demo.repo.PersonRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PrimeController {
    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/") // URL-address, main-page
    public String greeting(Model model) {
        Iterable<Person> persons = personRepo.findAll();
        model.addAttribute("persons", persons);
        model.addAttribute("title", "Главная страница"); // param title=attributeValue
        model.addAttribute("counter", new Counter());
        return "home"; // HTML filename
    }

    @GetMapping("/addPerson")
    public String addUser(Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // формат сегодняшней даты
        model.addAttribute("today", formatter.format(new Date()));
        return "addPerson";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/person/{id}")
    public String getPerson(@PathVariable Long id, Model model) {
        Person person = personRepo.findById(id).orElse(null);
        if (person == null) {
            model.addAttribute("title", "Человек не найден");
        } else {
            model.addAttribute("title", "Человек найден");
            model.addAttribute("persons", new Person[]{person});
        }
        // передаем данные для страницы
        model.addAttribute("counter", new Counter());
        return "home";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String FIO, Model model) { // для поиска по ФИО
        List<Person> persons = (List<Person>) personRepo.findAll(); // по-умолчанию выдаем всех
        if (FIO != null && !FIO.isEmpty()) { // если что-то введено
            String[] data = FIO.split(" ");
            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].substring(0, 1).toUpperCase() + data[i].substring(1).toLowerCase();
                // на всякий случай делаем обработку на регистр
            }
            if (data.length == 1) {
                persons = personRepo.findBySurname(data[0]);
                persons.addAll(personRepo.findByName(data[0])); // если один аргумент (любой)
                persons.addAll(personRepo.findByFatherName(data[0]));
            } else if (data.length == 2) {
                persons = personRepo.findBySurnameAndName(data[0], data[1]);
                persons.addAll(personRepo.findBySurnameAndFatherName(data[0], data[1])); // если два любых аргумента
                persons.addAll(personRepo.findByNameAndFatherName(data[0], data[1]));
            } else if (data.length == 3) {
                persons = personRepo.findBySurnameAndNameAndFatherName(data[0],
                        data[1], data[2]); // если 3 аргумента ФИО
            }
        }

        model.addAttribute("title", "Отсортированная страница");
        model.addAttribute("persons", persons); // передаем данные для страницы
        model.addAttribute("counter", new Counter());
        return "home";
    }

    @PostMapping("/addPerson")
    public String addPostPerson(@RequestParam String surname, @RequestParam String name,
                                @RequestParam String fatherName, @RequestParam String birthday,
                                @RequestParam String phone, @RequestParam String email,
                                @RequestParam String role, @RequestParam String department) {

        String proSurname = DataProcessor.capitalize(surname, "-");
        String proName = DataProcessor.capitalize(name, " ");// обработка корректности регистра
        String proFatherName = DataProcessor.capitalize(fatherName, " ");

        Person person = new Person(proSurname, proName, proFatherName, birthday, phone, email, role, department);
        personRepo.save(person);
        return "redirect:/";
    }

    @DeleteMapping("/person/{id}")
    @ResponseBody
    public void deletePerson(@PathVariable("id") Long id) {
        personRepo.deleteById(id);
    }

    @PutMapping("/person")
    @ResponseBody
    public void updatePersonData(@RequestBody Person person) {
        if (personRepo.findById(person.getId()).isPresent()) {
            Person original = personRepo.findById(person.getId()).get();
            if (!person.getSurname().isEmpty()) {
                original.setSurname(person.getSurname());
            }
            if (!person.getName().isEmpty()) {
                original.setName(person.getName());
            }
            original.setFatherName(person.getFatherName());
            if (!person.getBirthday().isEmpty()) {
                original.setBirthday(person.getBirthday());
            }
            if (!person.getPhoneNumber().isEmpty()) {
                original.setPhoneNumber(person.getPhoneNumber());
            }
            if (!person.getEmail().isEmpty()) {
                original.setEmail(person.getEmail());
            }
            if (!person.getRole().isEmpty()) {
                original.setRole(person.getRole());
            }
            if (!person.getDepartment().isEmpty()) {
                original.setDepartment(person.getDepartment());
            }
            personRepo.save(original);
        }
    }
}
