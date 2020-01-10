package hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses o = new LongestValidParentheses();
        String pars = "()()()((()(((((())";
        final int result = o.longestValidParentheses2(pars);
        System.out.println(pars + " contains " + result + " chars valid substring");
    }

    private int longestValidParentheses(String s) {
        int res = 0;
        int[] longest = new int[s.length()];
        final char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (chars[i - 1] == '(') {
                    if (i >= 2) {
                        longest[i] = longest[i - 2] + 2;
                    } else {
                        longest[i] = 2;
                    }
                } else {
                    if (i - longest[i - 1] - 1 >= 0 && chars[i - longest[i - 1] - 1] == '(') {

                        longest[i] = longest[i - 1] + 2 +
                                ((i - longest[i - 1] - 2 >= 0) ?
                                        longest[i - longest[i - 1] - 2] :
                                        0);

                    }
                }
            }
            res = Math.max(res, longest[i]);
        }

        return res;
    }

    private int longestValidParentheses1(String s) {
        int res = 0;
        List<Integer> results = new ArrayList<>();
        Stack<Character> parantheses = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (parantheses.empty()) {
                    if (res != 0) {
                        results.add(res);
                    }
                    res = 0;
                } else {
                    final char pop = parantheses.pop();
                    res += 2;
                }
            } else {
                parantheses.push(c);
            }
        }
        if (parantheses.empty()) {
            results.add(res);
        }
        results.sort(Comparator.reverseOrder());
        System.out.println(results);
        return results.isEmpty() ? 0 : results.get(0);
    }

    private int longestValidParentheses2(String s) {
        final char[] chars = s.toCharArray();
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < chars.length; i++) {
            int top = stack.peek();
            if (top != -1 && chars[top] == '(' && chars[i] == ')') {
                stack.pop();
                int newTop = stack.peek();
                res = Math.max(i - newTop, res);
            } else {
                stack.push(i);
            }
        }
        return res;
    }

}
