package com.example.app.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String fullName;
    private final String email;
    private LocalDate dob;
    private Integer age;

    public User(UUID id, String fullName, String email, LocalDate dob) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dob = dob;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
