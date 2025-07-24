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
                    Department savedDepartment = departmentRepository.save(department);
                    return  convertToDTO(savedDepartment);
                }))
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    // Mappers
    private DepartmentDTO convertToDTO(Department department){
        return new DepartmentDTO(
                department.getId(),
                department.getName()
        );
    }

    private Department convertToEntity(DepartmentDTO departmentDTO){
        Department department = new Department();
        department.setId(departmentDTO.id());
        department.setName(departmentDTO.name());
        return department;
    }

}
