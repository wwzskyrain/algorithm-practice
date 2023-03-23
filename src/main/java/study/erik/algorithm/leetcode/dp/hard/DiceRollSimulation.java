/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DiceRollSimulation.java, v 0.1 2023年03月23日 22:07 yueyi Exp $
 */
public class DiceRollSimulation {

    @LetCodeCommit(title = "1223. Dice Roll Simulation",
            diff = "hard",
            selfRemark = "这个dp用的真好，https://leetcode.com/problems/dice-roll-simulation/solutions/423808/c-java-two-dimensional-dp-with-explanation/")
    public int dieSimulator(int n, int[] rollMax) {
        long[][] dp = new long[n + 1][6];
        long[] sum = new long[n + 1];
        sum[0] = 1;
        long MOD = 1000_000_007;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j] && i - k >= 0; k++) {
                    dp[i][j] = (dp[i][j] + sum[i - k] - dp[i - k][j] + MOD) % MOD;
                }
                sum[i] = (sum[i] + dp[i][j]) % MOD;
            }
        }
        return ((int) sum[n]);
    }

}