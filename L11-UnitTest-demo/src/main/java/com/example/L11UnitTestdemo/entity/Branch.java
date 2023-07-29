package com.example.L11UnitTestdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private  String renewalNumber;

    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    private Set<Employee> employeeSet;


}
