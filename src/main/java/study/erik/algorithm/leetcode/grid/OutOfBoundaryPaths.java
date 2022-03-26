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
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Map;

/**
 * @author yueyi
 * @version : OutOfBoundaryPaths.java, v 0.1 2022年03月26日 9:53 上午 yueyi Exp $
 */
@LetCodeCommit(title = "576. Out of Boundary Paths",
        diff = "m",
        selfRemark = "用了两种方法写。")
@RunWith(Parameterized.class)
public class OutOfBoundaryPaths {

    private              int[][] directions = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int     MOD        = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {

        if (maxMove <= 0) {
            return 0;
        }
        int[][][] dp = new int[maxMove][m][n];
        // dp[i][j][k]表示从[j][k]处走i步出格格的路径.
        // ret = sum{dp[i][0][0]},(i=0,...,maxMove-1)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] direction : directions) {
                    int x = i + direction[0];
                    int y = j + direction[1];
                    if (x < 0 || y < 0 || x >= m || y >= n) {
                        dp[0][i][j]++;
                    }
                }
            }
        }
        for (int i = 1; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int c = 0;
                    for (int[] direction : directions) {
                        int x = j + direction[0];
                        int y = k + direction[1];
                        if (x < 0 || y < 0 || x >= m || y >= n) {
                            continue;
                        }
                        c = (c + dp[i - 1][x][y]) % MOD;
                    }
                    dp[i][j][k] = c;
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < maxMove; i++) {
            ret = (ret + dp[i][startRow][startColumn]) % MOD;
        }
        return ret;
    }

    /**
     * 递归加备忘录法，虽然ac了，但是战绩好惨呀， 5.15% 5.15%
     *
     * @param m
     * @param n
     * @param maxMove
     * @param startRow
     * @param startColumn
     * @param map
     * @return
     */
    private int solveWithRecursive(int m, int n, int maxMove, int startRow, int startColumn, Map<String, Integer> map) {
        int c = 0;
        if (maxMove == 0) {
            return c;
        }
        String key = new StringBuilder().append(startRow).append("-")
                .append(startColumn).append("-")
                .append(maxMove).toString();
        Integer ret = map.get(key);
        if (ret != null) {
            return ret;
        }

        for (int[] direction : directions) {
            int x = startRow + direction[0];
            int y = startColumn + direction[1];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                c++;
            } else {
                c = (c + solveWithRecursive(m, n, maxMove - 1, x, y, map)) % MOD;
            }
        }
        map.put(key, c);
        return c;
    }

    @Parameter
    public int m;
    @Parameter(1)
    public int n;
    @Parameter(2)
    public int maxMove;
    @Parameter(3)
    public int startRow;
    @Parameter(4)
    public int startColumn;
    @Parameter(5)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {2, 2, 2, 0, 0, 6},
                {1, 3, 3, 0, 1, 12},
                {8, 7, 16, 1, 5, 102984580},
                {10, 10, 0, 5, 5, 0}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findPaths(m, n, maxMove, startRow, startColumn));
    }
}