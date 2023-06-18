package com.gfg;

public abstract class TataCar implements IndianGovtNoms {

    private Engine engine;

    public final String getCompany(){
        return "Tata Motors";
    }

    public String startEngine(){
        return "";
    }

    public abstract String getRC();
}
