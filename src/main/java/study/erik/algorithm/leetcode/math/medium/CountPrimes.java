/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : CountPrimes.java, v 0.1 2021年12月21日 11:06 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CountPrimes {

    @LetCodeCommit(title = "204. Count Primes",
            selfRemark = "第一个暴力解法超时；"
                    + "这里有个好解法，就是'生成法'，而不是check法。",
            tag = "好题")
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        //默认大家都是素数——两重否定
        boolean[] notPrimes = new boolean[n];
        int count = 0;
        notPrimes[2] = false;
        for (int i = 2; i < notPrimes.length; i++) {
            if (!notPrimes[i]) {
                count++;
                // 迭代生成更多非负数，神奇的是，不同质数的自然数倍数是不会重叠的；
                for (int j = 2; i * j < notPrimes.length; j++) {
                    notPrimes[i * j] = true;
                }
            }
        }
        return count;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {9, 4},
                {2, 0},
                {3, 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, countPrimes(n));
    }
}