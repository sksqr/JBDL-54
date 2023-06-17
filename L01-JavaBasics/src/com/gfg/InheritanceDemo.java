package com.gfg;

public class InheritanceDemo {

    public static void main(String[] args) {
        Teacher t1 = new Teacher("Ravi");
        t1.walk();

        System.out.println("t1 :"+t1.hashCode());
        System.out.println("t1 parent :"+t1.parentHashCode());

        Mentor m1 = new Mentor("Shashi");
        m1.walk();

        TataCar car = new TataCar();

        Person p1 = new Teacher("Rakesh");
        System.out.println("p1 instanceof Teacher:"+(p1 instanceof Teacher));
        p1.walk();

        Teacher t2 = (Teacher) p1;
        t2.parentHashCode();

        Person p2 = new Person("Rahul");
        System.out.println("p2 instanceof Teacher:"+(p2 instanceof Teacher));
        Teacher t3 = (Teacher) p2;
        t3.walk();
        t3.parentHashCode();

    }

}
