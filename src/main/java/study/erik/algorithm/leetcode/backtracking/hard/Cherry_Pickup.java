package study.erik.algorithm.leetcode.backtracking.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/7 ，时间：11:49
 * 作者：yueyi
 * 描述：
 */

@RunWith(Parameterized.class)
@LetCodeCommit(title = "741. Cherry Pickup", selfRemark = "这个题目有个小陷阱，首先只是一来一回走两次，不是无数次——我审题有误。" + "其次，这个两次不能先后用一个简单的dp来做，因为不是最优。" + "这里还是用dp思想。")
public class Cherry_Pickup {


    /**
     * //  四个子问题：
     * //  （x1，y1）有两个来源，分别是（x1-1，y1），（x1，y1-1）
     * //  （x2，y2）也有两个来源，分别是（x2-1，y2），（x2，y2-1）
     * //  2*2，所以有四个子状态。
     * //  需要说明一下，因为是同步运动，所以p1变p2也必须变，比如p1left了，在p2必须left或者up相同的单位。
     *
     * @param grid
     * @return
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n + 1][n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        dp[1][1][1] = grid[0][0];
        for (int x1 = 1; x1 <= n; x1++) {
            for (int y1 = 1; y1 <= n; y1++) {
                for (int x2 = 1; x2 <= n; x2++) {
                    int y2 = x1 + y1 - x2;
                    if (dp[x1][y1][x2] > 0 || y2 < 1 || y2 > n || grid[x1 - 1][y1 - 1] == -1 || grid[x2 - 1][y2 - 1] == -1) {
                        continue;
                        // have already detected || out of boundary || cannot access
                    }
                    int cur = Math.max(Math.max(dp[x1 - 1][y1][x2], dp[x1 - 1][y1][x2 - 1]), Math.max(dp[x1][y1 - 1][x2], dp[x1][y1 - 1][x2 - 1]));
                    if (cur < 0) {
                        continue;
                    }
                    dp[x1][y1][x2] = cur + grid[x1 - 1][y1 - 1];
                    if (x1 != x2) {
                        dp[x1][y1][x2] += grid[x2 - 1][y2 - 1];
                    }
                }
            }
        }
        return dp[n][n][n] < 0 ? 0 : dp[n][n][n];
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {5, ArrayUtils.buildArray2Dimension("[[0,1,-1],[1,0,-1],[1,1,1]]")}, {0, ArrayUtils.buildArray2Dimension("[[1,1,-1],[1,-1,1],[-1,1,1]]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] grid;

    @Test
    public void test() {
        Assert.assertEquals(expect, cherryPickup(grid));
    }

}
