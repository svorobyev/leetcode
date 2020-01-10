package medium;

public class DivideTwoIntegers {

    public static void main(String[] args) {
        DivideTwoIntegers o = new DivideTwoIntegers();
        o.divide(-2147483648, 2);
    }


    private int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int result = 0;
        int a1 = Math.abs(dividend);
        int a2 = Math.abs(divisor);
        while (a1 - a2 >= 0) {
            int x = 0;
            while (a1 - (a2 << 1 << x) >= 0) {
                x++;
            }
            result += 1 << x;
            a1 -= a2 << x;
        }
        return (dividend >= 0) == (divisor >= 0) ? result : -result;
    }

}
