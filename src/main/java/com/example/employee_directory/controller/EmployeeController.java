package com.example.employee_directory.controller;


import com.example.employee_directory.model.dto.EmployeeDTO;
import com.example.employee_directory.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // --> Tells Spring to handle HTTP requests
@RequestMapping("/api/employees") // --> All the URLs will start with /employees
public class EmployeeController {

    private final EmployeeService employeeService;

    // Constructor Injection
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<EmployeeDTO> getEmployees(){
        return employeeService.getEmployees();
    }

    @PostMapping("")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee){
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployee){
       return employeeService.updateEmployee(id,updatedEmployee);
    }
}
