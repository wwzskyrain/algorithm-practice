package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-03-13 11:00
 * @description
 */
public class MinimumPathSum {

    /**
     * title = Minimum Path Sum
     * url = https://leetcode.com/problems/minimum-path-sum/
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        return solution1(grid);
    }

    /*
    这个题目很简单了；
    sum[i,j] = min{ sum[i-1,j], sum[i,j-1] } + grid[i,j]
    很欣慰：代码一蹴而就，毫无修饰。提交一遍通过，成绩87%和75.
    todo 后续题目：
        1.https://leetcode.com/problems/dungeon-game/
        2.https://leetcode.com/problems/cherry-pickup/
     */
    public int solution1(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int[][] sum = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            sum[i] = new int[grid[i].length];
        }

        sum[0][0] = grid[0][0];
        for (int i = 1; i < sum[0].length; i++) {
            sum[0][i] = grid[0][i] + sum[0][i - 1];
        }

        for (int i = 1; i < grid.length; i++) {
            sum[i][0] = grid[i][0] + sum[i - 1][0];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[i].length; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }
        return sum[sum.length - 1][sum[sum.length - 1].length - 1];
    }

    @LetCodeCommit(title = "64. Minimum Path Sum",
            selfRemark = "完全手写的")
    public int solution2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    @Test
    public void test_solution() {
        int[][] grid = {{1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};

        Assert.assertEquals(7, solution1(grid));
    }

}
