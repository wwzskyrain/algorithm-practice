/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : PartitionArrayForMaximumSum.java, v 0.1 2022年12月18日 12:55 yueyi Exp $
 */
public class PartitionArrayForMaximumSum {

    @LetCodeCommit(title = "1043. Partition Array for Maximum Sum",
            diff = "m",
            selfRemark = "看错了，这个题目很简单的")
    public int maxSumAfterPartitioning(int[] A, int K) {
        int N = A.length, dp[] = new int[N + 1];
        //dp[i]表示解
        for (int i = 1; i <= N; ++i) {
            int curMax = 0, best = 0;

            for (int k = 1; k <= K && i - k >= 0; ++k) {
                curMax = Math.max(curMax, A[i - k]);
                best = Math.max(best, dp[i - k] + curMax * k);
            }
            dp[i] = best;
        }
        return dp[N];
    }
}