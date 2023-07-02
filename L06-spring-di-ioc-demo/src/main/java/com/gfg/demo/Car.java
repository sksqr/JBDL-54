package com.gfg.demo;

public class Car {
    private String name;
    private Engine engine;

    public Car() {
        // tight coupling
        name = "Tata Tiago";
        engine = new Engine("Revetron",1200);
    }

    public Car(String name, Engine engine) {
        this.name = name;
        this.engine = engine;
    }

    public void runCar(){
        System.out.println(name+" Car is running with engine "+engine.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", engine=" + engine +
                '}';
    }
}
