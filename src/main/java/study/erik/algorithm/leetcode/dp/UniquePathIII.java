package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-10 23:30
 * @description
 */
public class UniquePathIII {

    /**
     * title = unique path III
     * title = https://leetcode.com/problems/unique-paths-iii/
     *
     * @param grid
     * @return
     */
    public int uniquePathsIII(int[][] grid) {
        return solution(grid);
    }

    /*
    这个问题，应该是dfs遍历穷举法了。
    是的，前序遍历就够了；
    有意思的是，我这里用了成员变量来计数。
    当执行Test时，第二个solution就没有把count重置，所以多了两2.
    昨晚想了好久，没明白。早上再看，突然就想明白了。
    更有意思的是，我这样提交了，leetcode 上的验证代码竟然没有问题。
     */
    public int solution(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = new boolean[grid[i].length];
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    x = i;
                    y = j;
                }
            }
        }
        dfs(grid, visited, x, y);
        return count;
    }

    private int count = 0;

    private void dfs(int[][] grid, boolean[][] visited, int x, int y) {
        if (grid[x][y] == 2) {
            if (allVisited(visited, grid)) {
                count++;
            }
            return;
        }
        visited[x][y] = true;
        byte[][] directions = new byte[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (byte[] direction : directions) {
            int X = x + direction[0];
            int Y = y + direction[1];
            if (inGrid(X, Y, grid) && !visited[X][Y] && grid[X][Y] != -1) {
                dfs(grid, visited, X, Y);
            }
        }
        visited[x][y] = false;
    }

    private boolean inGrid(int x, int y, int[][] board) {
        return x >= 0 && y >= 0 && x < board.length && y < board[x].length;
    }

    private boolean allVisited(boolean[][] visited, int[][] grid) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test_solution() {
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        Assert.assertEquals(2, solution(grid));


        count = 0; // 记得重置count
        int[][] grid1 = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        Assert.assertEquals(4, solution(grid1));
    }
}
