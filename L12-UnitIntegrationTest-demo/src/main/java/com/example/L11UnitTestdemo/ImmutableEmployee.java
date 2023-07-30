package com.example.L11UnitTestdemo;

import java.util.Date;


public final class ImmutableEmployee {
    private final Long id;
    private final String name;
    private final Date doj;

    public ImmutableEmployee(Long id, String name, Date doj) {
        this.id = id;
        this.name = name;
        this.doj = new Date(doj.getTime());
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Date getDoj() {
        return new Date(doj.getTime());
    }

    @Override
    public String toString() {
        return "ImmutableEmployee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", doj=" + doj +
                '}';
    }
}
