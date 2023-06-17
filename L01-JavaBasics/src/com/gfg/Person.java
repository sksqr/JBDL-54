package com.gfg;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void walk(){
        System.out.println(name+" is walking");
    }


    public void walk(String arg1){
        System.out.println(name+" is walking on "+arg1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
