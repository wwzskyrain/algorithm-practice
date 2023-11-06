package study.erik.algorithm.job.zijie.simulation02;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/6 08:35
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public int shortestBridge(int[][] grid) {
        findFirstI(grid);
        Queue<int[]> q = new LinkedList<>();
        int t = 0;
        q.addAll(firstI);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] poll = q.poll();
                int i = poll[0];
                int j = poll[1];
                for (int[] dir : dirs) {
                    int nextI = dir[0] + i;
                    int nextJ = dir[1] + j;
                    if (nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid[nextI].length) {
                        if (grid[nextI][nextJ] == 0) {
                            grid[nextI][nextJ] = -2;
                            q.add(new int[]{nextI, nextJ});
                        } else if (grid[nextI][nextJ] == 1) {
                            return t;
                        }
                    }
                }
            }
            t++;
        }
        return -1;
    }

    List<int[]> firstI = new ArrayList<>();
    int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public void findFirstI(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    return;
                }
            }
        }
    }

    public void dfs(int[][] grid, int i, int j) {
        if (grid[i][j] == 1) {
            firstI.add(new int[]{i, j});
            grid[i][j] = -1;
            for (int[] dir : dirs) {
                int nextI = dir[0] + i;
                int nextJ = dir[1] + j;
                if (nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid[nextI].length) {
                    dfs(grid, nextI, nextJ);
                }
            }
        }
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {1, ArrayUtils.buildArray2Dimension("[[0,1],[1,0]]")},
                {2, ArrayUtils.buildArray2Dimension("[[0,1,0],[0,0,0],[0,0,1]]")},
                {1, ArrayUtils.buildArray2Dimension("[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] grid;

    @Test
    public void test() {
        Assert.assertEquals(expect, shortestBridge(grid));
    }

}
