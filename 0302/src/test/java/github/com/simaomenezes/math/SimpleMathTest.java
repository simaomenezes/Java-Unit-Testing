package github.com.simaomenezes.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMathTest {

    @Test
    void testSun(){
        SimpleMath simpleMath = new SimpleMath();
        Double result = simpleMath.sum(1d, 2d);
        Double expected = 3d;
        assertEquals(expected, result);
    }
}
