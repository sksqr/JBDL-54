package com.example.L09SpringJPAdemo.controller;


import com.example.L09SpringJPAdemo.CardNotCreateException;
import com.example.L09SpringJPAdemo.LaptopAllocationException;
import com.example.L09SpringJPAdemo.dto.EmployeeDetailDTO;
import com.example.L09SpringJPAdemo.entity.Employee;
import com.example.L09SpringJPAdemo.repo.IEmployeeRepo;
import com.example.L09SpringJPAdemo.service.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
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

    @PersistenceContext
    private EntityManager entityManager;

//    @PostMapping
//    public ResponseEntity<Employee> createEmp(@RequestBody Employee employee){
//        employeeRepo.save(employee);
//        return ResponseEntity.ok(employee);
//    }

    @PostMapping
    public ResponseEntity<Employee> createEmp(@RequestBody EmployeeDetailDTO employeeDetailDTO) throws CardNotCreateException, LaptopAllocationException {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDetailDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmp(@PathVariable Long id){
        Employee employee  = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getEmp(@RequestParam String name){
        return ResponseEntity.ok(employeeRepo.findByName(name));
    }


    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllNames(){
        Query query = entityManager.createNativeQuery("Select name from employee");
        List<String> list = query.getResultList();
        return ResponseEntity.ok(list);
    }

}
