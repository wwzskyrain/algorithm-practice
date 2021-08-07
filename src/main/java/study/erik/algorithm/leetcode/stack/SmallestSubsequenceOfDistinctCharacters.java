/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : SmallestSubsequenceOfDistinctCharacters.java, v 0.1 2021年08月07日 8:13 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SmallestSubsequenceOfDistinctCharacters {

    @LetCodeCommit(title = "Smallest Subsequence of Distinct Characters")
    public String smallestSubsequence(String s) {
        return resolveMonotonicStack(s);
    }

    /**
     * 还是单调栈，不过没把握好细节
     *
     * @param s
     * @return
     */
    public String resolveMonotonicStack(String s) {
        int[] lastChar = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastChar[s.charAt(i) - 'a'] = i;
        }

        boolean[] hasIn = new boolean[26];
        int top = 0;
        int[] stack = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int charIndex = s.charAt(i) - 'a';
            if (hasIn[charIndex]) {
                continue;
            }
            //i < lastChar[s.charAt(stack[top - 1]) - 'a'] 表示 i后面还有没有当前栈顶元素
            while (top > 0 && s.charAt(stack[top - 1]) > s.charAt(i) && i < lastChar[s.charAt(stack[top - 1]) - 'a']) {
                hasIn[s.charAt(stack[top - 1]) - 'a'] = false;
                top--;
            }
            // 这里不用判断top是否越界，因为最多不超过26个
            stack[top++] = i;
            hasIn[charIndex] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < top; i++) {
            sb.append(s.charAt(stack[i]));
        }
        return sb.toString();
    }

    @Parameter
    public String s;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"cbaacabcaaccaacababa", "abc"},
                {"ababbbba", "ab"},
                {"abcd", "abcd"},
                {"cdadabcc", "adbc"},
                {"bcabc", "abc"},
                {"cbacdcbc", "acdb"}
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, smallestSubsequence(s));
    }

}