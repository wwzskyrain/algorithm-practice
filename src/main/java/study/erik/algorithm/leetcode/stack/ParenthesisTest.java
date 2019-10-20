package study.erik.algorithm.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author erik.wang
 * @Date 2019-10-18
 */
public class ParenthesisTest {

    private static final char[] endChars = {')', '}', ']'};

    /**
     * 测试一个s是不是一个'闭合'的字符串
     * leecode:https://leetcode.com/problems/valid-parentheses/
     * level：easy
     *
     * @param s
     * @return 效果很好：时间94%，存储less100%
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        for (char endChar : endChars) {
            char firstChar = s.charAt(0);
            if (endChar == firstChar) {
                return false;
            }
        }

        Deque<Character> stack = new ArrayDeque<>();
        char peekChar;
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    peekChar = stack.peek();
                    if (peekChar != '(') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                case ']':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    peekChar = stack.peek();
                    if (peekChar != '[') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    peekChar = stack.peek();
                    if (peekChar != '{') {
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test_validate_parentheses() {
        Assert.assertEquals(true, isValid("()"));
        Assert.assertEquals(true, isValid("()[]{}"));
        Assert.assertEquals(false, isValid("(]"));
    }


    /**
     * 解法一：双栈法，很朴素，但是很受用
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null && s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        Deque<Character> stackChar = new ArrayDeque<>();
        Deque<Integer> stackIndex = new ArrayDeque<>();
        stackChar.push('#');
        stackIndex.push(-1);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (chars[i] == '(') {
                stackChar.push(c);
                stackIndex.push(i);
            } else {
                if (stackChar.peek().equals('(')) {
                    stackChar.pop();
                    stackIndex.pop();
                    maxLength = Math.max(maxLength, i - stackIndex.peek());
                } else {
                    stackChar.push(c);
                    stackIndex.push(i);
                }
            }
        }
        return maxLength;
    }

    /**
     * @param s
     * @return leetcode = https://leetcode.com/problems/longest-valid-parentheses/
     * performance： 一般般
     * 这个题目我想了好久，起初以为很简单，然后人为好复杂，最后吃透了也就又归于简单了。
     * 这是解法2，解法一是借鉴的网上的一篇文章[https://www.cnblogs.com/sjjsh/p/4972336.html
     * 解法3是动态规划解法，时间复杂度100%
     * <p>
     * 思路：显然，无论怎样的匹配规则（貌似就只有一种匹配规则），匹配完成后，就只有一种形式。然后计算匹配剩下字符的index之间的距离
     * 取最大的那个就是匹配最长的那个子串啦。
     */
    public int longestValidParentheses1(String s) {
        if (s == null && s.length() == 0) {
            return 0;
        }
        int maxLength = 0;

        Deque<Integer> stackIndex = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (chars[i] == '(' || stackIndex.isEmpty()) {
                stackIndex.push(i);
            } else {
                if (chars[stackIndex.peek()] == ('(')) {
                    stackIndex.pop();
                } else {
                    stackIndex.push(i);
                }
            }
        }
        if (stackIndex.isEmpty()) {
            return chars.length;
        }
        int lastIndex = chars.length;
        while (!stackIndex.isEmpty()) {
            Integer index = stackIndex.pop();
            maxLength = Math.max(maxLength, lastIndex - index);
            lastIndex = index;
        }
        maxLength = Math.max(maxLength, lastIndex + 1);
        maxLength = maxLength / 2 * 2;
        return maxLength;
    }

    /**
     * 动态规划解法，堪称完美
     * performance：100、80
     * 解题思路：这种求子串的问题，一定要先顾一头，即子解的设定要是以[i]开头的子串，
     * 比如这里设计d[i]为义s[i]开头的最长符合字符串定义的子串的长度。这样以来再往下分析就清楚多了。
     *  小结：这是三个方法，都可以进行扩展即匹配字符为(){}[]。
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int maxLength = 0;
        char[] chars = s.toCharArray();
        int[] d = new int[chars.length];
        d[d.length - 1] = 0;
        for (int i = d.length - 2; i >= 0; i--) {
            if (chars[i] == '(') {
                int j = i + 1 + d[i + 1];
                if (j < d.length) {
                    // s[i+1,...,j-1]是以s[i+1]为首的最长字符串，即这些字符已经自己匹配成功了
                    if (chars[j] == ')') {
                        d[i] = d[i + 1] + 2;
                        if (j + 1 < d.length) { //注意续接上s[j+1]最长字符串。
                            d[i] += d[j + 1];
                            maxLength = Math.max(maxLength, d[i]);
                        }
                        maxLength = Math.max(maxLength, d[i]);
                    }

                }
            } else {
                d[i] = 0;
            }
        }
        return maxLength;
    }

    @Test
    public void test_longest_valid_parentheses() {
        Assert.assertEquals(2, longestValidParentheses2("(()"));
        Assert.assertEquals(2, longestValidParentheses2("()(()"));
        Assert.assertEquals(4, longestValidParentheses2(")()())"));

        Assert.assertEquals(8, longestValidParentheses2("()()()()"));
        Assert.assertEquals(4, longestValidParentheses2("(())"));

        Assert.assertEquals(8, longestValidParentheses2("((()()))"));

        Assert.assertEquals(2, longestValidParentheses2("(((()(()"));
    }


}
