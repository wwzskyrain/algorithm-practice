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
 * @version : StoneGameIV.java, v 0.1 2021年06月18日 7:56 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StoneGameIV {

    @LetCodeCommit(title = "Stone Game IV",
            postLink = "https://leetcode.com/problems/stone-game-iv/",
            selfRemark = "从n堆中那，每次拿一个平方数。谁先拿不动，谁输了." +
                    "也有点水，用dfs更直观。")
    public boolean winnerSquareGame(int n) {
        // 看代码很简单吧
        boolean[] dp = new boolean[n + 1];
        dp[0] = false;
        // dp[i] 表示 先手赢
        // dp[i] = !dp[i-1] || !dp[i-4] || !dp[i-9] || ...
        // dp[i] 在计算过程中是可以优化的——提前返回
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
//        return dp[n];
        return dfs(n, new Boolean[n + 1]);
    }

    public boolean dfs(int n, Boolean[] dp) {
        if (dp[n] != null) {
            return dp[n];
        }
        boolean ret = false;
        if (n == 1) {
            ret = true;
        } else {
            int i = 1;
            while (i * i <= n) {
                //如果拿了i*i，对手就会失败，哈哈，那就这样搞。
                if (!dfs(n - i * i, dp)) {
                    ret = true;
                    break;
                }
                i++;
            }
        }
        dp[n] = ret;
        return ret;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {1, true},
                {2, false},
                {4, true},
                {7, false},
                {8, true},
                {17, false}
        };
    }

    @Test
    public void test() {
        Assert.assertEquals(expect, winnerSquareGame(n));
    }

}