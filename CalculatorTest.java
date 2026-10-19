package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAddPositiveNumbers() {
        // Arrange & Act
        int result = calculator.add(2, 3);
        
        // Assert
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    void testAddNegativeNumbers() {
        int result = calculator.add(-2, -8);
        assertEquals(-10, result, "-2 + -8 should equal -10");
    }

    @Test
    void testValidDivision() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result);
    }

    @Test
    void testDivisionByZeroThrowsException() {
        // Verifies that an IllegalArgumentException is thrown when dividing by zero
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        });
    }
}

