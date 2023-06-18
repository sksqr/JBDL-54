package com.gfg.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayDemo {

    public static void main(String[] args) {

        String students[] = new String[2];
        students[0] = "Rakesh";
        students[1] = "Ravi";
        String temp[] = new String[3];

        for(int i=0; i<students.length; i++){
            temp[i] = students[i];
        }
        temp[2] = "Ram";
        students = temp;
        for (String s : students){
            System.out.println(s);
        }


        List<String> studentsList = new ArrayList<>();
        studentsList.add("Shashi");
        studentsList.add("Neeraj");
        studentsList.add("Dheeraj");
        Collections.sort(studentsList);
        System.out.println(studentsList);
    }
}
