package eazy;

import static java.lang.String.valueOf;

public class Palindrome {

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        palindrome.isPalindrome(211121112);

    }

    private boolean isPalindrome(int x) {
        return x == reverse(x);
    }

    private int reverse(int x) {
        long result = 0;
        while (x > 0) {
            result = result * 10 + x % 10;
            if (result < 0) return 0;
            x /= 10;
        }
        if (result >>> 31 != 0) {
            return 0;
        }
        return (int) (result);
    }

}
