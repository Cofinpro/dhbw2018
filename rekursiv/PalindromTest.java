package rekursiv;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromTest {

    @Test
    public void palindromLoop() {

        String palindromString = "OTTo";
        assertEquals(Palindrom.palindromLoop(palindromString.toLowerCase()),true);

        String nonPalindrom = "dsofhgdsfg";
        assertEquals(Palindrom.palindromLoop(nonPalindrom.toLowerCase()),false);

    }

    @Test
    public void palindromRekusiv() {

        String palindromString = "OTtO";
        assertEquals(Palindrom.palindromRekusiv(palindromString.toLowerCase()),true);

        String nonPalindrom = "dsofhgdsfg";
        assertEquals(Palindrom.palindromRekusiv(nonPalindrom.toLowerCase()),false);
    }
}