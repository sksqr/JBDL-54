package com.example.L11UnitTestdemo.service;

import com.example.L11UnitTestdemo.dto.EmployeeDetailDTO;
import com.example.L11UnitTestdemo.entity.Address;
import com.example.L11UnitTestdemo.entity.Employee;
import com.example.L11UnitTestdemo.repo.IEmployeeRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    private AutoCloseable autoCloseable;
    @Mock
    private IEmployeeRepo employeeRepo;

    private EmployeeService employeeService;
    private EmployeeDetailDTO employeeDetailDTO;
    private Employee employee;

    @BeforeEach
    public void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeService(employeeRepo);
        Address address = Address.builder()
                .id(1l)
                .line1("A-33")
                .line2("Sector-3")
                .city("Noida")
                .build();
        employee = Employee.builder()
                .id(1l)
                .email("emp@gmail.com")
                .name("Employee")
                .address(address)
                .build();

        employeeDetailDTO = EmployeeDetailDTO.builder()
                .email("emp@gmail.com")
                .name("Employee")
                .line1("A-33")
                .line2("Sector-3")
                .city("Noida")
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testCreateEmployee(){
        when(employeeRepo.save(any())).thenReturn(employee);
        Employee actual = employeeService.createEmployee(employeeDetailDTO);
        assertThat(actual).isEqualTo(employee);

    }





}
