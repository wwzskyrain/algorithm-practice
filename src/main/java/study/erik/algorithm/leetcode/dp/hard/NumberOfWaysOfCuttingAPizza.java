/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumberOfWaysOfCuttingAPizza.java, v 0.1 2023年05月13日 17:54 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfWaysOfCuttingAPizza {

    @LetCodeCommit(title = "1444. Number of Ways of Cutting a Pizza",
            diff = "h",
            selfRemark = "这个题目就不写了，还是这一类dp：看着是二维，其实是一维的dp")
    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        Integer[][][] dp = new Integer[k][m][n];
        int[][] preSum = new int[m + 1][n + 1];

        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                preSum[r][c] = preSum[r][c + 1] + preSum[r + 1][c] - preSum[r + 1][c + 1] + (pizza[r].charAt(c) == 'A' ? 1 : 0);
            }
        }

        return dfs(m, n, k - 1, 0, 0, dp, preSum);
    }

    public int dfs(int m, int n, int k, int r, int c, Integer[][][] dp, int[][] preSum) {

        if (preSum[r][c] == 0) {return 0;}
        if (k == 0) {return 1;}
        if (dp[k][r][c] != null) {return dp[k][r][c];}
        int ans = 0;
        for (int nr = r + 1; nr < m; nr++) {
            if (preSum[r][c] - preSum[nr][c] > 0) {
                ans = (ans + dfs(m, n, k - 1, nr, c, dp, preSum)) % 1_000_000_007;
            }
        }

        for (int nc = c + 1; nc < n; nc++) {
            if (preSum[r][c] - preSum[r][nc] > 0) {
                ans = (ans + dfs(m, n, k - 1, r, nc, dp, preSum)) % 1_000_000_007;
            }
        }

        return dp[k][r][c] = ans;

    }

    @Parameter
    public String[] pizza;
    @Parameter(1)
    public int      k;
    @Parameter(2)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"A..", "AAA", "..."}, 3, 3},
                {new String[] {"A..", "AA.", "..."}, 3, 1},
                {new String[] {"A..", "A..", "..."}, 1, 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, ways(pizza, k));
    }

}