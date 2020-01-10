package eazy;

public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        reverseInteger.reverse(12301);
    }

    private int reverse(int x) {
        int sign = x > 0 ? 1 : -1;
        x *= sign;
        long result = 0;
        while (x > 0) {
            result = result * 10 + x % 10;
            if (result < 0) return 0;
            x /= 10;
        }
        if (result >>> 31 != 0) {
            return 0;
        }
        return (int) (result * sign);
    }

}
