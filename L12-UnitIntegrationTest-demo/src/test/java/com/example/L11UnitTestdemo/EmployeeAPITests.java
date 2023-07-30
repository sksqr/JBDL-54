package com.example.L11UnitTestdemo;

import com.example.L11UnitTestdemo.dto.EmployeeDetailDTO;
import com.example.L11UnitTestdemo.entity.Address;
import com.example.L11UnitTestdemo.entity.Employee;
import com.example.L11UnitTestdemo.repo.IEmployeeRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource(
        locations = ("classpath:application-it.properties")
)
@AutoConfigureMockMvc
public class EmployeeAPITests {


    @Autowired
    private MockMvc mockMvc;

    private EmployeeDetailDTO employeeDetailDTO;
    private Employee employee;

    @Autowired
    private IEmployeeRepo employeeRepo;

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
    public void tearDown(){
//        employeeRepo.deleteAll();
    }


    @Test
    public void testCreateEmpAPI() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Integer initialSize = employeeRepo.findAll().size();
        String jsonData = objectMapper.writeValueAsString(employeeDetailDTO);
        mockMvc.perform(post("/emp")
                        .contentType("application/json")
                        .content(jsonData))
                .andDo(print()).andExpect(status().isOk());

        Integer finalSize = employeeRepo.findAll().size();
        assertThat(finalSize-initialSize).isEqualTo(1);
    }

    @Test
    void testGetEmpByIdAPISuccess() throws Exception {
        employeeRepo.save(employee);
        mockMvc.perform(get("/emp/"+employee.getId()))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetEmpByIdAPIFailure() throws Exception {
        mockMvc.perform(get("/emp/"+10))
                .andDo(print()).andExpect(status().isNotFound());
    }


    @Test
    void testGetEmpByEmailAPISuccess() throws Exception {
        employeeRepo.save(employee);
        mockMvc.perform(get("/emp?email=emp@gmail.com"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetEmpByEmailAPIFailure() throws Exception {
        mockMvc.perform(get("/emp?email=emp1@gmail.com"))
                .andDo(print()).andExpect(status().isNotFound());
    }


}
