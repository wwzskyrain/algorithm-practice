/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumberOfDiceRollsWithTargetSum.java, v 0.1 2023年03月04日 17:44 yueyi Exp $
 */
public class NumberOfDiceRollsWithTargetSum {

    @LetCodeCommit(title = "1155. Number of Dice Rolls With Target Sum",
            selfRemark = "这是一个很经典的dp题目")
    public int numRollsToTarget(int n, int face, int target) {
        int MOD = (int) Math.pow(10, 9) + 7;
        long[][] dp = new long[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int t = 0; t <= target; t++) {
                if (t > i * face) {
                    // 优化了
                    break;
                }
                for (int k = 1; k <= face; k++) {
                    if (t >= k) {
                        dp[i][t] = (dp[i][t] + dp[i - 1][t - k]) % MOD;
                    } else {
                        break;
                    }
                }
            }
        }
        return (int) dp[n][target];
    }

}