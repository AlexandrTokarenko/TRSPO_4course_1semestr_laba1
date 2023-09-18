package com.example.laba1.fun;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionTest {


    @Test
    void calc() {

        double expected = 0;

        double result = Function.calc(0);

        assertEquals(expected, result);
    }
}