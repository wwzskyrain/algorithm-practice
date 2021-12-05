/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CoinChangeII.java, v 0.1 2021年12月05日 10:40 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CoinChangeII {

    @LetCodeCommit(title = "518. Coin Change 2",
            selfRemark = "这个题目相对I其有意思程度要高一个级别。"
                    + "这里涉及到两层循环的顺序——是coins在外还是dp在外。"
                    + "首先，其相同点是其复杂度都是amount*coins.length，"
                    + "但是其计算的东西却不同的。"
                    + "另外，solution1和solution2的区别是dp[i]是先更新还是后更新，这一点不是很重要")
    public int change(int amount, int[] coins) {
        //return solution1(amount, coins);
        return solution2(amount, coins);
    }

    public int solution1(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 0; i < dp.length; i++) {
                int nextI = i + coin;
                if (nextI >= 0 && nextI < dp.length) {
                    dp[nextI] += dp[i];
                }
            }
        }
        return dp[amount];
    }

    public int solution2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 0; i < dp.length; i++) {
                int nextI = i + coin;
                if (nextI >= 0 && nextI < dp.length) {
                    dp[nextI] += dp[i];
                }
            }
        }
        return dp[amount];
    }

    @Parameter
    public int[] coins;
    @Parameter(1)
    public int   amount;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,5]"), 5, 4},
                {ArrayUtils.buildArray("[2]"), 3, 0},
                {ArrayUtils.buildArray("[10]"), 10, 1},
                {ArrayUtils.buildArray("[1,2,5]"), 5, 4},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, change(amount, coins));
    }

}