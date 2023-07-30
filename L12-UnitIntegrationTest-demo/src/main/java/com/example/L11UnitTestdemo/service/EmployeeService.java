package com.example.L11UnitTestdemo.service;

import com.example.L11UnitTestdemo.dto.EmployeeDetailDTO;
import com.example.L11UnitTestdemo.entity.Address;
import com.example.L11UnitTestdemo.entity.Employee;
import com.example.L11UnitTestdemo.repo.IEmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    private IEmployeeRepo employeeRepo;

    public EmployeeService(IEmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Transactional
    public Employee createEmployee(EmployeeDetailDTO employeeDetailDTO){
        Employee employee = new Employee();
        employee.setName(employeeDetailDTO.getName());
        employee.setEmail(employeeDetailDTO.getEmail());
        Address address = new Address();
        address.setLine1(employeeDetailDTO.getLine1());
        address.setLine2(employeeDetailDTO.getLine2());
        address.setCity(employeeDetailDTO.getCity());
        employee.setAddress(address);
        employee = employeeRepo.save(employee);
        LOGGER.info("Saved employee");
        return employee;
    }

    public Employee getEmployeeById(Long id){
        return employeeRepo.findById(id).orElse(null);
    }


    public Employee getEmployeeByEmail(String  email){
        return employeeRepo.findByEmail(email);
    }
}
