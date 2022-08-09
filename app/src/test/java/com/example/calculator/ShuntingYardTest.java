package com.example.calculator;

import com.example.calculator.controller.ShuntingYardCalculator;

import org.junit.Assert;
import org.junit.Test;

public class ShuntingYardTest {
    private final ShuntingYardCalculator calculator = new ShuntingYardCalculator();

    @Test
    public void simpleAddition() {
        String answer = calculator.shuntingYard("5 + 6");
        Assert.assertEquals("11.0", answer);
    }

    @Test
    public void simpleSubtraction() {
        String answer = calculator.shuntingYard("5.2 - 6.2");
        Assert.assertEquals("-1.0", answer);
    }

    @Test
    public void simpleMultiplication() {
        String answer = calculator.shuntingYard("5 × 6");
        Assert.assertEquals("30.0", answer);
    }

    @Test
    public void bug() {
        String answer = calculator.shuntingYard("1 × 1 - 1");
        Assert.assertEquals("0.0", answer);
    }

}
