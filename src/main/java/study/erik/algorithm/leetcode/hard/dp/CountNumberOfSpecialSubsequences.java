/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CountNumberOfSpecialSubsequences.java, v 0.1 2023年07月27日 14:44 yueyi Exp $
 */
public class CountNumberOfSpecialSubsequences {

    @LetCodeCommit(title = "1955. Count Number of Special Subsequences",
            selfRemark = "这个题目简练有难度，适合面试。")
    public int countSpecialSubsequences(int[] nums) {
        int[] dp = new int[3];
        int MOD = 1_000_000_007;
        for (int num : nums) {
            switch (num) {
                case 0:
                    // 三部分组成：
                    // 1.之前的【0】序列；
                    // 2.之前的【0】序列都加上一个0；
                    // 3.这个0自身组成的一个【0】序列
                    dp[num] = ((dp[num] + 1) % MOD + dp[num]) % MOD;
                    break;
                case 1:
                    // 三部分组成：
                    // 1.之前的【0、1】序列；
                    // 2.之前的【0、1】序列都加上一个1；
                    // 3.之前的【0】序列都加上一个1
                    dp[num] = ((dp[num] + dp[0]) % MOD + dp[num]) % MOD;
                    break;
                case 2:
                    // 三部分组成：
                    // 1.之前的【0、1、2】序列；
                    // 2.之前的【0、1、2】序列都加上一个2；
                    // 3.之前的【0、1】序列都加上一个1
                    dp[num] = ((dp[num] + dp[num]) % MOD + dp[1]) % MOD;
                default:
            }
        }
        return dp[2];
    }
}