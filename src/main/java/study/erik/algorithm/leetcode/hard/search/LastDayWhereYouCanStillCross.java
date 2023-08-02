package study.erik.algorithm.leetcode.hard.search;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LastDayWhereYouCanStillCross {

    @LetCodeCommit(title = "1970. Last Day Where You Can Still Cross")
    public int latestDayToCross(int row, int col, int[][] cells) {
        int n = cells.length;
        int low = 1;
        int high = n;
        while (low < high) {
            int mid = high - (high - low) / 2;
            if (canCross(row, col, mid, cells)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public boolean canCross(int row, int col, int day, int[][] cells) {
        int[][] grid = new int[row][col];
        for (int i = 0; i < day; i++) {
            grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
        }
        for (int i = 0; i < col; i++) {
            if (grid[0][i]==0 && dfs(grid, 0, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 0) {
            // 越界了 or grid[row][col]不是陆地
            return false;
        }
        if (row == grid.length - 1) {
            return true;
        }
        grid[row][col] = -1;
        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];
            if (dfs(grid, nextRow, nextCol)) {
                return true;
            }
        }
        return false;
    }

    @Parameterized.Parameter
    public int row;
    @Parameterized.Parameter(1)
    public int col;
    @Parameterized.Parameter(2)
    public int[][] cells;
    @Parameterized.Parameter(3)
    public int expect;

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, 2, ArrayUtils.buildArray2Dimension("[[1,1],[2,1],[1,2],[2,2]]"), 2},
                {2, 2, ArrayUtils.buildArray2Dimension("[[1,1],[1,2],[2,1],[2,2]]"), 1},
                {3, 3, ArrayUtils.buildArray2Dimension("[[1,2],[2,1],[3,3],[2,2],[1,1],[1,3]"), 3},

        });
    }

    @Test
    public void test() {
        Assert.assertEquals(expect, latestDayToCross(row, col, cells));
    }

}
