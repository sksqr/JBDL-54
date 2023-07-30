package com.example.L11UnitTestdemo.repo;

import com.example.L11UnitTestdemo.entity.Address;
import com.example.L11UnitTestdemo.entity.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

@DataJpaTest
class IEmployeeRepoTest {

    @Autowired
    private IEmployeeRepo employeeRepo;
    private Employee employee;

    @BeforeEach
    void setUp() {
        Address address = Address.builder()
                .line1("A-33")
                .line2("Sector-3")
                .city("Noida")
                .build();
        employee = Employee.builder()
                .email("emp@gmail.com")
                .name("Employee")
                .address(address)
                .build();
        employeeRepo.save(employee);
    }

    @AfterEach
    void tearDown() {
        employeeRepo.deleteAll();
    }

    @Test
    void testFindByNameSuccess() {
        List<Employee> actualList = employeeRepo.findByName("Employee");
        assertThat(actualList.size()).isEqualTo(1);
        Employee actualEmp = actualList.get(0);
        assertThat(actualEmp).isEqualTo(employee);

    }

    @Test
    void testFindByNameFailure() {
        List<Employee> actualList = employeeRepo.findByName("Shashi");
        assertThat(actualList.size()).isEqualTo(0);
    }
}