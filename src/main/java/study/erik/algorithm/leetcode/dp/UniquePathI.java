package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-10 10:27
 * @description
 */
public class UniquePathI {

    /**
     * title = Unique Paths
     * url = https://leetcode.com/problems/unique-paths/
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        return solution1(m, n);
    }

    /*
    成绩：100% 和 5. 空间太水了。我们改一下
    奇怪，我们把m行降到了2行，空间复杂度还是一样的5%，我不太相信的，不过也没有办法
     */
    public int solution1(int m, int n) {
        int[][] dp = new int[2][n];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[n];
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            int level = i % 2;
            dp[level][0] = 1;
            for (int j = 1; j < n; j++) {
                dp[level][j] = dp[(level + 1) % 2][j] + dp[level][j - 1];
            }
        }
        return dp[(m - 1) % 2][n - 1];
    }

    public int dp(int column, int row, int[][] board) {

        int[][] m = new int[row][column];

        for (int i = 0; i < m.length; i++) {
            m[i][0] = 1;
        }

        for (int i = 0; i < m[0].length; i++) {
            m[0][i] = 1;
        }

        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                m[i][j] = m[i - 1][j] * (board[i - 1][j] == 1 ? 0 : 1) + m[i][j - 1] * (board[i][j - 1] == 1 ? 0 : 1);
            }
        }

        return m[row - 1][column - 1];
    }

    @Test
    public void test_solution() {
        int column = 7;
        int row = 3;
//        Assert.assertEquals(28, dp(column, row));
//        Assert.assertEquals(3, solutionForHouseRobber1(3, 2));
//        Assert.assertEquals(28, solutionForHouseRobber1(7, 3));
    }


}
