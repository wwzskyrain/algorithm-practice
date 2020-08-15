package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-14 08:39
 */
public class ValidParentheses {

    @LetCodeCommit(no = 20, title = "Valid Parentheses", time = 98, space = 68,
            selfRemark = "这也算一个细节题了，看test_case都到8了，不过很简单")
    public boolean isValid(String s) {

        if ((s.length() & 1) == 1) {
            return false;
        }
        char[] stack = new char[s.length()];
        int stackIndex = 0;
        int i = 0;


        while (i < s.length()) {
            char c = s.charAt(i++);
            switch (c) {
                case '[':
                case '{':
                case '(':
                    stack[stackIndex++] = c;
                    break;
                case ']':
                    if (stackIndex <= 0) {
                        return false;
                    }
                    if (stack[stackIndex - 1] == '[') {
                        stackIndex--;
//                      出栈
                        break;
                    }
                    return false;
                case '}':
                    if (stackIndex <= 0) {
                        return false;
                    }
                    if (stack[stackIndex - 1] == '{') {
                        stackIndex--;
//                      出栈
                        break;
                    }
                    return false;

                case ')':
                    if (stackIndex <= 0) {
                        return false;
                    }
                    if (stack[stackIndex - 1] == '(') {
                        stackIndex--;
//                      出栈
                        break;
                    }
                    return false;

                default:
                    throw new IllegalArgumentException();
            }
        }
        return stackIndex == 0;

    }

    @Test
    public void test_solution1() {
        Assert.assertTrue(isValid("()"));
    }

    @Test
    public void test_solution_2() {
        Assert.assertTrue(isValid("()[]{}"));
    }

    @Test
    public void test_solution_3() {
        Assert.assertFalse(isValid("(]"));
    }

    @Test
    public void test_solution_4() {
        Assert.assertFalse(isValid("([)]"));
    }

    @Test
    public void test_solution_5() {
        Assert.assertTrue(isValid("{[]}"));
    }

    @Test
    public void test_solution_6() {
        Assert.assertTrue(isValid(""));
    }

    @Test
    public void test_solution_7() {
        Assert.assertFalse(isValid("(("));
    }

    @Test
    public void test_solution_8() {
        Assert.assertFalse(isValid("){"));
    }


}
