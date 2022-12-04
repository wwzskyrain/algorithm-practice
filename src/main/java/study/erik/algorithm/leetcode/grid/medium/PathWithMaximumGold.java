/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.medium;

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
 * @version : PathWithMaximumGold.java, v 0.1 2022年12月03日 21:05 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PathWithMaximumGold {

    @LetCodeCommit(title = "1219. Path with Maximum Gold",
            diff = "m",
            selfRemark = "这个题理论上是用backtrace完成，但是可以取巧借用dfs。"
                    + "1.另外，dfs一般就是先处理，再返回即可。"
                    + "2.这里dfs还有个好处就是返回值就是路径值。"
                    + "3.如果是backtrace，需要有一个外部变量接受当前的值，从而发现最大值，而不能指望backtrace完了返回最大值，因为backtrace完了之后，一切又归于原状了")
    public int getMaximumGold(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                max = Math.max(max, dfs(i, j, grid));
            }
        }
        return max;
    }

    int[][] dirs = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private int dfs(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] <= 0) {
            return 0;
        }
        grid[x][y] = -grid[x][y];
        int maxV = 0;
        for (int[] dir : dirs) {
            int xNext = x + dir[0];
            int yNext = y + dir[1];
            maxV = Math.max(maxV, dfs(xNext, yNext, grid));
        }
        grid[x][y] = -grid[x][y];
        return maxV + grid[x][y];
    }

    @Parameter
    public int[][] grid;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension(
                        "[[0,0,34,0,5,0,7,0,0,0],[0,0,0,0,21,0,0,0,0,0],[0,18,0,0,8,0,0,0,4,0],[0,0,0,0,0,0,0,0,0,0],[15,0,0,0,0,22,0,0,"
                                + "0,21],[0,0,0,0,0,0,0,0,0,0],[0,7,0,0,0,0,0,0,38,0]]"),
                        38},
                {ArrayUtils.buildArray2Dimension("[[0,6,0],[5,8,7],[0,9,0]]"), 24},
                {ArrayUtils.buildArray2Dimension("[[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]"), 28}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, getMaximumGold(grid));
    }
}