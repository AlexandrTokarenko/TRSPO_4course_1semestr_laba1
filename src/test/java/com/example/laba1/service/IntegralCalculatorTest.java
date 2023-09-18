package com.example.laba1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegralCalculatorTest {

    private IntegralCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new IntegralCalculator();
    }

    @Test
    public void calculateTest() {

        double a = 1;
        double b = 9;
        int n = 1000;

        double expected = 171.9;
        double result = calculator.calculate(a,b,n);

        assertEquals(expected,result,1e-1);
    }
}