import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AdderTest {

    @Test
    void add() {
        int a = -6;
        int b = -6;
        int expected = -12;
        int actual = Adder.add(a, b);
        assertEquals(expected, actual);

        a = -6;
        b = 13;
        expected = 7;
        actual = Adder.add(a, b);
        assertEquals(expected, actual);
    }

    @Test
    void addEncapsulated() {
        Integer a = new Integer(5);
        Integer b = new Integer(-6);
        int expected = -1;
        int actual = Adder.add(a, b);
        assertEquals(expected, actual);

        a = new Integer(13);
        b = new Integer(10);
        expected = 23;
        actual = Adder.add(a, b);
        assertEquals(expected, actual);

        a = new Integer(13);
        b = null;
        expected = 13;
        actual = Adder.add(a, b);
        assertEquals(expected, actual);

        a = null;
        b = null;
        expected = 0;
        actual = Adder.add(a, b);
        assertEquals(expected, actual);
    }

    @Test
    void addRecursive() {
        int a = 10;
        int b = 6;
        int expected = 16;
        int actual = Adder.addRecursive(a, b);
        assertEquals(expected, actual);

        a = 10;
        b = -6;
        expected = 4;
        actual = Adder.addRecursive(a, b);
        assertEquals(expected, actual);
    }
}