package com.example.employee_directory.service;

import com.example.employee_directory.model.Department;
import com.example.employee_directory.model.Employee;
import com.example.employee_directory.model.dto.EmployeeDTO;
import com.example.employee_directory.repository.DepartmentRepository;
import com.example.employee_directory.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    // Constructor injection
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository){
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    // Get all employees
    public List<EmployeeDTO> getEmployees(){
        return employeeRepository.findAll(Sort.by(Sort.Direction.DESC,"id"))
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
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setEmail(updatedEmployee.email());
                    employee.setName(updatedEmployee.name());

                    if (updatedEmployee.departmentId() != null) {
                        Department department = departmentRepository.findById(updatedEmployee.departmentId())
                                .orElseThrow(() -> new RuntimeException("Department not found"));
                        employee.setDepartment(department);
                    } else {
                        employee.setDepartment(null);
                    }

                    employee.setInternalNotes(updatedEmployee.internalNotes());
                    employee.setSalary(updatedEmployee.salary());
                    employee.setUpdatedAt(new Date());

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
                employee.getEmail(),
                employee.getDepartment() != null ? employee.getDepartment().getId() : null,
                employee.getDepartment().getName(),
                employee.getInternalNotes(),
                employee.getSalary()
        );
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setName(employeeDTO.name());
        employee.setEmail(employeeDTO.email());

        if (employeeDTO.departmentId() != null) {
            Department department = departmentRepository.findById(employeeDTO.departmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }

        employee.setInternalNotes(employeeDTO.internalNotes());
        employee.setSalary(employeeDTO.salary());

        // Set timestamps
        Date now = new Date();
        employee.setCreatedAt(now);
        employee.setUpdatedAt(now);

        return employee;

    }

}
