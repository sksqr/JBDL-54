package com.example.L11UnitTestdemo.controller;


import com.example.L11UnitTestdemo.dto.EmployeeDetailDTO;
import com.example.L11UnitTestdemo.entity.Employee;
import com.example.L11UnitTestdemo.repo.IEmployeeRepo;
import com.example.L11UnitTestdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private IEmployeeRepo employeeRepo;

    @Autowired
    private EmployeeService employeeService;

//    @PostMapping
//    public ResponseEntity<Employee> createEmp(@RequestBody Employee employee){
//        employeeRepo.save(employee);
//        return ResponseEntity.ok(employee);
//    }

    @PostMapping
    public ResponseEntity<Employee> createEmp(@RequestBody EmployeeDetailDTO employeeDetailDTO){
        return ResponseEntity.ok(employeeService.createEmployee(employeeDetailDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmp(@PathVariable Long id){
        Employee employee  = employeeRepo.findById(id).get();
        return ResponseEntity.ok(employee);
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getEmp(@RequestParam String name){
        return ResponseEntity.ok(employeeRepo.findByName(name));
    }

}
