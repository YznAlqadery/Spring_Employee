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
    public List<EmployeeDTO> getEmployees(){
        return employeeRepository.findAll()
                .stream().map(this::convertToDTO)
                .toList();
    }

    // Get employee by id
    public EmployeeDTO getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Employee not found."));
    }

    // Create employee
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    // Delete employee
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    // Update employee
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee){
       return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setEmail(updatedEmployee.email());
                    employee.setName(updatedEmployee.name());
                    employee.setDepartment(updatedEmployee.department());
                    Employee savedEmployee = employeeRepository.save(employee);
                    return convertToDTO(savedEmployee);
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
