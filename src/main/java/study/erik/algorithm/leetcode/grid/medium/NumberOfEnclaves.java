/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author yueyi
 * @version : NumberOfEnclaves.java, v 0.1 2022年12月11日 14:27 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfEnclaves {

    @LetCodeCommit(title = "1020. Number of Enclaves")
    public int numEnclaves(int[][] grid) {
        // i is column
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 1) {
                bfs(grid, 0, i);
            }
            if (grid[grid.length - 1][i] == 1) {
                bfs(grid, grid.length - 1, i);
            }
        }

        for (int i = 1; i < grid.length - 1; i++) {
            if (grid[i][0] == 1) {
                bfs(grid, i, 0);
            }
            if (grid[i][grid[i].length - 1] == 1) {
                bfs(grid, i, grid[i].length - 1);
            }
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                res += grid[i][j] == 1 ? 1 : 0;
            }
        }
        return res;
    }

    int[][] directions = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private void bfs(int[][] grid, int x, int y) {

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        grid[x][y] = -1;
        Set<Integer> visited = new HashSet<>();
        visited.add(x * grid[x].length + y);

        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                int[] poll = q.poll();
                size--;
                for (int[] direction : directions) {
                    int newX = poll[0] + direction[0];
                    int newY = poll[1] + direction[1];
                    if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[newX].length) {
                        if (grid[newX][newY] == 0 || grid[newX][newY] == -1) {
                            continue;
                        }
                        int v = newX * grid[newX].length + newY;
                        if (!visited.contains(v)) {
                            visited.add(v);
                            q.offer(new int[] {newX, newY});
                            grid[newX][newY] = -1;
                        }
                    }
                }
            }
        }
    }

    @Parameter
    public int[][] grids;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]"), 3},
                {ArrayUtils.buildArray2Dimension("[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numEnclaves(grids));
    }

}