package com.example.L11UnitTestdemo.repo;

import com.example.L11UnitTestdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);
    Employee findByEmail(String email);
}
