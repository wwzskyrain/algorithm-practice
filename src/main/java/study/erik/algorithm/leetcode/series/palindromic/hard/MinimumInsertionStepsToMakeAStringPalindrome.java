/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.palindromic.hard;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumInsertionStepsToMakeAStringPalindrome.java, v 0.1 2023年04月16日 21:34 yueyi Exp $
 */
public class MinimumInsertionStepsToMakeAStringPalindrome {

    @LetCodeCommit(title = "1312. Minimum Insertion Steps to Make a String Palindrome",
            diff = "h",
            selfRemark = "绝妙的题解"
                    + "1.把问题转化为求两字符串的最长公共子串。LCS"
                    + "2.LCS时，来了一个顿挫，用dp[i+1][j+1]表示Si和Sj的最长公共子串")
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i + 1][j + 1] = s.charAt(i) == s.charAt(n - j - 1) ?
                        dp[i][j] + 1 : Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return n - dp[n][n];
    }

}