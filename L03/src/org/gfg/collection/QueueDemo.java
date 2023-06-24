package org.gfg.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {

    public static void main(String[] args) {

        Queue<String> students = new LinkedList<>();

        students.add("Ravi");
        students.add("ajay");
        students.add("parul");

        System.out.println(students.add("Rakesh"));
        System.out.println(students.peek());
        System.out.println(students.peek());
        System.out.println(students.poll());
        System.out.println(students.poll());

    }
}
