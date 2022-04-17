/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ValidParenthesisString.java, v 0.1 2022年04月17日 8:25 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ValidParenthesisString {

    @LetCodeCommit(title = "678. Valid Parenthesis String",
    diff = "m",
    selfRemark = "我这个写法是回溯法，确实没什么问题，就是性能差点。"
            + "lee大神有一个O(n)的写法，确实很好，思路是特征法，而不是操作.")
    public boolean checkValidString(String s) {
        return checkWithRecursive(s.toCharArray(), 0);
    }

    private boolean checkWithRecursive(char[] chars, int i) {
        if (i == chars.length) {
            return doCheck(chars);
        }
        if (chars[i] == '*') {
            chars[i] = '(';
            if (checkWithRecursive(chars, i + 1)) {
                return true;
            }
            chars[i] = ')';
            if (checkWithRecursive(chars, i + 1)) {
                return true;
            }
            char[] newChars = new char[chars.length - 1];
            System.arraycopy(chars, 0, newChars, 0, i);
            System.arraycopy(chars, i + 1, newChars, i, chars.length - (i + 1));
            if (checkWithRecursive(newChars, i)) {
                return true;
            }
        } else {
            return checkWithRecursive(chars, i + 1);
        }
        return false;
    }

    private boolean doCheck(char[] chars) {
        char[] stack = new char[chars.length];
        int index = 0;
        for (char c : chars) {
            if (c == '(') {
                stack[index++] = c;
            } else {
                if (index > 0 && stack[index - 1] == '(') {
                    index--;
                } else {
                    return false;
                }
            }
        }
        return index == 0;
    }

    @Parameter
    public String  s;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"*", true},
                {"(", false},
                {"()", true},
                {"(*)", true},
                {"(*))", true},
                {"()()", true},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, checkValidString(s));
    }
}