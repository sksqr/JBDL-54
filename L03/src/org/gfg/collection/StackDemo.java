package org.gfg.collection;

import java.util.Stack;

public class StackDemo {

    public static void main(String[] args) {
//LIFO
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(1);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        printValue(10);
    }

    public static void printValue(Integer n){
        if(n>5){
            System.out.println(n);
            printValue(n-1);
        }
    }
// (a+b))
}
