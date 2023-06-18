package com.gfg;

public class EnumDemo {

    public static void main(String[] args) {
        Day day = Day.FRIDAY;

        System.out.println(day.getValue());
        System.out.println(day.getDayOfWeek());

        Lecture lecture = new Lecture("L02","shashi", LectureStatus.LIVE);



    }
}
