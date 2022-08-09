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
    public void simpleCombinationOfMultiplicationAndAddition() {
        String answer = calculator.shuntingYard("1 × 1 - 1");
        Assert.assertEquals("0.0", answer);
    }

    @Test
    public void complexCalculation() {
        String answer = calculator.shuntingYard("(-1.3 + 2) × 4 ÷ 1.4");
        Assert.assertEquals("2.0", answer);
    }
}
