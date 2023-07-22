package com.example.L09SpringJPAdemo.singletondemo;

public enum Day {
    SUNDAY("SUN"),MONDAY("MON");
    private String value;
     Day(String value) {
         this.value = value;
    }

    public String getValue(){
         return value;
    }

}
