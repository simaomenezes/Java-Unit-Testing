package github.com.simaomenezes.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleMathTest {

    @Test
    void testSun(){
        SimpleMath simpleMath = new SimpleMath();
        double firstNumber = 1d;
        double secondNumber = 2d;
        Double result = simpleMath.sum(firstNumber, secondNumber);
        Double expected = 3d;
        assertEquals(expected, result, ()-> "The testSun method should return :" + expected);
        assertNotEquals(4d, result, ()->  "The testSun method should not return 4");
        assertNotNull(result, ()-> "The testSun method should not return null");
    }

    @Test
    void testDivision(){
        SimpleMath simpleMath = new SimpleMath();

        double firstNumber = 10d;
        double secondNumber = 5d;
        Double result = simpleMath.division(firstNumber, secondNumber);

        double expected = 2d;
        assertEquals(expected, result, () -> "The testDivision method should return: " + expected);
    }

    @Test
    void testSubtraction(){
        SimpleMath simpleMath = new SimpleMath();

        double firstNumber = 10d;
        double secondNumber = 5d;
        Double result = simpleMath.subtraction(firstNumber, secondNumber);

        double expected = 5d;
        assertEquals(expected, result, () -> "The testSubtraction method should return: " + expected);
    }

    @Test
    void testMultiplication(){
        SimpleMath simpleMath = new SimpleMath();

        double firstNumber = 10d;
        double secondNumber = 5d;
        Double result = simpleMath.multiplication(firstNumber, secondNumber);

        double expected = 50d;
        assertEquals(expected, result, () -> "The testMultiplication method should return: " + expected);
    }

    @Test
    void testMean(){
        SimpleMath simpleMath = new SimpleMath();

        double firstNumber = 5d;
        double secondNumber = 5d;
        Double result = simpleMath.mean(firstNumber, secondNumber);

        double expected = 5d;
        assertEquals(expected, result, () -> "The testMean method should return: " + expected);
    }

    @Test
    void testSquareRoot(){
        SimpleMath simpleMath = new SimpleMath();

        double anyNumber = 45d;
        Double result = simpleMath.squareRoot(anyNumber);

        double expected = 6.708203932499369d;
        assertEquals(expected, result, () -> "The testSquareRoot method should return: " + expected);
    }
}
