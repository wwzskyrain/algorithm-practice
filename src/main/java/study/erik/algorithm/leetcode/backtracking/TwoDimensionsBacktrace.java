package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/06/02
 **/
public class TwoDimensionsBacktrace {


    @Test
    public void test_num_of_island() {

        char[][] grid = {{'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        Assert.assertEquals(1, numIslands(grid));
    }

    /**
     * description=求岛屿个数的问题是一个很经典的问题
     * leetCode https://leetcode.com/problems/number-of-islands/
     * 这篇文章中有很好的讲解，这里的代码也基本是抄写自它的。http://www.imooc.com/article/39641
     * 这里的解法并没有一个很明显的'试探形式-一进一退'，因为这个题的特性得知，这里不要回溯，只需要遍历，所以，
     * 我们只需要记录我们发起dfs的次数就行了。每发生一次就表示遍历完了一篇区域。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {

        int num = 0;
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


        boolean[][] visited = new boolean[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            visited[i] = new boolean[grid[i].length];
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(direction, grid, visited, i, j);
                    num++;
                }
            }
        }
        return num;

    }

    public void dfs(int[][] direction, char[][] grid, boolean[][] visited, int x, int y) {

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + direction[i][0];
            int newY = y + direction[i][1];

            if (newX >= 0 && newX < visited.length && newY >= 0 && newY < visited[newX].length
                    && !visited[newX][newY] && grid[newX][newY] == '1') {
                dfs(direction, grid, visited, newX, newY);
            }
        }
    }


    @Test
    public void test_exist() {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        Assert.assertTrue(exist(board, "ABCCED"));
        Assert.assertTrue(exist(board, "SEE"));
        Assert.assertFalse(exist(board, "ABCB"));


        char[][] board1 = new char[][]{
                {'A'}
        };

        Assert.assertFalse(exist(board1, "b"));
    }

    /**
     * 二维的回溯法，思想很简单；可惜这样写超时了。
     * leetCode: https://leetcode.com/problems/word-search/
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {

        char[][] visited = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            visited[i] = new char[board[i].length];
        }
        for (int i = 0; i < visited.length; i++) {

            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = '0';
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited[i][j] = '1';
                    if (doExist(board, visited, i, j, word, 1)) {
                        return true;
                    }
                    visited[i][j] = '0';
                }
            }
        }
        return false;
    }

    public boolean doExist(char[][] board, char[][] visited, int x, int y, String word, int level) {

        if (level == word.length()) {
            return true;
        }

        boolean left = false;
        boolean right = false;
        boolean up = false;
        boolean down = false;

        // TODO: 2019/7/1 这四个if分支可以用一个for循环简化
        if (valid(visited, x - 1, y) && board[x - 1][y] == word.charAt(level)) {
            visited[x - 1][y] = '1';
            down = doExist(board, visited, x - 1, y, word, level + 1);
            visited[x - 1][y] = '0';
        }

        if (valid(visited, x + 1, y) && board[x + 1][y] == word.charAt(level)) {
            visited[x + 1][y] = '1';
            up = doExist(board, visited, x + 1, y, word, level + 1);
            visited[x + 1][y] = '0';
        }

        if (valid(visited, x, y - 1) && board[x][y - 1] == word.charAt(level)) {
            visited[x][y - 1] = '1';
            left = doExist(board, visited, x, y - 1, word, level + 1);
            visited[x][y - 1] = '0';
        }

        if (valid(visited, x, y + 1) && board[x][y + 1] == word.charAt(level)) {
            visited[x][y + 1] = '1';
            right = doExist(board, visited, x, y + 1, word, level + 1);
            visited[x][y + 1] = '0';
        }
        return left || right || up || down;

    }

    public boolean valid(char[][] visited, int x, int y) {
        if (x < visited.length && x >= 0 && y < visited[x].length && y >= 0 && visited[x][y] == '0') {
            return true;
        }
        return false;
    }


}
