package com.example.L11UnitTestdemo;

import java.util.Date;

public class ImmutableDemo {

    public static void main(String[] args) {
        String name = "Rahul";
        Date doj = new Date();
        ImmutableEmployee employee = new ImmutableEmployee(1l,name, doj);
        System.out.println(employee);
        name = "Ravi";
        doj.setTime(32428947588l);
        employee.getDoj().setTime(3445566545l);
        System.out.println(employee);
    }
}
