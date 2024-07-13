package com.example.demo.repo; //package com.example.demo.repo;

import com.example.demo.models.Person;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PersonRepo extends CrudRepository<Person, Long> {
    List<Person> findByName(String name);
    List<Person> findBySurname(String surname); // 1 argument
    List<Person> findByFatherName(String fatherName);

    List<Person> findBySurnameAndName(String surname, String name);
    List<Person>findBySurnameAndFatherName(String surname, String fatherName); // 2 arguments
    List<Person> findByNameAndFatherName(String name, String fatherName);

    List<Person> findBySurnameAndNameAndFatherName(String surname, String name, String fatherName); // 3 arguments
}
