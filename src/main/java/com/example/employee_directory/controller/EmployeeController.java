package com.example.employee_directory.controller;


import com.example.employee_directory.model.Employee;
import com.example.employee_directory.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // --> Tells Spring to handle HTTP requests
@RequestMapping("/api/employees") // --> All the URLs will start with /employees
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    // Constructor Injection
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("")
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found."));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee){
        employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setEmail(updatedEmployee.getEmail());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found."));
    }

}
