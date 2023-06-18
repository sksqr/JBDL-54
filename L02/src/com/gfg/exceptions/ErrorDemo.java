package com.gfg.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDemo {

    public static void main(String[] args) {
        printData("Data");
    }

    //StackOverFlow
    public static void printData(String data){
        printData(data);
    }


    public static void heapOutOfMemoryDemo(){
        List<Date> list = new ArrayList<>();
        while (true){
            list.add(new Date());
        }
    }
}
