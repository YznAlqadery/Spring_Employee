package com.example.employee_directory.model.dto;



import java.util.List;

public record DepartmentDTO(Long id,String name, List<EmployeeDTO> employees) {
}
