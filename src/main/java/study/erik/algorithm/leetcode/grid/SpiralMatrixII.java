/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : SpiralMatrixII.java, v 0.1 2022年04月13日 10:50 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SpiralMatrixII {

    @LetCodeCommit(title = "Spiral Matrix II",
            diff = "m",
            selfRemark = "成绩不错，100和90，0ms")
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];

        int i = 1;
        // 东、南、西、北
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        int x = 0, y = 0;
        while (true) {
            ret[x][y] = i++;
            int nextX = x + directions[directionIndex][0];
            int nextY = y + directions[directionIndex][1];
            if (valid(nextX, nextY, ret, n)) {
                x = nextX;
                y = nextY;
            } else {
                directionIndex = (directionIndex + 1) % 4;
                nextX = x + directions[directionIndex][0];
                nextY = y + directions[directionIndex][1];
                if (valid(nextX, nextY, ret, n)) {
                    x = nextX;
                    y = nextY;
                } else {
                    return ret;
                }
            }
        }
    }

    private boolean valid(int x, int y, int[][] ret, int n) {
        return x >= 0 && x < n &&
                y >= 0 && y < n &&
                ret[x][y] == 0;
    }

    @Parameter
    public int n;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3},
                {4},
                {5},
                {6},
                {7},
        };
    }

    @Test
    public void test_1() {
        int[][] grid = generateMatrix(n);
        Arrays.stream(grid).map(Arrays::toString).forEach(System.out::println);
        System.out.println("-------");
    }
}