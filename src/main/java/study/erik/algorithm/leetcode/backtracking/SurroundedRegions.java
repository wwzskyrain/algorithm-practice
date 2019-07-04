package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2019/06/02
 **/
public class SurroundedRegions {

    @Test
    public void test_solve() {
//        char[][] board = {
//                {'X', 'X', 'X', 'X'},
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'X'}};
//
//        solve(board);
//        System.out.println(Arrays.deepToString(board));

        char[][] board1 = {{'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}};

        solve(board1);
        System.out.println(Arrays.deepToString(board1));
    }

    /**
     * leetCode https://leetcode.com/problems/surrounded-regions/
     * 被包围的要消灭。这个题目其实是个图的遍历问题。
     * @param board
     */
    public void solve(char[][] board) {

        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                //从四条边中的'O'开始遍历，与这些'O'联通的'O'都是要保留的，而联通不到的'O'都是要被'围困而死'的。
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) {
                    if (board[i][j] == 'O') bfs(direction, board, i, j);
                }
            }
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == '$') board[i][j] = 'O';
            }
        }

    }

    public void bfs(int[][] direction, char[][] board, int x, int y) {

        board[x][y] = '$';
        for (int i = 0; i < direction.length; i++) {
            int newX = x + direction[i][0];
            int newY = y + direction[i][1];

            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[newX].length
                    && board[newX][newY] == 'O') {
                bfs(direction, board, newX, newY);
            }
        }
    }


}
