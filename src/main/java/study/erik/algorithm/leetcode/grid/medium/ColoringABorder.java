/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.medium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author yueyi
 * @version : ColoringABorder.java, v 0.1 2022年12月11日 21:25 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ColoringABorder {

    @LetCodeCommit(title = "1034. Coloring A Border")
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int[][] directions = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        q.add(new int[] {row, col});
        set.add(row * grid[row].length + col);
        int originalColor = grid[row][col];

        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                int[] poll = q.poll();
                size--;
                int x = poll[0];
                int y = poll[1];
                for (int[] direction : directions) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[newX].length) {
                        grid[x][y] = -originalColor;
                    } else if (grid[newX][newY] != originalColor && grid[newX][newY] != -originalColor) {
                        grid[x][y] = -originalColor;
                    } else if (grid[newX][newY] == originalColor) {
                        int v = newX * grid[newX].length + newY;
                        if (!set.contains(v)) {
                            set.add(v);
                            q.add(new int[] {newX, newY});
                        }
                    }
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == -originalColor) {
                    grid[i][j] = color;
                }
            }
        }

        return grid;
    }

    @Parameter
    public int[][] gird;
    @Parameter(1)
    public int     row;
    @Parameter(2)
    public int     col;
    @Parameter(3)
    public int     color;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,1,2],[1,1,2],[2,2,1]]"), 0, 0, 3},
                {ArrayUtils.buildArray2Dimension("[[1,1],[1,2]]"), 0, 0, 3},
                {ArrayUtils.buildArray2Dimension("[[1,2,2],[2,3,2]]"), 0, 1, 3},
                {ArrayUtils.buildArray2Dimension("[[1,1,1],[1,1,1],[1,1,1]]"), 1, 1, 2},
        };
    }

    @Test
    public void test_() {
        int[][] g = colorBorder(gird, row, col, color);
        for (int[] ints : g) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("------------");
    }

}