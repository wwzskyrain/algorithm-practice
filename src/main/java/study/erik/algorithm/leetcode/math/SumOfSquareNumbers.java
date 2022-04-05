/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : SumOfSquareNumbers.java, v 0.1 2022年04月02日 9:56 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SumOfSquareNumbers {

    @LetCodeCommit(title = "633. Sum of Square Numbers")
    public boolean judgeSquareSum(int c) {
        long left = 0, right = (int) Math.sqrt(c);
        while (left <= right) {
            // 必须用long，不然会溢出。
            // 当right有点大，需要调小的时，不用long就会溢出.
            long cur = left * left + right * right;
            if (cur < c) {
                left++;
            } else if (cur > c) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }

    @Parameter
    public int     c;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {2147483600, true},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, judgeSquareSum(c));
    }
}