/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumberOfWaysToRearrangeSticksWithKSticksVisible.java, v 0.1 2023年07月08日 15:01 yueyi Exp $
 */
@LetCodeCommit(title = "1866. Number of Ways to Rearrange Sticks With K Sticks Visible",
        diff = "h",
        selfRemark = "好久没遇到lee大神了。这种dp题目就是他的拿手戏。"
                + "dp[n][k]有两个来源"
                + "1.当最大值放在最后一位时，dp[n][k] = dp[n-1][k-1]"
                + "2.当最大值放不在最后一位时，dp[n][k] = dp[n-1][k] * (n - 1)。 注解，这(n-1)表示无论(1,...,n-1)谁放在最后一位，都是不可见的")
@RunWith(Parameterized.class)
public class NumberOfWaysToRearrangeSticksWithKSticksVisible {

    int[][] dp  = new int[1001][1001];
    int     mod = 1_000_000_007;

    public int rearrangeSticks(int n, int k) {
        if (n == k) {return 1;}
        if (k == 0) {return 0;}
        if (dp[n][k] == 0) {
            dp[n][k] = (int) ((rearrangeSticks(n - 1, k - 1) + (long) (n - 1) * rearrangeSticks(n - 1, k)) % mod);
        }
        return dp[n][k];
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int k;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {20, 11, 647427950},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, rearrangeSticks(n, k));
    }
}