/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumberOfWaysToStayInTheSamePlaceAfterSomeSteps.java, v 0.1 2023年04月08日 11:32 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {

    @LetCodeCommit(title = "1269. Number of Ways to Stay in the Same Place After Some Steps",
            selfRemark = "这个题目很简单的，但是对空间和时间都要求的比较严，所以才是hard级别。"
                    + "对于空间，简化为两个数组。为什么能简化，不说。"
                    + "对于时间，简化实际的arrLen，取steps/2和arrLen的较小值。")
    public int numWays(int steps, int arrLen) {
        arrLen = Math.min(steps / 2 + 1, arrLen);
        long[][] dp = new long[2][arrLen];
        dp[0][0] = 1;
        int s = 1;
        long MOD = 1000000007;
        while (s <= steps) {
            int cur = s % 2;
            int last = 1 - cur;
            for (int j = 0; j < arrLen; j++) {
                dp[cur][j] = dp[last][j];
                if (j > 0) {
                    dp[cur][j] = (dp[cur][j] + dp[last][j - 1]) % MOD;
                }
                if (j < arrLen - 1) {
                    dp[cur][j] = (dp[cur][j] + dp[last][j + 1]) % MOD;
                }
            }
            s++;
        }
        return ((int) dp[(steps) % 2][0]);
    }

    @Parameter
    public int steps;
    @Parameter(1)
    public int arrLen;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3, 2, 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numWays(steps, arrLen));
    }

}