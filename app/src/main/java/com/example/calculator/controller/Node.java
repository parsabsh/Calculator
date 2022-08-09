package com.example.calculator.controller;

public class Node {
    private boolean isOperator;
    private char operator;
    private Double number;

    public Node(char operator) {
        this.operator = operator;
        this.isOperator = true;
    }

    public Node(double number) {
        this.number = number;
        this.isOperator = false;
    }

    public boolean isOperator() {
        return isOperator;
    }

    public char getOperator() {
        return operator;
    }

    public Double getNumber() {
        return number;
    }

}
