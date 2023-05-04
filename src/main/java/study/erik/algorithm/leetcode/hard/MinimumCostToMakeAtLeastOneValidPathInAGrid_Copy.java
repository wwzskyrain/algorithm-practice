/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : MinimumCostToMakeAtLeastOneValidPathInAGrid.java, v 0.1 2023年05月03日 19:17 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumCostToMakeAtLeastOneValidPathInAGrid_Copy {

    int     INF = (int) 1e9;
    int[][] DIR = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    //整体来看是一个bfs的算法框架。其中dfs只是为了寻找联通单元。
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length, cost = 0;
        int[][] dp = new int[m][n];// 既可以表示解，也可以表示访问过
        for (int i = 0; i < m; i++) {Arrays.fill(dp[i], INF);}
        Queue<int[]> bfs = new LinkedList<>();
        dfs(grid, 0, 0, dp, cost, bfs);
        while (!bfs.isEmpty()) {
            cost++;
            for (int size = bfs.size(); size > 0; size--) {
                int[] top = bfs.poll();
                int r = top[0], c = top[1];
                for (int i = 0; i < 4; i++) {
                    dfs(grid, r + DIR[i][0], c + DIR[i][1], dp, cost, bfs);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 用dfs找完想通的区域。其实可以不用dfs，因为每个cell只有一个箭头方向，所以完全可以用一个循环搞定的。
     *
     * @param grid
     * @param r
     * @param c
     * @param dp
     * @param cost
     * @param bfs
     */
    void dfs(int[][] grid, int r, int c, int[][] dp, int cost, Queue<int[]> bfs) {
        int m = grid.length;
        int n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || dp[r][c] != INF) {return;}
        dp[r][c] = cost;
        bfs.offer(new int[] {r, c}); // add to try change direction later
        int nextDir = grid[r][c] - 1;
        dfs(grid, r + DIR[nextDir][0], c + DIR[nextDir][1], dp, cost, bfs);
    }

    @Parameter
    public int[][] grid;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]"), 3},
                {ArrayUtils.buildArray2Dimension("[[1,1,3],[3,2,2],[1,1,4]]"), 0},
                {ArrayUtils.buildArray2Dimension("[[1,2],[4,3]]"), 1},
                {ArrayUtils.buildArray2Dimension("[[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]"), 3},
                {ArrayUtils.buildArray2Dimension("[[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]"), 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minCost(grid));
    }

}