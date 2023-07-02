package com.gfg.demo;

public class Engine {
    private String name;
    private Integer cc;
    public Engine() {
        System.out.println("Creating Engine ");
    }
    public Engine(String name, Integer cc) {
        this.name = name;
        this.cc = cc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                ", cc=" + cc +
                '}';
    }

    public void engineDestroy()
    {
        System.out.println("Destroying "+name);
    }

    public void engineInit()
    {
        System.out.println("Initializing "+name);
    }

}
