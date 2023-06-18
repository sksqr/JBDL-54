package com.gfg;

public enum Day {
    SUNDAY("SUN",1), MONDAY("MON",2), TUESDAY("TUE",3),
    WEDNESDAY("WED",4),
    THURSDAY("THU",5), FRIDAY("FRI",6), SATURDAY("SAT",7);

    private String value;

    private int dayOfWeek;

    Day(String value, int dayOfWeek) {
        this.value = value;
        this.dayOfWeek = dayOfWeek;
    }

    public String getValue() {
        return value;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    //    Day(String value) {
//        this.value = value;
//    }
}
