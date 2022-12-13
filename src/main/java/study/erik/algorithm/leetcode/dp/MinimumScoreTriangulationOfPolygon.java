/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumScoreTriangulationOfPolygon.java, v 0.1 2022年12月12日 21:47 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumScoreTriangulationOfPolygon {

    @LetCodeCommit(title = "1039. Minimum Score Triangulation of Polygon",
            diff = "这个题目不赖，是一个双边dp")
    public int minScoreTriangulation(int[] values) {

        int n = values.length;
        int[][] dp = new int[n][n];
        // dp[i][j] = v[i::j]的解
        // dp[i][j] = dp[i][k] + dp[k][j] +i*k*j
        for (int l = 2; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                for (int j = i + l; j < n; j++) {
                    int min = Integer.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n - 1];
    }

    @Parameter
    public int[] values;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3]"), 6},
                {ArrayUtils.buildArray("[3,7,4,5]"), 144},
                {ArrayUtils.buildArray("[1,3,1,4,1,5]"), 13},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minScoreTriangulation(values));
    }
}