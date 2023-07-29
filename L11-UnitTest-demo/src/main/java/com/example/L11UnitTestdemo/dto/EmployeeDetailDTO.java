package com.example.L11UnitTestdemo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDTO {

    private String email;

    private String name;

    private String line1;

    private  String line2;

    private String city;
}
