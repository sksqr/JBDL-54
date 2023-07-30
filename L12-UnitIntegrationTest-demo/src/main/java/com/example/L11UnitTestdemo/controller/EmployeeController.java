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


    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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
        Employee employee  = employeeService.getEmployeeById(id);
        if(employee == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<Employee> getEmpByEmail(@RequestParam String email){
        Employee employee  = employeeService.getEmployeeByEmail(email);
        if(employee == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }



}
