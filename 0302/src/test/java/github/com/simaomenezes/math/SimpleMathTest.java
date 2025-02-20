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
}
