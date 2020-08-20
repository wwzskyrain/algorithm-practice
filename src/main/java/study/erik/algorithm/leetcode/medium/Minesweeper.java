package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-20 08:25
 */
public class Minesweeper {

    @LetCodeCommit(title = "529. Minesweeper", time = 81, space = 64, diff = "m",
            selfRemark = "这种dfs的问题，我们已经处理的很好的；做题虽然花了40分钟，但是整个过程都是可控的，顺畅的；" +
                    "这个题目，在leetcode上的time和space都很差，所以我们看一下diss",
            related = {"Binary Tree Right Side View",
                    "Insufficient Nodes in Root to Leaf Paths"})
    public char[][] updateBoard(char[][] board, int[] click) {
        dfs(board, click);
        return board;
    }

    private int[][] direction = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public void dfs(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if (x < board.length && x >= 0 &&
                y < board[x].length && y >= 0) {
            if (board[x][y] != 'E' && board[x][y] != 'M') {
                return;
            }
            if (board[x][y] == 'M') {
                board[x][y] = 'X';
                return;
            }

            int mineCount = 0;
            for (int i = 0; i < direction.length; i++) {
                int x1 = x + direction[i][0];
                int y1 = y + direction[i][1];
                if (x1 >= 0 && x1 < board.length
                        && y1 >= 0 && y1 < board[x1].length
                        && board[x1][y1] == 'M') {
                    mineCount++;
                }
            }
            if (mineCount != 0) {
                board[x][y] = (char) ('0' + mineCount);
            } else {
                board[x][y] = 'B';
                for (int i = 0; i < direction.length; i++) {
                    int x1 = x + direction[i][0];
                    int y1 = y + direction[i][1];
                    dfs(board, new int[]{x1, y1});
                }
            }
        }
    }

    @Test
    public void test_solution() {
        char[][] expect = {
                {'B', '1', 'E', '1', 'B'},
                {'B', '1', 'M', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}};

        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}};
        int[] click = {3, 0};
        Assert.assertArrayEquals(expect, updateBoard(board, click));

    }

    @Test
    public void test_solution_2() {
        char[][] expect = {
                {'B', '1', 'E', '1', 'B'},
                {'B', '1', 'X', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}};

        char[][] board = {
                {'B', '1', 'E', '1', 'B'},
                {'B', '1', 'M', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}};

        int[] click = {1, 2};
        char[][] actual = updateBoard(board, click);
        Assert.assertArrayEquals(expect, actual);

    }

}
