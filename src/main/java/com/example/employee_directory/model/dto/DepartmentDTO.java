package com.example.employee_directory.model.dto;

import com.example.employee_directory.model.Employee;

import java.util.List;

public record DepartmentDTO(String name, List<Employee> employees) {
}
