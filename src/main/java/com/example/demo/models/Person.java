package com.example.demo.models; //package com.example.demo.models;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String surname;
    private String name;
    @Nullable
    private String fatherName;
    private String birthday;
    private String phoneNumber;
    private String email;
    private String role;
    private String department;


    public Person() {
    }

    public Person(String surname, String name,
                  @Nullable String fatherName,
                  String birthday,
                  String phoneNumber,
                  String email, String role, String department) {
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(@Nullable String fatherName) {
        this.fatherName = fatherName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}