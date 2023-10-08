package study.erik.algorithm.leetcode.matrix;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/6 ，时间：11:54
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "2088. Count Fertile Pyramids in a Land")
public class Count_Fertile_Pyramids_in_a_Land {


    /**
     * dp的解法
     *
     * @param grid
     * @return
     */
    public int countPyramidsSolution2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        /**
         *  dp[i][j]以[i][j]为顶点的最大金字塔的高度-1；
         *  对于正金字塔：dp[i][j] = min( dp[i+1][j-1],dp[i+1][j],dp[i+1][j+1] )+1;
         */
        int total = 0;
        for (int i = m - 1; i >= 0; i--) {
            //正金字塔
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    dp[i][j] = -1; //是为了Q1计算统一加1方便。
                } else {
                    if (i == m - 1 || j == 0 || j == n - 1) {
                        dp[i][j] = 0;
                    } else {
                        //Q1:计算
                        dp[i][j] = Math.min(Math.min(dp[i + 1][j - 1], dp[i + 1][j]), dp[i + 1][j + 1]) + 1;
                        total += dp[i][j];
                    }
                }

            }
        }
        for (int i = 0; i < m; i++) {
            //倒金字塔
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    dp[i][j] = -1;
                } else {
                    if (i == 0 || j == 0 || j == n - 1) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]) + 1;
                        total += dp[i][j];
                    }
                }
            }
        }
        return total;
    }


    /**
     * 勉强过关
     *
     * @param grid
     * @return
     */
    public int countPyramidsSolution1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Four[][] ff = new Four[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ff[i][j] = new Four();
                if (grid[i][j] == 0) {
                    ff[i][j].left = 0;
                    ff[i][j].up = 0;
                } else {
                    ff[i][j].left = j == 0 ? grid[i][j] : ff[i][j - 1].left + 1;
                    ff[i][j].up = i == 0 ? grid[i][j] : ff[i - 1][j].up + 1;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    ff[i][j].down = 0;
                    ff[i][j].right = 0;
                } else {
                    ff[i][j].down = (i == (m - 1)) ? grid[i][j] : ff[i + 1][j].down + 1;
                    ff[i][j].right = (j == (n - 1)) ? grid[i][j] : ff[i][j + 1].right + 1;
                }
            }
        }
        int total = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int h = 2;
                while (true) {
                    Four four = ff[i][j];
                    if (four.down < h) {
                        break;
                    }
                    int ii = i + h - 1;
                    if (ii >= m) {
                        break;
                    }
                    Four four1 = ff[ii][j];
                    if (four1.left < h || four1.right < h) {
                        break;
                    }
                    total++;
                    h++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int h = 2;
                while (true) {
                    Four four = ff[i][j];
                    if (four.up < h) {
                        break;
                    }
                    int ii = i - (h - 1);
                    if (ii < 0) {
                        break;
                    }
                    Four four1 = ff[ii][j];
                    if (four1.left < h || four1.right < h) {
                        break;
                    }
                    total++;
                    h++;
                }
            }
        }
        return total;
    }

    public static class Four {
        public int up;
        public int down;
        public int left;
        public int right;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray2Dimension("[[0,1,1,0],[1,1,1,1]]")}, {2, ArrayUtils.buildArray2Dimension("[[1,1,1],[1,1,1]]")}, {0, ArrayUtils.buildArray2Dimension("[[1,0,1],[0,0,0],[1,0,1]]")}, {13, ArrayUtils.buildArray2Dimension("[[1,1,1,1,0],[1,1,1,1,1],[1,1,1,1,1],[0,1,0,0,1]]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] grid;

    @Test
    public void test() {
//        Assert.assertEquals(expect, countPyramidsSolution1(grid));
        Assert.assertEquals(expect, countPyramidsSolution2(grid));
    }

}
