/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ScoreOfParentheses.java, v 0.1 2022年05月23日 07:21 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ScoreOfParentheses {

    @LetCodeCommit(title = "856. Score of Parentheses")
    public int scoreOfParentheses(String s) {
        if (s.length() == 2) {
            return 1;
        }
        String substring = s.substring(1, s.length() - 1);
        if (isParent(substring)) {
            return scoreOfParentheses(substring) * 2;
        }
        int idx = 0;
        char[] chars = s.toCharArray();
        int startIndex = 0;
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                idx++;
            } else {
                idx--;
                if (idx == 0) {
                    res += (scoreOfParentheses(s.substring(startIndex, i + 1)));
                    startIndex = i + 1;
                }
            }
        }
        return res;
    }

    public boolean isParent(String s) {
        int idx = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                idx++;
                continue;
            }
            idx--;
            if (idx < 0) {
                return false;
            }
        }
        return idx == 0;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"(()(()))", 6},
                {"()", 1},
                {"(())", 2},
                {"((()))", 4},
                {"(((())))", 8},
                {"()()", 2},
                {"((()))(())()", 7}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, scoreOfParentheses(s));
    }

}