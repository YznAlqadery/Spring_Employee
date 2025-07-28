    package com.example.employee_directory.controller;

    import com.example.employee_directory.model.dto.DepartmentDTO;
    import com.example.employee_directory.service.DepartmentService;
    import jakarta.validation.Valid;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

//    @RestController
//    @RequestMapping("/api/departments")
    @RequestMapping("/departments")
    @Controller
    public class DepartmentController {

        private final DepartmentService departmentService;

        public DepartmentController(DepartmentService departmentService) {
            this.departmentService = departmentService;
        }


//        @GetMapping("")
//        public ResponseEntity<List<DepartmentDTO>> getDepartments(){
//            return ResponseEntity.ok(departmentService.getDepartments());
//        }


        @GetMapping("")
        public String getDepartments(Model model){
            List<DepartmentDTO> departments = departmentService.getDepartments();

            model.addAttribute("departments",departments);
            return "departments/index";
        }

        @GetMapping("/details")
        public String getDepartmentById(Model model, @RequestParam Long id){
            DepartmentDTO department = departmentService.getDepartmentById(id);
            model.addAttribute("department",department);
            return "departments/details";
        }

        @GetMapping("/create")
        public String createEmployee(Model model){
            model.addAttribute("departmentDTO",new DepartmentDTO(0L,"",List.of()));
            return "departments/create";
        }

        @PostMapping("/create")
        public String createEmployee(@Valid @ModelAttribute("departmentDTO") DepartmentDTO departmentDTO,
                                     BindingResult bindingResult,
                                     Model model){
            if(bindingResult.hasErrors()){
                return "departments/create";
            }
            departmentService.createDepartment(departmentDTO);
            return "redirect:/departments";
        }

        @GetMapping("/edit")
        public String editDepartment(Model model, @RequestParam Long id){
            DepartmentDTO department = departmentService.getDepartmentById(id);
            model.addAttribute("department",department);
            return "departments/edit";
        }

        @PostMapping("/edit")
        public String editDepartment(@Valid @ModelAttribute("department") DepartmentDTO departmentDTO,
                                     @RequestParam Long id,
                                     BindingResult bindingResult,
                                     Model model){
            if (bindingResult.hasErrors()){
                return "departments/edit";
            }
            departmentService.updateDepartment(id,departmentDTO);
            return "redirect:/departments";
        }

        @GetMapping("/delete")
        public String deleteDepartment(@RequestParam Long id){
            departmentService.deleteDepartment(id);
            return "redirect:/departments";
        }

//        @GetMapping("/{id}")
//        public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id){
//            return ResponseEntity.ok(departmentService.getDepartmentById(id));
//        }
//
//        @PostMapping("")
//        public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO){
//            DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
//            return ResponseEntity.status(201).body(createdDepartment);
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
//            departmentService.deleteDepartment(id);
//            return ResponseEntity.noContent().build();
//        }
//
//
//        @PutMapping("/{id}")
//        public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO){
//            return ResponseEntity.ok(departmentService.updateDepartment(id,departmentDTO));
//        }

    }
