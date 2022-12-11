/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RemoveAllAdjacentDuplicatesInString.java, v 0.1 2022年12月10日 20:54 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RemoveAllAdjacentDuplicatesInString {

    @LetCodeCommit(title = "1047. Remove All Adjacent Duplicates In String")
    public String removeDuplicates(String s) {
        char[] stack = new char[s.length()];
        char[] chars = s.toCharArray();
        int i = 0;
        for (char c : chars) {
            if (i == 0) {
                stack[i++] = c;
            } else {
                if (stack[i - 1] == c) {
                    i--;
                } else {
                    stack[i++] = c;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int ii = 0;
        while (ii < i) {
            sb.append(stack[ii++]);
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
                {"abbaca", "ca"},
                {"azxxzy", "ay"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, removeDuplicates(s));
    }
}