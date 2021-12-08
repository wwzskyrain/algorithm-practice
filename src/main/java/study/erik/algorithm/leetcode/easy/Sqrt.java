/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
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
 * @version : Sqrt.java, v 0.1 2021年12月09日 2:20 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class Sqrt {

    @LetCodeCommit(title = "69. Sqrt(x)",
            selfRemark = "这个题目特别有陷阱，在while中的判断中，"
                    + "一律用除，不要用乘，因为乘容易溢出")
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int l = 1;
        int h = x;
        while (true) {
            int m = l + (h - l) / 2;
            if (m == x / m) {
                return m;
            } else if (m < x / m) {
                if ((m + 1) > x / (m + 1)) {
                    return m;
                }
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
    }

    @Parameter
    public int x;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {4, 2},
                {8, 2},
                {2147395599, 46339},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, mySqrt(x));
    }

}