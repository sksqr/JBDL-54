package com.example.L08SpringJPAdemo.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String email;
    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne
    @JsonIgnoreProperties("employeeSet")
    private Branch branch;

}
