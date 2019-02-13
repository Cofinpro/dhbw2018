package application.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResultTest {

    private Result one;
    private Result two;
    private Result three;
    private Result four;

    @Before
    public void setUp() {
        one = new Result("Simon", 3L);
        two = new Result("Simon", 3L);
        three = new Result("Simon", 4L);
        four = new Result("Elias", 4L);
    }

    @Test
    public void compareTo() {
        assertTrue(two.compareTo(three) < 0);
        assertTrue(four.compareTo(three) < 0);
        assertTrue(three.compareTo(four) > 0);
    }

    @Test
    public void equals() {
        assertEquals(one, two);
        assertNotEquals(one, three);
        assertNotEquals(three, four);
    }
}