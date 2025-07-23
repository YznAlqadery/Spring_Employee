package com.example.employee_directory.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // --> Tells Spring to handle HTTP requests
@RequestMapping("/employees") // --> All the URLs will start with /employees
public class EmployeeController {
}
