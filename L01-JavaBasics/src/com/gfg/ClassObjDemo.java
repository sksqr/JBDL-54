package com.gfg;

public class ClassObjDemo {
    public static void main(String[] args) {

        int number =1;

        Lecture lec1 = new Lecture("L01", "Shashi", "Live");
        System.out.println(lec1);

        Lecture lec2 = new Lecture("L02", "Shashi", "Upcoming");
        System.out.println(lec2);

        Lecture lec3 = new Lecture();
        lec3.setMentor("shashi");
        lec3.setTitle("L03");
        lec3.setStatus("Upcoming");
        System.out.println(lec3);

        System.out.println(Lecture.num);
//        System.out.println(Lecture.title);
        System.out.println(lec1.title);



    }
}
