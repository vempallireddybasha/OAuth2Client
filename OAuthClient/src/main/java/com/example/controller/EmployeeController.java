package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//   EmployeeController (BCryptPasswordEncoder passwordEncoder){
//        this.bCryptPasswordEncoder=passwordEncoder;
//    }

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        employee.setAuthorities("ROLE_USER");
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
       Employee e= employeeService.saveEmployee(employee);
       return   ResponseEntity.ok().body(e);
    }
    @GetMapping("/get")
    public String hello(Principal principal){
        return "Welcome to OAuth2  Course "+principal.getName()+" Great Loking forward to have you with Us..";
    }
}
