package com.example.employee_directory.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity // --> Tells Spring Data JPA to create a table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // --> Automatically generates primary keys
    private Long id;
    private String name;
    private String department;
    private String email;

    private String internalNotes;
    private Double salary;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // Required by JPA and Spring to instantiate the object when fetching from the DB
    public Employee(){

    }

    // Helpful for creating new employees easily in code
    public Employee(String name, String department, String email,
                    String internalNotes, Double salary,
                    Date createdAt, Date updatedAt) {
        this.name = name;
        this.department = department;
        this.email = email;
        this.internalNotes = internalNotes;
        this.salary = salary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    public String getInternalNotes() {
        return internalNotes;
    }
    public void setInternalNotes(String internalNotes) {
        this.internalNotes = internalNotes;
    }

    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;

    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
