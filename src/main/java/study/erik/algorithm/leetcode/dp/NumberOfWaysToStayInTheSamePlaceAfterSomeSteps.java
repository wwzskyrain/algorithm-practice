/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumberOfWaysToStayInTheSamePlaceAfterSomeSteps.java, v 0.1 2021年05月13日 12:53 下午 yueyi Exp $
 */

public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {

    @LetCodeCommit(title = "Number of Ways to Stay in the Same Place After Some Steps", no = 1269)
    public int numWays(int steps, int arrLen) {

        // dp[i][j] = dp[i-1][j-1] + dp[i-1][j] + dp[i-1][j+1];
        int maxColumn = Math.min(arrLen, steps);
        int MODULO = 1000000007;
        int[][] dp = new int[steps][maxColumn];
        for (int i = 0; i < dp[0].length; i++) {
            if (i < 2) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = 0;
            }

        }
        for (int i = 1; i < steps; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                long x = (j - 1) >= 0 ? dp[i - 1][j - 1] : 0;
                long y = dp[i - 1][j];
                long z = (j + 1) < dp[i].length ? dp[i - 1][j + 1] : 0;
                dp[i][j] = ((int) ((x + y + z) % MODULO));
            }
        }
        return dp[steps - 1][0];
    }

    @Test
    public void test_1() {
        int steps = 2, arrLen = 4;
        Assert.assertEquals(2, numWays(steps, arrLen));
    }

    @Test
    public void test_2() {
        int steps = 4, arrLen = 2;
        Assert.assertEquals(8, numWays(steps, arrLen));
    }

    @Test
    public void test_3() {
        int steps = 4, arrLen = 3;
        Assert.assertEquals(9, numWays(steps, arrLen));
    }
}