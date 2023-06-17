package com.gfg;

public class Teacher extends Person{

    private String subject;
    public Teacher(String name) {
        super(name);
    }

    @Override
    public void walk() {
        System.out.println("Teacher "+getName()+" is walking");
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int parentHashCode(){
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "subject='" + subject + '\'' +
                "name='" + super.getName() + '\'' +
                '}';
    }
}
