/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : SpiralMatrixIII.java, v 0.1 2022年04月14日 3:14 下午 yueyi Exp $
 */
public class SpiralMatrixIII {

    @LetCodeCommit(title = "885. Spiral Matrix III",
    selfRemark = "题目出的是有点恶心，但是还好有规律。"
            + "成绩不错，80%和88%")
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {

        int[][] grid = new int[rows * cols][2];
        // 东、南、西、北
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIndex = 0;
        int l = 1, ll = l;
        int i = 0;
        int x = rStart, y = cStart;
        grid[i++] = new int[] {x, y};
        while (i < grid.length) {
            if (ll == 0) {
                dirIndex = (dirIndex + 1) % 4;
                if (dirIndex % 2 == 0) {
                    // 转向到东、西时要加长
                    l++;
                }
                ll = l;
            }
            x += directions[dirIndex][0];
            y += directions[dirIndex][1];
            if (valid(x, y, rows, cols)) {
                grid[i++] = new int[] {x, y};
            }
            ll--;
        }
        return grid;
    }

    private boolean valid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    @Test
    public void test_0() {
        int[][] grid = spiralMatrixIII(1, 4, 0, 0);
        Arrays.stream(grid).map(Arrays::toString).forEach(System.out::println);
    }

    @Test
    public void test_1() {
        int[][] grid = spiralMatrixIII(5, 6, 1, 4);
        Arrays.stream(grid).map(Arrays::toString).forEach(System.out::println);
    }
}