package Graphic;

import java.awt.*;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

// A class that represents a mathematical function
class Function {
    // The expression of the function in postfix notation
    List<String> expression;
    // The color of the function on the graph
    Color color;

    // Constructor
    Function(List<String> expression, Color color) {
        this.expression = expression;
        this.color = color;
    }

    // Evaluate the function at a given x value
    double evaluate(double x) {
// Use a stack to evaluate the postfix expression
        Stack<Double> stack = new Stack<>();
        for (String token : expression) {
            if (isNumber(token)) { // if the token is a number, push it to the stack
                stack.push(Double.parseDouble(token));
            } else if (isVariable(token)) { // if the token is a variable, push the x value to the stack
                stack.push(x);
            } else { // if the token is an operator, pop two operands and apply the operator
                double b = stack.pop();
                double a = stack.pop();
                double result = applyOperator(token, a, b);
                stack.push(result);
            }
        }
        return stack.pop(); // return the final result from the stack
    }

    // Check if a token is a number
    boolean isNumber(String token) {
        try {
            Double.parseDouble(token); // try to parse the token as a double
            return true; // if successful, return true
        } catch (NumberFormatException e) {
            return false; // if not, return false
        }
    }

    // Check if a token is a variable
    boolean isVariable(String token) {
        return token.equals("x"); // return true if the token is "x"
    }

    // Apply an operator to two operands and return the result
    double applyOperator(String operator, double a, double b) {
        switch (operator) {
            case "+": // addition
                return a + b;
            case "-": // subtraction
                return a - b;
            case "*": // multiplication
                return a * b;
            case "/": // division
                return a / b;
            case "^": // exponentiation
                return Math.pow(a, b);
            default: // invalid operator
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}

// A class that represents a graph panel


// A class that represents the main frame of the application





