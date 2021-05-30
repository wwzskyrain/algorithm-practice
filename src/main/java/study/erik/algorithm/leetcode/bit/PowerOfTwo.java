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

import java.util.Arrays;

/**
 * @author yueyi
 * @version : PowerOfTwo.java, v 0.1 2021年05月30日 7:09 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PowerOfTwo {

    @LetCodeCommit(title = " Power of Two")
    public boolean isPowerOfTwo(int n) {
        return n <= 0 ? false : (n & (n - 1)) == 0;
    }

    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {1, true}, {16, true}, {3, false}, {4, true}, {5, false},
                {0, false}, {-2147483648, false}
        });
    }

    @Parameter
    public int     n;
    @Parameter(1)
    public boolean exception;

    // n=-2147483648          n-1=2147483647
    @Test
    public void test() {
        Assert.assertEquals(isPowerOfTwo(n), exception);
    }

}