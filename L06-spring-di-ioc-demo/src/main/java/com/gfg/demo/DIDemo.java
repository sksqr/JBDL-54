package com.gfg.demo;

public class DIDemo {
    public static void main(String[] args) {

        Car car1 = new Car();
        car1.runCar();
        System.out.println(car1);

        Engine engine = new Engine("Mega Power",2200);
        //DI
        Car car2 = new Car("Hexa",engine);
        car2.runCar();
        System.out.println(car2);

    }
}
