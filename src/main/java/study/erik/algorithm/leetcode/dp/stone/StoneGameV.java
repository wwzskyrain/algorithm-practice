/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.stone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : StoneGameV.java, v 0.1 2021年06月18日 12:32 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StoneGameV {

    @LetCodeCommit(title = "Stone Game V",
            selfRemark = "不玩游戏了，改求最值了。一分，一扔，一得")
    public int stoneGameV(int[] stoneValue) {

        int[][] dp = new int[stoneValue.length][stoneValue.length];
        // dp[i][j] = max{
        //                sum(i,k) == sum(k+1,j) ? sum(i,k) + max(dp[k+1][j], dp[i][k]) ||
        //                sum(i,k) >  sum(k+1,j) ? sum(k+1,j) + dp[k+1][j] : sum(i,k) + dp[i][k]
        //                };
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 0;
        }
        int[] prefixSum = new int[stoneValue.length];
        for (int i = 0; i < prefixSum.length; i++) {
            prefixSum[i] = i == 0 ? stoneValue[0] : prefixSum[i - 1] + stoneValue[i];
        }
        for (int l = 1; l < stoneValue.length; l++) {
            for (int i = 0; i < stoneValue.length; i++) {
                int j = i + l;
                for (int k = i; k < j && j < stoneValue.length; k++) {
                    int sumI_K = prefixSum[k] - prefixSum[i] + stoneValue[i];
                    int sumK1_J = prefixSum[j] - prefixSum[k];
                    if (sumI_K == sumK1_J) {
                        dp[i][j] = Math.max(dp[i][j], sumI_K + Math.max(dp[k + 1][j], dp[i][k]));
                    } else {
                        dp[i][j] = Math.max(dp[i][j], sumI_K > sumK1_J ? sumK1_J + dp[k + 1][j] : sumI_K + dp[i][k]);
                    }
                }
            }
        }
        return dp[0][stoneValue.length - 1];

    }

    @Parameter
    public int[] stoneValue;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {new int[]{6, 2, 3, 4, 5, 5}, 18},
                {new int[]{7, 7, 7, 7, 7, 7, 7}, 28},
                {new int[]{4}, 0},
                {new int[]{2, 1, 1}, 3},
                };
    }

    @Test
    public void test() {
        Assert.assertEquals(expect, stoneGameV(stoneValue));
    }

}