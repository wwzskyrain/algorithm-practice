/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.robber;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : PizzaWith3NSlices.java, v 0.1 2023年05月04日 21:26 yueyi Exp $
 */
public class PizzaWith3NSlices {

    @LetCodeCommit(title = "1388. Pizza With 3n Slices",
            selfRemark = "这个题目要分析好，有两处。"
                    + "1.分析出把cycle变成line线性化"
                    + "2.在线性化时，分析出是选取不相邻的数字")
    public int maxSizeSlices(int[] slices) {
        int n = slices.length / 3;
        int m = slices.length;
        int[] slices1 = Arrays.copyOfRange(slices, 0, m - 1);
        int[] slices2 = Arrays.copyOfRange(slices, 1, m);
        return Math.max(max(slices1, n), max(slices2, n));
    }

    public int max(int[] slices, int n) {
        int m = slices.length;
        int[][] dp = new int[m + 1][n + 1];
        // dp[i][j]表示在前i个数中取j个数的最大值。
        // 这里用m+1和n+1，并且用i=1和j=1开始
        // 隐含的意义是i=0时，表示没有数据，选取0...j个都是0；
        // 隐含的意义是j=0时，表示不管有几个数据，我都不选，其和自然也为0；
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1) {
                    dp[i][j] = slices[i - 1];
                } else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i - 2][j - 1] + slices[i - 1]);
                }
            }
        }
        return dp[m][n];
    }

}