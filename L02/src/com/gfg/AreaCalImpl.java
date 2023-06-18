package com.gfg;

public class AreaCalImpl implements AreaCalculator{
    @Override
    public double circleArea(double r) {
//        pi = 22/7;
        return pi*r*r;
    }

    @Override
    public double sphereSurfaceArea(double r) {
        return 0;
    }
}
