package com.gfg;

import java.util.Scanner;

public class PolymorphismDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        Person t1 = null;
        if(input.equals("T")){
            t1 = new Teacher("Ravi");
        }
        else {
            t1 = new Person("Shashi");
        }
        t1.walk("Road");
        t1.walk();
    }
}
