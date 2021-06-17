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
 * @version : StoneGameIII.java, v 0.1 2021年06月17日 12:37 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StoneGameIII {

    @LetCodeCommit(title = "Stone Game III")
    public String stoneGameIII(int[] stoneValue) {
        return resolveWithDp(stoneValue);
    }

    /**
     * 有了前面的题目，感觉这个hard题好水.
     * todo
     * 1. dp的定义是这一题的关键
     * @param stoneValue
     * @return
     */
    public String resolveWithDp(int[] stoneValue) {
        int[] dp = new int[stoneValue.length + 1];
        dp[stoneValue.length] = 0; // 为计算方便
        // dp[i] 表示 先手在stoneValue[i, i+1, ... ]比后手的差值
        // dp[i] = max{ stone[i] - dp[i+1], stone[i] + stone[i+1] - dp[i+2], stone[i] + stone[i+1] + stone[3] - dp[i+3]}
        for (int i = stoneValue.length - 1; i >= 0; i--) {
            if (i + 1 < dp.length) {
                dp[i] = stoneValue[i] - dp[i + 1];
            }
            if (i + 2 < dp.length) {
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
            }
            if (i + 3 < dp.length) {
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
            }
        }
        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }

    }

    @Parameter
    public int[]  stoneValue;
    @Parameter(1)
    public String winner;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {1, 2, 3, 7}, "Bob"},
                {new int[] {1, 2, 3, -9}, "Alice"},
                {new int[] {1, 2, 3, 6}, "Tie"},
                {new int[] {1, 2, 3, -1, -2, -3, 7}, "Alice"},
                {new int[] {-1, -2, -3}, "Tie"}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(winner, stoneGameIII(stoneValue));
    }

}