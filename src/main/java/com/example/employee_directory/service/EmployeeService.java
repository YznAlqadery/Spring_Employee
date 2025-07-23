package com.example.employee_directory.service;

import com.example.employee_directory.model.Employee;
import com.example.employee_directory.model.dto.EmployeeDTO;
import com.example.employee_directory.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    // Get employee by id
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found."));
    }

    // Create employee
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    // Delete employee
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    // Update employee
    public Employee updateEmployee(Long id, Employee updatedEmployee){
       return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setEmail(updatedEmployee.getEmail());
                    employee.setName(updatedEmployee.getName());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found."));
    }

    // Mappers
    private EmployeeDTO convertToDTO(Employee employee){
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getEmail()
        );
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.name());
        employee.setEmail(employeeDTO.email());
        employee.setDepartment(employeeDTO.department());
        return employee;
    }

}
