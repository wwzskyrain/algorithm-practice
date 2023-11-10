package study.erik.algorithm.job.zijie.simulation07;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.nowcoder.zijie.找零钱问题;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/8 18:44
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int n = hits.length;
        int[] ret = new int[n];
        for (int i = 0; i < hits.length; i++) {
            int[] h = hits[i];
            if (grid[h[0]][h[1]] == 1) {
                grid[h[0]][h[1]] = 0;
                ret[i] = getNum(grid, h[0], h[1]);
            }
        }
        return ret;
    }

    int[][] dir2 = new int[][]{{1, 0}, {0, -1}, {0, 1},};

    public int getNum(int[][] grid, int x, int y) {
        int total = 0;
        for (int[] ints : dir2) {
            int nextX = ints[0] + x;
            int nextY = ints[1] + y;
            if (nextX < grid.length && nextY >= 0 && nextY < grid[nextX].length && grid[nextX][nextY] == 1) {
                boolean[][] visited = new boolean[grid.length][grid[0].length];
                if (!isSold(grid, nextX, nextY, visited)) {
                    total++;
                    grid[nextX][nextY] = 0;
                    total += getNum(grid, nextX, nextY);
                }
            }
        }
        return total;
    }

    public boolean isSold(int[][] grid, int x, int y, boolean[][] visited) {
        if (grid[x][y] != 1) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        if (visited[x][y]) {
            return false;
        }
        visited[x][y] = true;
        if (y + 1 < grid[x].length && isSold(grid, x, y + 1, visited)) {
            return true;
        }
        if (y - 1 >= 0 && isSold(grid, x, y - 1, visited)) {
            return true;
        }
        if (isSold(grid, x - 1, y, visited)) {
            return true;
        }
        return false;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray("[2]"),
                        ArrayUtils.buildArray2Dimension("[[1,0,0,0],[1,1,1,0]]"),
                        ArrayUtils.buildArray2Dimension("[[1,0]]")},
                {ArrayUtils.buildArray("[0,0]"),
                        ArrayUtils.buildArray2Dimension("[[1,0,0,0],[1,1,0,0]]"),
                        ArrayUtils.buildArray2Dimension("[[1,1],[1,0]]")},
        });
    }

    @Parameterized.Parameter
    public int[] expect;
    @Parameterized.Parameter(1)
    public int[][] grid;
    @Parameterized.Parameter(2)
    public int[][] hits;


    @Test
    public void test() {
        Assert.assertArrayEquals(expect, hitBricks(grid, hits));
    }

}
