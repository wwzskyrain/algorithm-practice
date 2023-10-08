/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.backtracking.hard;

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
 * @version : CherryPickupII.java, v 0.1 2023年05月14日 16:51 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class Cherry_Pickup_II {

    @LetCodeCommit(title = "1463. Cherry Pickup II",
            diff = "h",
            selfRemark = "最近做的好多这类题目。dfs+mem"
                    + "无脑加备忘录。"
                    + "我们曾经思考过备忘录的效率。")
    public int cherryPickup(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] directions = new int[][] {{1, -1}, {1, 0}, {1, 1}};
        Integer[][][] dp = new Integer[row][col][col];
        return dfs(grid, 0, 0, col - 1, directions, dp);
    }

    public int dfs(int[][] grid, int row, int p1, int p2, int[][] directions, Integer[][][] dp) {
        if (row == grid.length) {
            return 0;
        }
        if (dp[row][p1][p2] != null) {
            return dp[row][p1][p2];
        }
        int cur = grid[row][p1] + grid[row][p2];
        int max = 0;
        for (int[] d1 : directions) {
            int nextP1 = d1[1] + p1;
            if (nextP1 < grid[row].length && nextP1 >= 0) {
                for (int[] d2 : directions) {
                    int nextP2 = d2[1] + p2;
                    if (nextP2 == nextP1) {
                        continue;
                    }
                    if (nextP2 < grid[row].length && nextP2 >= 0) {
                        max = Math.max(max, dfs(grid, row + 1, nextP1, nextP2, directions, dp));
                    }
                }
            }
        }
        dp[row][p1][p2] = cur + max;
        return dp[row][p1][p2];
    }

    @Parameter
    public int[][] grid;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[3,1,1],[2,5,1],[1,5,5],[2,1,1]]"), 24},
                {ArrayUtils.buildArray2Dimension("[[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]"), 28},
                {ArrayUtils.buildArray2Dimension("[[3,1,1],[2,5,1],[1,5,5],[2,1,1]]"), 24},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, cherryPickup(grid));
    }

}