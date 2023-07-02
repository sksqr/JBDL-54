package com.gfg.lombok;

public class LombokDemo {

    public static void main(String[] args) {
        Product p = new Product();
        p.setName("laptop");

        Person person = new Person();
        person.setName("Rahul");

        Person person2 = new Person("Ravi",27);

        System.out.println(person2);

    }
}
