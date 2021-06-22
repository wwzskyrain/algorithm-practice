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
 * @version : StoneGameVII.java, v 0.1 2021年06月19日 10:05 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StoneGameVII {

    @LetCodeCommit(title = "Stone Game VII")
    public int stoneGameVII(int[] stones) {
        int[][] dp = new int[stones.length][stones.length];
        // dp[i][j] 表示子数组stone[i...j]的先手最大差值
        // dp[i][j] = max(sumI1_J - dp[i+1][j], sumI_1J - dp[i][j-1])
        int[] preSum = new int[stones.length];
        for (int i = 0; i < preSum.length; i++) {
            preSum[i] = i == 0 ? stones[0] : stones[i] + preSum[i - 1];
        }
        for (int l = 1; l < stones.length; l++) {
            for (int i = 0; i < stones.length; i++) {
                int j = i + l;
                if (j >= stones.length) {
                    continue;
                }
                dp[i][j] = Math.max(
                        preSum[j] - preSum[i] - dp[i + 1][j],
                        preSum[j - 1] - preSum[i] + stones[i] - dp[i][j - 1]);
            }
        }
        return dp[0][stones.length - 1];
    }

    @Parameter
    public int[] stones;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {5, 3, 1, 4, 2}, 6},
                {new int[] {7, 90, 5, 1, 100, 10, 10, 2}, 122}
        };
    }

    @Test
    public void test() {
        Assert.assertEquals(expect, stoneGameVII(stones));
    }
}