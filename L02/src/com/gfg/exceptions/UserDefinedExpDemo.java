package com.gfg.exceptions;

public class UserDefinedExpDemo {

    public static void main(String[] args) {

        System.out.println("Starting...");
        try {
            System.out.println(searchProduct("Mobile"));
        } catch (ProductNotFoundException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
        System.out.println("Done");
    }


    public static String searchProduct(String keyword) throws ProductNotFoundException {
        if(!keyword.equals("laptop")){
            throw new ProductNotFoundException(keyword+" Does not exist");
        }
        return "Exist";
    }
}
