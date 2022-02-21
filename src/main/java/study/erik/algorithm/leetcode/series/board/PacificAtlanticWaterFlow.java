/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.board;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : PacificAtlanticWaterFlow.java, v 0.1 2022年02月21日 9:22 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PacificAtlanticWaterFlow {

    @LetCodeCommit(title = "417. Pacific Atlantic Water Flow",
            time = 41, space = 31,
            selfRemark = "一遍过，代码比较多，思路很清晰."
                    + "注意，不能直接遍历heights，要用图遍历的方式来遍历heights")
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] toPacific = new boolean[m][n];
        boolean[][] visited = new boolean[m][n];
        int[][] directions = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Deque<int[]> queueToPacific = new LinkedList<>();
        queueToPacific.offer(new int[] {0, 0});
        toPacific[0][0] = true;
        visited[0][0] = true;
        while (!queueToPacific.isEmpty()) {
            int[] head = queueToPacific.poll();
            for (int[] direction : directions) {
                int x = head[0] + direction[0];
                int y = head[1] + direction[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                    continue;
                }
                if (x == 0 || y == 0 || heights[x][y] >= heights[head[0]][head[1]]) {
                    queueToPacific.offer(new int[] {x, y});
                    toPacific[x][y] = true;
                    visited[x][y] = true;
                }
            }
        }

        boolean[][] toAtlantic = new boolean[m][n];
        visited = new boolean[m][n];
        Deque<int[]> queueToAtlantic = new LinkedList<>();
        queueToAtlantic.offer(new int[] {m - 1, n - 1});
        toAtlantic[m - 1][n - 1] = true;
        visited[m - 1][n - 1] = true;
        while (!queueToAtlantic.isEmpty()) {
            int[] head = queueToAtlantic.poll();
            for (int[] direction : directions) {
                int x = head[0] + direction[0];
                int y = head[1] + direction[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                    continue;
                }
                if (x == m - 1 || y == n - 1 || heights[x][y] >= heights[head[0]][head[1]]) {
                    queueToAtlantic.offer(new int[] {x, y});
                    toAtlantic[x][y] = true;
                    visited[x][y] = true;
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (toPacific[i][j] && toAtlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    @Parameter
    public int[][]             heights;
    @Parameter(1)
    public List<List<Integer>> expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]"),
                        ArrayUtils.buildList2Dimension("[[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]")},
                {ArrayUtils.buildArray2Dimension("[[2,1],[1,2]]"),
                        ArrayUtils.buildList2Dimension("[[0,0],[0,1],[1,0],[1,1]]")},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, pacificAtlantic(heights));
    }

}