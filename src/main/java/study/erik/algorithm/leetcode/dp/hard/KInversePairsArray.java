/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
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
 * @version : KInversePairsArray.java, v 0.1 2022年07月04日 09:11 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class KInversePairsArray {

    @LetCodeCommit(title = "629. K Inverse Pairs Array",
            diff = "h",
            selfRemark = "在dp的基础上，对求和做一个preSum优化.")
    public int kInversePairs(int n, int k) {

        int[][] dp = new int[n + 1][k + 1];
        int MOD = 1000000007;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                } else {
                    int count = (dp[i - 1][j] + MOD - (j >= i ? dp[i - 1][j - i] : 0)) % MOD;
                    dp[i][j] = (dp[i][j - 1] + count) % MOD;
                }
            }
        }
        return (dp[n][k] + MOD - (k > 0 ? dp[n][k - 1] : 0)) % MOD;
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
                //{3, 0, 1},
                //{3, 1, 2},
                //{5, 4, 20},
                {1000, 1000, 663677020},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, kInversePairs(n, k));
    }
}