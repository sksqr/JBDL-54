package com.example.L08SpringJPAdemo.service;

import com.example.L08SpringJPAdemo.dto.EmployeeDetailDTO;
import com.example.L08SpringJPAdemo.entity.Address;
import com.example.L08SpringJPAdemo.entity.Employee;
import com.example.L08SpringJPAdemo.repo.IEmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private IEmployeeRepo employeeRepo;

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
        employeeRepo.save(employee);
        LOGGER.info("Saved employee");
        return employee;
    }
}
