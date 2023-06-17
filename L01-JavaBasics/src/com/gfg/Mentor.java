package com.gfg;

public class Mentor extends Teacher{


    public Mentor(String name) {
        super(name);
    }

    public String getMentorName(){
        return super.getName();
    }
}
