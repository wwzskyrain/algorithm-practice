/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.array;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DominoAndTrominoTiling.java, v 0.1 2022年07月03日 16:35 yueyi Exp $
 */
public class DominoAndTrominoTiling {

    @LetCodeCommit(title = "790. Domino and Tromino Tiling",
    diff = "h",
    selfRemark = "这个递归式子找的好")
    public int numTilings(int n) {
        // dp[n] = 2*dp[n-1]+dp[n-3]
        int[] dp = new int[1001];
        int MOD = 1000000007;
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        if (n <= 3) {
            return dp[n];
        }
        for (int i = 4; i < dp.length; i++) {
            dp[i] = (2 * dp[i - 1] % MOD + dp[i - 3] % MOD) % MOD;
        }
        return dp[n];
    }

}