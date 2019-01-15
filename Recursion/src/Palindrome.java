public class Palindrome {
    public static boolean isPalindrome(String input) {
        input = input.toLowerCase();
        for (int i = 0; i < input.length()/2; i++) {
            if (input.charAt(i) != input.charAt(input.length()-(i+1))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeRecoursive(String input) {
        return isPalindromeRecoursiveRespectCase(input.toLowerCase());
    }

    public static boolean isPalindromeRecoursiveRespectCase(String input) {
        if (input.length() <= 1) {
            return true;
        }
        return input.charAt(0) == input.charAt(input.length()-1) && isPalindromeRecoursiveRespectCase(input.substring(1, input.length()-1));
    }
}
