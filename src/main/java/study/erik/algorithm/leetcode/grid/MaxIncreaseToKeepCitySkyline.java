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

/**
 * @author yueyi
 * @version : MaxIncreaseToKeepCitySkyline.java, v 0.1 2022年05月18日 21:17 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaxIncreaseToKeepCitySkyline {

    @LetCodeCommit(title = "807. Max Increase to Keep City Skyline",
            diff = "m",
            selfRemark = "好没意思的一个题目")
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] iMax = new int[grid.length];
        int[] jMax = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                int h = grid[i][j];
                iMax[i] = Math.max(iMax[i], h);
                jMax[j] = Math.max(jMax[j], h);
            }
        }
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                total += Math.min(iMax[i], jMax[j]) - grid[i][j];
            }
        }
        return total;
    }

    @Parameter
    public int[][] grid;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]"), 35},
                {ArrayUtils.buildArray2Dimension("[[0,0,0],[0,0,0],[0,0,0]]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxIncreaseKeepingSkyline(grid));
    }
}

