package com.example.L08SpringJPAdemo.repo;

import com.example.L08SpringJPAdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);
}
