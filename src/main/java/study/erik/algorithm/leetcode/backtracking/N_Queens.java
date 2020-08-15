package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-15 12:55
 */
public class N_Queens {

    @LetCodeCommit(no = 51, title = "N-Queens", time = 96, space = 80,
            selfRemark = "一遍过，看来这个问题我们很熟悉了; 空间可以再压缩，当前是O(n*n), 可以压缩到O(n)")
    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];
        List<List<String>> solutions = new ArrayList<>();
        buildBoard(board, 0, solutions);
        return solutions;
    }

    public void buildBoard(char[][] board, int n, List<List<String>> solutions) {
        int N = board.length;
        if (n == N) {
            List<String> solution = new ArrayList<>();
            for (char[] chars : board) {
                //初始化时，char = 0；
                StringBuilder sb = new StringBuilder();
                for (char c : chars) {
                    sb.append(c == 0 ? '.' : c);
                }
                solution.add(sb.toString());
            }
            solutions.add(solution);
            return;
        }

        for (int j = 0; j < board[n].length; j++) {
            boolean settled = false;
            for (int i = 0; i < n; i++) {
                if (board[i][j] == 'Q') {
                    settled = true;
                    break;
                }
            }
            if (settled) {
                continue;
            }

            for (int i = 1; i <= n; i++) {
                if ((j + i) < board[n - i].length && board[n - i][j + i] == 'Q') {
                    settled = true;
                    break;
                }
                if ((j - i) >= 0 && board[n - i][j - i] == 'Q') {
                    settled = true;
                    break;
                }
            }
            if (settled) {
                continue;
            }

//          回溯
            board[n][j] = 'Q';
            buildBoard(board, n + 1, solutions);
            board[n][j] = '.';
        }
    }

    @Test
    public void test_solution_1() {
        List<List<String>> lists = solveNQueens(4);
        for (List<String> list : lists) {
            System.out.println("-----------");
            for (String s : list) {
                System.out.println(s);
            }
        }
    }


}
