/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.parentheses;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumAddToMakeParenthesesValid.java, v 0.1 2022年11月03日 22:53 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumAddToMakeParenthesesValid {

    @LetCodeCommit(title = "921. Minimum Add to Make Parentheses Valid")
    public int minAddToMakeValid(String s) {
        int balance = 0;
        int openingNumsToInsert = 0;
        for (int i = 0; i < s.length(); i++) {
            balance += s.charAt(i) == '(' ? 1 : -1;
            if (balance == -1) {
                openingNumsToInsert++;
                // 修正balance为非负
                balance++;
            }
        }
        // 如果balance>0说明有太多(没有消灭
        return openingNumsToInsert + balance;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"())", 1},
                {"(((", 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minAddToMakeValid(s));
    }
}