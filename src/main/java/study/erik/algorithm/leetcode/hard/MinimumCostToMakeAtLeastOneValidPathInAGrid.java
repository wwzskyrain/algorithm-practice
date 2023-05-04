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
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : MinimumCostToMakeAtLeastOneValidPathInAGrid.java, v 0.1 2023年05月03日 19:17 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumCostToMakeAtLeastOneValidPathInAGrid {

    @LetCodeCommit(title = "1368. Minimum Cost to Make at Least One Valid Path in a Grid",
            selfRemark = "这是地杰斯特拉的解法。后面还有一个BFS的解法"
                    + "首先要分析题目，这个就是求最短路径的题目——可惜第一次我并没有这么准确的认识到。"
                    + "最短路径问题用迪杰斯特拉和bfs都可以搞定。"
                    + "这两个解法我都熟悉了，不必多言——可以独立写出来吗")
    public int minCost(int[][] grid) {
        int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int n = grid.length;
        int m = grid[0].length;
        Comparator<int[]> c = Comparator.comparing(a -> a[0]);
        PriorityQueue<int[]> queue = new PriorityQueue<>(c);
        queue.add(new int[] {0, 0, 0});
        int[][] dist = new int[n][m];
        for (int[] ints : dist) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cost = poll[0];
            int row = poll[1];
            int col = poll[2];
            if (dist[row][col] != cost) {
                // outdated
                continue;
            }
            for (int i = 0; i < dirs.length; i++) {
                int nextRow = row + dirs[i][0];
                int nextCol = col + dirs[i][1];
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                    int newCost = cost;
                    if ((grid[row][col] - 1) != i) {
                        newCost++;
                    }
                    if (dist[nextRow][nextCol] > newCost) {
                        dist[nextRow][nextCol] = newCost;
                        queue.add(new int[] {newCost, nextRow, nextCol});
                    }
                }
            }
        }
        return dist[n - 1][m - 1];
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