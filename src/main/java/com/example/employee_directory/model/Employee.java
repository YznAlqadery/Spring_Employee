package com.example.employee_directory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity // --> Tells Spring Data JPA to create a table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // --> Automatically generates primary keys
    private Long id;
    private String name;
    private String department;
    private String email;

    // Required by JPA and Spring to instantiate the object when fetching from the DB
    public Employee(){

    }

    // Helpful for creating new employees easily in code
    public Employee(String name, String department, String email) {
        this.name = name;
        this.department = department;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
