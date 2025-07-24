package com.example.employee_directory.controller;


import com.example.employee_directory.model.dto.EmployeeDTO;
import com.example.employee_directory.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // --> Tells Spring to handle HTTP requests
@RequestMapping("/api/employees") // --> All the URLs will start with /api/employees
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor Injection
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(){
        List<EmployeeDTO> employees  = employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employee){
        EmployeeDTO createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(201).body(createdEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployee){
        EmployeeDTO updated = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(updated);
    }
}
