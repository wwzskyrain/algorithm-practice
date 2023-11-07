package study.erik.algorithm.job.zijie.simulation04;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/7 16:54
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        Integer[][][] memo = new Integer[m][n][maxMove + 1];
        return dfs(memo, startRow, startColumn, maxMove, m, n);
    }

    int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    int MOD = (int) 1e9 + 7;

    public int dfs(Integer[][][] memo, int x, int y, int maxMove, int m, int n) {
        if (memo[x][y][maxMove] != null) {
            return memo[x][y][maxMove];
        }
        long t = 0;
        if (maxMove == 0) {
            return memo[x][y][maxMove] = (int) t;
        }
        for (int[] dir : dirs) {
            int nextX = dir[0] + x;
            int nextY = dir[1] + y;
            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                t++;
            } else {
                t += dfs(memo, nextX, nextY, maxMove - 1, m, n);
            }
            t %= MOD;
        }
        return memo[x][y][maxMove] = (int) t;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {6, 2, 2, 2, 0, 0},
                {12, 1, 3, 3, 0, 1},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int m;
    @Parameterized.Parameter(2)
    public int n;
    @Parameterized.Parameter(3)
    public int maxMove;
    @Parameterized.Parameter(4)
    public int startRow;
    @Parameterized.Parameter(5)
    public int startColumn;

    @Test
    public void test() {
        Assert.assertEquals(expect, findPaths(m, n, maxMove, startRow, startColumn));
    }

}
