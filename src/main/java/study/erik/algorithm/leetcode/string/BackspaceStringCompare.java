/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : BackspaceStringCompare.java, v 0.1 2022年05月22日 10:09 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BackspaceStringCompare {

    @LetCodeCommit(title = "844. Backspace String Compare")
    public boolean backspaceCompare(String s, String t) {

        char[] clearedS = clear(s);
        char[] clearedT = clear(t);
        if (clearedS.length != clearedT.length) {
            return false;
        }
        for (int i = 0; i < clearedS.length; i++) {
            if (clearedS[i] != clearedT[i]) {
                return false;
            }
        }
        return true;
    }

    public char[] clear(String s) {
        char[] stack = new char[s.length()];
        int i = 0;
        for (char c : s.toCharArray()) {
            if (c == '#') {
                i = Math.max(0, i - 1);
            } else {
                stack[i++] = c;
            }
        }
        return Arrays.copyOf(stack, i);
    }

    @Parameter
    public String  s;
    @Parameter(1)
    public String  t;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"ab#c", "ad#c", true},
                {"a##c", "#a#c", true},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, backspaceCompare(s, t));
    }

}