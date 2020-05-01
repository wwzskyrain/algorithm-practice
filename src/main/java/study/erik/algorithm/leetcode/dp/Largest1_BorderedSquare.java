package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-01 00:06
 */
public class Largest1_BorderedSquare {

    public int largest1BorderedSquare(int[][] grid) {
        return solution(grid);
    }

    /**
     * 成绩：89和100%
     * 思路是我自己想的，而且是第一直觉——给自己👏。
     * 但是实现的时候，这里用了两个辅助数据，而不是用一个辅助数组+二元组的形式，这一点优化是来自于diss的。
     * 除此之外，代码完全是独立编写，而且不复杂的。
     *
     * @param grid
     * @return
     */
    public int solution(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int longestEdge = 0;
        //垂直累积辅助数组
        int[][] ver = new int[grid.length][grid[0].length];
        //水平累积辅助数组
        int[][] hor = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    longestEdge = 1;
                    ver[i][j] = grid[i][j] + (i > 0 ? ver[i - 1][j] : 0);
                    hor[i][j] = grid[i][j] + (j > 0 ? hor[i][j - 1] : 0);
                }
            }
        }
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[i].length - 1; j >= 0; j--) {
                int maxLength = Math.min(hor[i][j], ver[i][j]);
                while (maxLength > 1 && maxLength > longestEdge) {
                    int i1 = ver[i][j - maxLength + 1];
                    int i2 = hor[i - maxLength + 1][j];
                    int minEdge = Math.min(i1, i2);
                    if (minEdge >= maxLength) {
                        longestEdge = maxLength;
                        break;
                    } else {
                        //这里不要过于优化，而写成了maxLength = minEdge -1;
                        maxLength--;
                    }
                }
            }
        }
        return longestEdge * longestEdge;
    }

    @Test
    public void test_solution() {

        int[][] grid2 = {{1, 1, 0}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        Assert.assertEquals(9, largest1BorderedSquare(grid2));

        int[][] grid1 = {{1, 1, 0, 0}};
        Assert.assertEquals(1, largest1BorderedSquare(grid1));


        int[][] grid = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        Assert.assertEquals(9, largest1BorderedSquare(grid));
    }
}
