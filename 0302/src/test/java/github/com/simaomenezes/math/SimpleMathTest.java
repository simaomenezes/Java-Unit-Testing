package github.com.simaomenezes.math;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath class")
public class SimpleMathTest {

    SimpleMath simpleMath;

    @BeforeAll
    static void setup(){
        System.out.println("Running @BeforeAll method!!!");
    }

    @AfterAll
    static void cleanup(){
        System.out.println("Running @AfterAll method!!!");
    }

    @BeforeEach
    void beforeEachMethod(){
        simpleMath = new SimpleMath();
        System.out.println("Running @BeforeEach method!!!");
    }

    @AfterEach
    void afterEachMethod(){
        System.out.println("Running @AfterEach method!!!");
    }


    // test[System Under Test]_[Condition or State Change]_[Expected Result]

    @Test
    @DisplayName("Test 6.2 + 2 = 8.2")
    void testSun_When_SixPointTwoPlusTwoEqualsEight(){
        System.out.println("Test 6.2 + 2 = 8.2!");
        // AAA Arrange, Act, Assert
        // Given / Arrange

        double firstNumber = 6.2d;
        double secondNumber = 2d;
        double expected = 8.2d;

        // When / Act
        Double actual = simpleMath.sum(firstNumber, secondNumber);

        // Then / Assert
        assertEquals(expected, actual,
                () -> firstNumber + " + " +
                        secondNumber + " did not produce " +
                        expected + " !");

    }
    @Test
    void testSun(){

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


        double firstNumber = 10d;
        double secondNumber = 5d;
        Double result = simpleMath.division(firstNumber, secondNumber);

        double expected = 2d;
        assertEquals(expected, result, () -> "The testDivision method should return: " + expected);
    }

    @Test
    void testSubtraction(){


        double firstNumber = 10d;
        double secondNumber = 5d;
        Double result = simpleMath.subtraction(firstNumber, secondNumber);

        double expected = 5d;
        assertEquals(expected, result, () -> "The testSubtraction method should return: " + expected);
    }

    @Test
    void testMultiplication(){


        double firstNumber = 10d;
        double secondNumber = 5d;
        Double result = simpleMath.multiplication(firstNumber, secondNumber);

        double expected = 50d;
        assertEquals(expected, result, () -> "The testMultiplication method should return: " + expected);
    }

    @Test
    void testMean(){


        double firstNumber = 5d;
        double secondNumber = 5d;
        Double result = simpleMath.mean(firstNumber, secondNumber);

        double expected = 5d;
        assertEquals(expected, result, () -> "The testMean method should return: " + expected);
    }

    @Test
    @DisplayName("Test Square Root of 81 = 9")
    void testSquareRoot(){

        System.out.println("Test Square Root of 81 = 9");

        double number = 81d;
        double expected = 9d;

        Double result = simpleMath.squareRoot(number);

        assertEquals(expected, result, () -> "The testSquareRoot of " + number + " did not produce " + expected + "!");
    }
}
