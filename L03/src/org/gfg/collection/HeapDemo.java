package org.gfg.collection;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class HeapDemo {

    public static void main(String[] args) {
//        Queue<Integer> queue = new PriorityQueue<>();
//        queue.add(100);
//        queue.add(150);
//        queue.add(70);
//        queue.add(50);
//        System.out.println(queue.peek());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());


//        Queue<Student> students = new PriorityQueue<>();
//        students.add(new Student("3", "Rakesh", 99));
//        students.add(new Student("4", "Rahul", 89));
//        students.add(new Student("2", "Rani", 91));
//        students.add(new Student("1", "Ravi", 95));
//
//        while (!students.isEmpty()){
//            System.out.println(students.poll());
//        }

        Queue<Student> students = new PriorityQueue<>(((Comparator<Student>) (student1, student2) -> student1.getTotalMarks() - student2.getTotalMarks()).reversed());
        students.add(new Student("3", "Rakesh", 99));
        students.add(new Student("4", "Rahul", 89));
        students.add(new Student("2", "Rani", 91));
        students.add(new Student("1", "Ravi", 95));
        students.add(new Student("1", "Ravi", 95));

        while (!students.isEmpty()){
            System.out.println(students.poll());
        }
    }
}
