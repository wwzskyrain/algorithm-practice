/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid;

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
import java.util.List;

/**
 * @author yueyi
 * @version : SpiralMatrix.java, v 0.1 2022年04月14日 8:26 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SpiralMatrix {


    public List<Integer> spiralOrderSolution2(int[][] matrix) {
        //还有一个方法，动态边界定义法
        List<Integer> ret = new ArrayList<>();
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                //访问当前上边界
                ret.add(matrix[up][i]);
            }
            if (++up > down) {
                //重新定义上边界。然后和下边界比较：up==down，说明还有一行；up>down则最后一行刚刚也访问完了。
                break;
            }
            // 访问右边界
            for (int i = up; i <= down; i++) {
                ret.add(matrix[i][right]);
            }
            if (--right < left) {
                break;
            }
            // 访问下边界
            for (int i = right; i >= left; i--) {
                ret.add(matrix[down][i]);
            }
            if (--down < up) {
                break;
            }

            for (int i = down; i >= up; i--) {
                ret.add(matrix[i][left]);
            }
            if(++left>right){
                break;
            }
        }
        return ret;
    }

    @LetCodeCommit(title = "54. Spiral Matrix",
            selfRemark = "同样的好成绩， 100%和90%" +
                    "确实不错的。")
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<>();
        // 东、南、西、北
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        int x = 0, y = 0;
        while (true) {
            ret.add(matrix[x][y]);
            matrix[x][y] = Integer.MAX_VALUE;
            int nextX = x + directions[directionIndex][0];
            int nextY = y + directions[directionIndex][1];
            if (valid(nextX, nextY, matrix)) {
                x = nextX;
                y = nextY;
            } else {
                directionIndex = (directionIndex + 1) % 4;
                nextX = x + directions[directionIndex][0];
                nextY = y + directions[directionIndex][1];
                if (valid(nextX, nextY, matrix)) {
                    x = nextX;
                    y = nextY;
                } else {
                    return ret;
                }
            }
        }
    }

    private boolean valid(int x, int y, int[][] ret) {
        int m = ret.length;
        int n = ret[0].length;
        return x >= 0 && x < m &&
                y >= 0 && y < n &&
                ret[x][y] != Integer.MAX_VALUE;
    }

    @Parameter
    public int[][] matrix;
    @Parameter(1)
    public List<Integer> expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
//                {
//                        ArrayUtils.buildArray2Dimension("[[1,2,3],[4,5,6],[7,8,9]]"),
//                        Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5)
//                },
                {
                        ArrayUtils.buildArray2Dimension("[[2,5],[8,4],[0,-1]]"),
                        Arrays.asList(2, 5, 4, -1, 0, 8),
                        },
                {
                        ArrayUtils.buildArray2Dimension("[[1,2,3,4],[5,6,7,8],[9,10,11,12]]"),
                        Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
                        },
                };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, spiralOrderSolution2(matrix));
    }
}
