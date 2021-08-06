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
 * @version : RemoveKDigits.java, v 0.1 2021年08月06日 11:43 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RemoveKDigits {

    @LetCodeCommit(title = "Remove K Digits")
    public String removeKdigits(String num, int k) {
        return result(num, k);
    }

    /**
     * 这个单调栈解法和上一题如出一辙。
     * @param num
     * @param k
     * @return
     */
    public String result(String num, int k) {
        if (num.length() == k) {
            return "0";
        }
        char[] stack = new char[num.length() - k];
        int stackIndex = 0;
        for (int i = 0; i < num.length(); i++) {
            while (stackIndex > 0 && stack[stackIndex - 1] > num.charAt(i) && num.length() - i + stackIndex > stack.length) {
                stackIndex--;
            }
            if (stackIndex < stack.length) { stack[stackIndex++] = num.charAt(i); }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < stack.length && stack[i] == '0') { i++; }
        while (i < stack.length) {
            sb.append(stack[i++]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    @Parameter
    public String num;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"112", 1, "11"},
                {"10", 1, "0"},
                {"1432219", 3, "1219"},
                {"10200", 1, "200"},
                {"10", 2, "0"},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, removeKdigits(num, k));
    }

}