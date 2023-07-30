package com.example.L11UnitTestdemo.controller;

import com.example.L11UnitTestdemo.dto.EmployeeDetailDTO;
import com.example.L11UnitTestdemo.entity.Address;
import com.example.L11UnitTestdemo.entity.Employee;
import com.example.L11UnitTestdemo.repo.IEmployeeRepo;
import com.example.L11UnitTestdemo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    private EmployeeDetailDTO employeeDetailDTO;
    private Employee employee;

    @BeforeEach
    public void setUp(){
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
    void tearDown() {
    }

    @Test
    void testCreateEmp() throws Exception {
        when(employeeService.createEmployee(any())).thenReturn(employee);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(employeeDetailDTO);
        mockMvc.perform(post("/emp")
                .contentType("application/json")
                .content(jsonData))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetEmp() throws Exception {
        when(employeeService.getEmployeeById(1l)).thenReturn(employee);
        mockMvc.perform(get("/emp/"+1))
                .andDo(print()).andExpect(status().isOk());
    }
}