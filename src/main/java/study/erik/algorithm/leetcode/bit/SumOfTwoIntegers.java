/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : SumOfTwoIntegers.java, v 0.1 2021年11月14日 7:20 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SumOfTwoIntegers {

    @LetCodeCommit(title = "371. Sum of Two Integers")
    public int getSum(int a, int b) {
        int c = a & b;
        while (c != 0) {
            b = a ^ b;
            a = c << 1;
            c = a & b;
        }
        return a ^ b;
    }

    @Parameter
    public int a;
    @Parameter(1)
    public int b;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{1, 2, 3},
                {2, 3, 5},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, getSum(a, b));
    }
}