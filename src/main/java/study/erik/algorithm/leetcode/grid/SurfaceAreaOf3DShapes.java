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
 * @version : SurfaceAreaOf3DShapes.java, v 0.1 2022年05月30日 21:08 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SurfaceAreaOf3DShapes {

    @LetCodeCommit(title = "892. Surface Area of 3D Shapes")
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int subSum = 0;
            int pre = 0;
            for (int j = 0; j < n; j++) {
                int h = grid[i][j];
                if (h > 0) {
                    sum += 2;
                }
                if (h >= pre) {
                    subSum += (grid[i][j] - pre);
                }
                pre = grid[i][j];
            }
            sum += subSum * 2;
        }
        for (int j = 0; j < n; j++) {
            int subSum = 0;
            int pre = 0;
            for (int i = 0; i < n; i++) {
                if (grid[i][j] >= pre) {
                    subSum += grid[i][j] - pre;
                }
                pre = grid[i][j];
            }
            sum += subSum * 2;
        }

        return sum;
    }

    @Parameter
    public int[][] grid;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2],[3,4]]"), 34},
                {ArrayUtils.buildArray2Dimension("[[1,1,1],[1,0,1],[1,1,1]]"), 32},
                {ArrayUtils.buildArray2Dimension("[[2,2,2],[2,1,2],[2,2,2]]"), 46},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, surfaceArea(grid));
    }

}