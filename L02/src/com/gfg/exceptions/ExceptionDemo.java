package com.gfg.exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionDemo {

    public static void main(String[] args) // throws FileNotFoundException
    {
        System.out.println("Starting program");

        String s1 = "Shashi";

        System.out.println(s1.length());

        System.out.println(s1.charAt(0));

//        System.out.println(s1.charAt(10)); //unchecked

//        try {
//            readDataFromFile();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        readDataFromFile();

        System.out.println("Done");
    }

    public static void readDataFromFile()  {
//        try {
//            FileReader fileReader = new FileReader("/tmp/file1.txt"); // checked exception
//            int a = fileReader.read();
//            System.out.println("Try Completed");
//        } catch (FileNotFoundException e) {
//            System.out.println("FileNotFoundException");
////            throw new RuntimeException();
////            throw new FileNotFoundException();
////            throw new NullPointerException();
//        } catch (IOException e) {
//            System.out.println("IOException");
//        }

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("/tmp/file1.txt"); // checked exception
            int a = fileReader.read();
            System.out.println(a);
            System.out.println("Try Completed");
        }
        catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        }
        catch (Exception e) {
            System.out.println("IOException");
        }
        finally {
            System.out.println("Executing finally");
//            try {
//                fileReader.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }

        System.out.println("Completed reading");

    }
}
