import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;


class PalindromeTest {

    @Test
    void isPalindrome() {
        assertTrue(Palindrome.isPalindrome(""));
        assertTrue(Palindrome.isPalindrome("A"));
        assertTrue(Palindrome.isPalindrome("Anna"));
        assertTrue(Palindrome.isPalindrome("Otto"));
        assertTrue(Palindrome.isPalindrome("AKA"));
        assertTrue(Palindrome.isPalindrome("12321"));
        assertFalse(Palindrome.isPalindrome("Anka"));
        assertFalse(Palindrome.isPalindrome("12312"));
        assertFalse(Palindrome.isPalindrome("Ottos"));
    }

    @Test
    void isPalindromeRecoursive() {
        assertTrue(Palindrome.isPalindromeRecoursive(""));
        assertTrue(Palindrome.isPalindromeRecoursive("A"));
        assertTrue(Palindrome.isPalindromeRecoursive("Anna"));
        assertTrue(Palindrome.isPalindromeRecoursive("Otto"));
        assertTrue(Palindrome.isPalindromeRecoursive("AKA"));
        assertTrue(Palindrome.isPalindromeRecoursive("12321"));
        assertFalse(Palindrome.isPalindromeRecoursive("Anka"));
        assertFalse(Palindrome.isPalindromeRecoursive("12312"));
        assertFalse(Palindrome.isPalindromeRecoursive("Ottos"));
    }
}