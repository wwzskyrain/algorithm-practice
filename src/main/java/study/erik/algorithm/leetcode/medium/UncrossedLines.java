/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : UncrossedLines.java, v 0.1 2022年12月11日 23:01 yueyi Exp $
 */
public class UncrossedLines {

    @LetCodeCommit(title = "1035. Uncrossed Lines",
            selfRemark = "小小dp题目，就不测试了")
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[][] dp = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                dp[i][j] = (nums1[i] == nums2[j] ? 1 : 0);
                if (i > 0 && j > 0) {
                    // 有左上角
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + (nums1[i] == nums2[j] ? 1 : 0));
                }
                if (i > 0) {
                    // 有上
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                if (j > 0) {
                    // 有左
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1 - 1][n2 - 1];
    }

}