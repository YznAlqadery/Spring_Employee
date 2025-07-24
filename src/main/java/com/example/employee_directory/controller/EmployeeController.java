package com.example.employee_directory.controller;


import com.example.employee_directory.model.Department;
import com.example.employee_directory.model.Employee;
import com.example.employee_directory.model.dto.EmployeeDTO;
import com.example.employee_directory.service.DepartmentService;
import com.example.employee_directory.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController // --> Tells Spring to handle HTTP requests
//@RequestMapping("/api/employees") // --> All the URLs will start with /api/employees
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    // Constructor Injection
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService){
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public String getEmployees(Model model){
        List<EmployeeDTO> employees  = employeeService.getEmployees();
        model.addAttribute("employees",employees);
        return "employees/index";
    }

//    @PostMapping("")
//    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employee){
//        EmployeeDTO createdEmployee = employeeService.createEmployee(employee);
//        return ResponseEntity.status(201).body(createdEmployee);
//    }


    @GetMapping("/create")
    public String createEmployee(Model model){
       model.addAttribute("employeeDTO", new EmployeeDTO(0L,"","",0L,"","",0.0));
       model.addAttribute("departments",departmentService.getDepartments());
       return "employees/create";
    }

    @PostMapping("/create")
    public String createEmployee(
            @Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()){
            return "employee/create";
        }

        employeeService.createEmployee(employeeDTO);

        return "redirect:/employees";

    }

    @GetMapping("/edit")
    public String editEmployee(Model model, @RequestParam Long id){
        EmployeeDTO employeeDTO  = employeeService.getEmployeeById(id);

        model.addAttribute("employeeDTO",employeeDTO);
        model.addAttribute("departments",departmentService.getDepartments());
        return "employees/edit";
    }

    @PostMapping("/edit")
    public String editEmployee(
            @RequestParam Long id,
            @Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            return "employees/edit";
        }

        employeeService.updateEmployee(id,employeeDTO);

        return "redirect:/employees";

    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
//        employeeService.deleteEmployee(id);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployee){
        EmployeeDTO updated = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(updated);
    }


}
