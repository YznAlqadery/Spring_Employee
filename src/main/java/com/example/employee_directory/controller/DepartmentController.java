    package com.example.employee_directory.controller;

    import com.example.employee_directory.model.dto.DepartmentDTO;
    import com.example.employee_directory.service.DepartmentService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/departments")
    public class DepartmentController {

        private final DepartmentService departmentService;

        public DepartmentController(DepartmentService departmentService) {
            this.departmentService = departmentService;
        }


        @GetMapping("")
        public ResponseEntity<List<DepartmentDTO>> getDepartments(){
            return ResponseEntity.ok(departmentService.getDepartments());
        }

        @GetMapping("/{id}")
        public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id){
            return ResponseEntity.ok(departmentService.getDepartmentById(id));
        }

        @PostMapping("")
        public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO){
            DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
            return ResponseEntity.status(201).body(createdDepartment);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
            departmentService.deleteDepartment(id);
            return ResponseEntity.noContent().build();
        }


        @PutMapping("/{id}")
        public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO){
            return ResponseEntity.ok(departmentService.updateDepartment(id,departmentDTO));
        }

    }
