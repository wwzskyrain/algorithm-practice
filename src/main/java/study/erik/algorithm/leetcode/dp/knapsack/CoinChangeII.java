/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.knapsack;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CoinChangeII.java, v 0.1 2021年06月10日 8:45 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CoinChangeII {

    @LetCodeCommit(title = "Coin Change 2")
    public int change(int amount, int[] coins) {
        //return solutionWithDp(amount, coins);
        return solutionWithDpII(amount, coins);
    }

    /**
     * 这种解法是经典的背包问题解法，然后这种接法的成绩很低，因为还有一种更巧妙的解法。
     *
     * @param amount
     * @param coins
     * @return
     */
    public int solutionWithDp(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        // dp[i,j] = dp[i-1, j] + dp[i-1, j-c[i]] + dp[i-1, j-2*c[i]] + dp[i-1, j-3*c[i]]

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = i % coins[0] == 0 ? 1 : 0;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                int sum = 0;
                for (int k = 0; k <= j / coins[i]; k++) {
                    sum += dp[i - 1][j - k * coins[i]];
                }
                dp[i][j] = sum;
            }
        }
        return dp[coins.length - 1][amount];
    }

    /**
     * 这种接法才表流行，反其道而行之
     *
     * @param amount
     * @param coins
     * @return
     */
    public int solutionWithDpII(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // dp[i] = dp[i] + dp[i-coins[j]]
        // dp[i]  表示以当前coins[0...j]这几种硬币可以make up成i的组合种类.
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i < dp.length; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {5, new int[] {1, 2, 5}, 4},
                {3, new int[] {2}, 0},
                {10, new int[] {10}, 1},
        };
    }

    @Parameter
    public int   amount;
    @Parameter(1)
    public int[] coins;
    @Parameter(2)
    public int   expect;

    @Test
    public void test() {
        Assert.assertEquals(expect, change(amount, coins));
    }
}