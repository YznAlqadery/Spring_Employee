package com.example.employee_directory.service;

import com.example.employee_directory.model.Department;
import com.example.employee_directory.model.dto.DepartmentDTO;
import com.example.employee_directory.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> getDepartments(){
        return departmentRepository.findAll()
                .stream().map(this::convertToDTO)
                .toList();
    }

    public DepartmentDTO getDepartmentById(Long id){
        return departmentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Department not found."));
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO){
        Department department = convertToEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }

    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }

    public DepartmentDTO updateDepartment(Long id, DepartmentDTO updatedDepartment){
        return departmentRepository.findById(id)
                .map((department -> {
                    department.setName(updatedDepartment.name());
                    department.setEmployees(updatedDepartment.employees());
                    Department savedDepartment = departmentRepository.save(department);
                    return  convertToDTO(savedDepartment);
                }))
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    // Mappers
    private DepartmentDTO convertToDTO(Department department){
        return new DepartmentDTO(
                department.getName(),
                department.getEmployees()
        );
    }

    private Department convertToEntity(DepartmentDTO departmentDTO){
        Department department = new Department();
        department.setName(departmentDTO.name());
        department.setEmployees(departmentDTO.employees());
        return department;
    }
}
