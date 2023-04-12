/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumFallingPathSumII.java, v 0.1 2023年03月26日 14:07 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumFallingPathSumII {

    @LetCodeCommit(title = "1289. Minimum Falling Path Sum II")
    public int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int min1LastRow = Integer.MAX_VALUE;
        int min1ColumnLastRow = -1;
        int min2LastRow = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int min1 = Integer.MAX_VALUE;
            int min1Column = -1;
            int min2 = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    if (j == min1ColumnLastRow) {
                        dp[i][j] = grid[i][j] + min2LastRow;
                    } else {
                        dp[i][j] = grid[i][j] + min1LastRow;
                    }
                } else {
                    //第一行
                    dp[i][j] = grid[i][j];
                }
                int v = dp[i][j];
                if (v < min2) {
                    if (v < min1) {
                        min2 = min1;
                        min1 = v;
                        min1Column = j;
                    } else {
                        min2 = v;
                    }
                }
            }
            min1LastRow = min1;
            min2LastRow = min2;
            min1ColumnLastRow = min1Column;
        }
        return min1LastRow;
    }

    @Parameter
    public int[][] grid;

    @Parameter(1)
    public int except;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2,3],[4,5,6],[7,8,9]]"), 13},
                {ArrayUtils.buildArray2Dimension("[[7]]"), 7},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(except, minFallingPathSum(grid));
    }
}