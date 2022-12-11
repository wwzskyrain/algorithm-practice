/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : LongestArithmeticSubsequence.java, v 0.1 2022年12月11日 20:24 yueyi Exp $
 */
public class LongestArithmeticSubsequence {

    @LetCodeCommit(title = "1027. Longest Arithmetic Subsequence")
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> index = new HashMap<>();
        int[][] dp = new int[n][n];
        int max = 2;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = nums[j] - nums[i];
                Integer lastIndex = index.get(nums[i] - diff);
                if (lastIndex == null) {
                    dp[i][j] = 2;
                } else {
                    dp[i][j] = dp[lastIndex][i] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
            index.put(nums[i], i);
        }
        return max;
    }
}