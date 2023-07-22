package com.example.L09SpringJPAdemo.service;

import com.example.L09SpringJPAdemo.CardNotCreateException;
import com.example.L09SpringJPAdemo.LaptopAllocationException;
import com.example.L09SpringJPAdemo.aop.LogExecutionTime;
import com.example.L09SpringJPAdemo.dto.EmployeeDetailDTO;
import com.example.L09SpringJPAdemo.entity.Address;
import com.example.L09SpringJPAdemo.entity.Employee;
import com.example.L09SpringJPAdemo.repo.IEmployeeRepo;
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

    private String randomData = null;

    private boolean cardCreated = true;


    private boolean laptopCreated = false;

    @Transactional(rollbackFor = {CardNotCreateException.class}, noRollbackFor = {LaptopAllocationException.class})
    public Employee createEmployee(EmployeeDetailDTO employeeDetailDTO) throws CardNotCreateException, LaptopAllocationException {
        Employee employee = new Employee();
        Address address = new Address();
        employee.setAddress(address);
        employeeRepo.save(employee);
        employee.setName(employeeDetailDTO.getName());
        employee.setEmail(employeeDetailDTO.getEmail());
        address.setLine1(employeeDetailDTO.getLine1());
        address.setLine2(employeeDetailDTO.getLine2());
        address.setCity(employeeDetailDTO.getCity());
        LOGGER.info("Saved employee");
//        LOGGER.info("randomData size {}",randomData.length());
        if(!cardCreated){
            throw new CardNotCreateException();
        }
        if(!laptopCreated){
            throw new LaptopAllocationException();
        }
        return employee;
    }

    @LogExecutionTime
    public Employee getEmployeeById(Long id){
        employeeRepo.findById(id).get();
        employeeRepo.findById(id).get();
        return employeeRepo.findById(id).get();
    }
}
