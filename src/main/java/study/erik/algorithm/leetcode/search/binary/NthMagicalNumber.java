/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NthMagicalNumber.java, v 0.1 2022年08月02日 00:33 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NthMagicalNumber {

    @LetCodeCommit(title = "878. Nth Magical Number",
    selfRemark = "又是一个高级的折半查找-binary_search")
    public int nthMagicalNumber(int n, int a, int b) {
        int MOD = 1_000_000_007;
        long L = leastCommonMultiple(a, b);
        long low = 0;
        // n要先转成long在进行乘，不然可能会溢出int
        long hi = (long)n * Math.min(a, b);
        while (low < hi) {
            long mid = low + (hi - low) / 2;
            if (mid / a + mid / b - mid / L < n) {
                low = mid + 1;
            } else {
                hi = mid;
            }
        }
        return ((int) (low % MOD));
    }

    private int leastCommonMultiple(int a, int b) {
        return a / greatestCommonDivisor(a, b) * b;
    }

    /**
     * 最大公约数
     *
     * @return 最大公约数
     */
    private int greatestCommonDivisor(int a, int b) {
        if (a == 0) {return b;}
        return greatestCommonDivisor(b % a, a);
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int a;
    @Parameter(2)
    public int b;
    @Parameter(3)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{1, 2, 3, 2},
                {4, 2, 3, 6},
                {1000000000, 40000, 40000, 999720007}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, nthMagicalNumber(n, a, b));
    }
}