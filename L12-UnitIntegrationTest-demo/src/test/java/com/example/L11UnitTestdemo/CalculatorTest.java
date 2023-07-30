package com.example.L11UnitTestdemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
    }


    @Test
    public void testMultiply(){
        Integer a = 3;
        Integer b = 5;
        Integer expected = 15;
        assertThat(calculator.multiply(a,b)).isEqualTo(expected);
    }


    @Test
    public void testAdd(){
        Integer a = 3;
        Integer b = 5;
        Integer expected = 8;
        assertThat(calculator.add(a,b)).isEqualTo(expected);
    }

}
