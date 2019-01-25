import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromTest {

    @Test
    void testPalindromSchleife() {
        Palindrom palindrom = new Palindrom();
        assertTrue(palindrom.testPalindromSchleife("Regallager"));
        assertTrue(palindrom.testPalindromSchleife(""));
        assertFalse(palindrom.testPalindromSchleife("Philipp"));
    }

    @Test
    void testPalindromRekursiv() {
        Palindrom palindrom = new Palindrom();
        assertTrue(palindrom.testPalindromSchleife("Regallager"));
        assertTrue(palindrom.testPalindromSchleife(""));
        assertFalse(palindrom.testPalindromSchleife("Philipp"));
    }
}