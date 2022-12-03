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
 * @version : RottingOranges.java, v 0.1 2022年12月03日 14:58 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RottingOranges {

    @LetCodeCommit(title = "994. Rotting Oranges")
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case 1:
                        count++;
                        break;
                    case 2:
                        q.add(new int[] {i, j});
                        set.add(i * m + j);
                        break;
                    default:
                }
            }
        }
        int min = 0;
        if (count == 0) {
            return min;
        }
        int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!q.isEmpty()) {
            min++;
            int s = q.size();
            while (s > 0) {
                int[] poll = q.poll();
                int x = poll[0];
                int y = poll[1];
                for (int[] dir : dirs) {
                    int xN = x + dir[0];
                    int yN = y + dir[1];
                    if (xN >= 0 && xN < n && yN >= 0 && yN < m) {
                        if (grid[xN][yN] == 1) {
                            if (!set.contains(xN * m + yN)) {
                                count--;
                                if (count == 0) {
                                    return min;
                                }

                                q.add(new int[] {xN, yN});
                                set.add(xN * m + yN);
                            }
                        }
                    }
                }
                s--;
            }
        }
        return -1;
    }

    @Parameter
    public int[][] grid;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[2,1,1],[1,1,0],[0,1,1]]"), 4},
                {ArrayUtils.buildArray2Dimension("[[2,1,1],[0,1,1],[1,0,1]]"), -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, orangesRotting(grid));
    }

}