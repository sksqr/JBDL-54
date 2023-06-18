package com.gfg;

public interface IndianGovtNoms {

     String govt = "INDIA";


    String getPUC();

    default String getLocation(){
        return "Not Available";
    }
}
