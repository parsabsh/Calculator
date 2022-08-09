package com.example.calculator.controller;

import java.util.Stack;

public class ShuntingYardCalculator {

    public String shuntingYard(String expression) {

        Stack<Node> operator_stack = new Stack<>();
        Stack<Node> output_queue = new Stack<>();

        int i = 0;
        // this loop has written by shunting yard algorithm
        while (i < expression.length()) {
            if (expression.charAt(i) == ' ') {
                i++;
                continue;
            }

            // if it is a number, put it into the output queue
            if (Character.isDigit(expression.charAt(i)) || (expression.charAt(i) == '-' && Character.isDigit(expression.charAt(i + 1)))) {
                int startIndex = i;
                if (expression.charAt(i) == '-') {
                    i++;
                }
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    i++;
                }
                output_queue.push(new Node(Double.parseDouble(expression.substring(startIndex, i))));
                continue;
            }

            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                // if the operator stack is empty, put the operator in it
                if (operator_stack.isEmpty()) {
                    operator_stack.push(new Node(expression.charAt(i)));
                } else {
                    // operators with higher or equal precedence
                    while (!operator_stack.isEmpty() && operator_stack.peek().getOperator() != '(') {
                        // pop from the operator stack and put it into the output queue
                        output_queue.push(new Node(operator_stack.pop().getOperator()));
                    }
                    // put the new operator in the operator stack
                    operator_stack.push(new Node(expression.charAt(i)));
                }
                i++;
                continue;
            }

            if (expression.charAt(i) == '×' || expression.charAt(i) == '÷') {
                // if the operator stack is empty, put the operator in it
                if (operator_stack.isEmpty()) {
                    operator_stack.push(new Node(expression.charAt(i)));
                } else {
                    // operators with higher or equal precedence
                    while (!operator_stack.isEmpty() && (operator_stack.peek().getOperator() == '×' || operator_stack.peek().getOperator() == '÷')) {
                        // pop from the operator stack and put it into the output queue
                        output_queue.push(new Node(operator_stack.pop().getOperator()));
                    }
                    // put the new operator in the operator stack
                    operator_stack.push(new Node(expression.charAt(i)));
                }
                i++;
                continue;
            }

            if (expression.charAt(i) == '(') {
                operator_stack.push(new Node(expression.charAt(i)));
                i++;
                continue;
            }

            if (expression.charAt(i) == ')') {
                while (operator_stack.peek().getOperator() != '(') {
                    // pop from the operator stack and put it into the output queue
                    output_queue.push(new Node(operator_stack.pop().getOperator()));
                }
                operator_stack.pop();
                i++;
            }
        }

        // pop out the rest of the operators in the stack and put them into the output queue
        while (!operator_stack.isEmpty()) {
            // pop from the operator stack
            // pop from the operator stack and put it into the output queue
            output_queue.push(new Node(operator_stack.pop().getOperator()));
        }

        // ------------------------------------------------------------------------------------------------------------
        // this section we evaluate the answer using our operator Stack and output Queue (there is no parentheses here)
        for (int j = 0; j < output_queue.size(); j++) {
            if (!output_queue.get(j).isOperator()) { // if it is a number store it in the stack
                operator_stack.push(new Node(output_queue.get(j).getNumber()));
            } else { // apply the operation between the last two numbers in the stack and store put the result back to the stack
                double a = operator_stack.pop().getNumber();
                double b = operator_stack.pop().getNumber();
                operator_stack.push(new Node(apply_operation(output_queue.get(j).getOperator(), b, a))); //! TODO
            }
        }
        return String.valueOf(operator_stack.pop().getNumber());
    }

    private Double apply_operation(char operator, Double a, Double b) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '×':
                return a * b;
            case '÷':
                return a / b;
        }
        return 0d;
    }
}
