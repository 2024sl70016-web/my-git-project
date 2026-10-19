package com.example;

public class Calculator {

    // Simple method to add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Method to handle division, including handling division by zero
    public double divide(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Cannot divide by zero!");
        }
        return (double) numerator / denominator;
    }
}

