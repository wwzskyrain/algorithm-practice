package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import static study.erik.algorithm.util.QuestionType.Back_Tracking;


/**
 * @author erik.wang
 * @date 2020-08-15 13:20
 */
public class N_Queens_II {

    @LetCodeCommit(no = 52, title = "N-Queens II", time = 86, space = 77,
            selfRemark = "和N_Queens一样的，前者是求解，后者求解的个数；我也是稍稍改一下就完成了的",
            types = Back_Tracking,
            related = {"Palindrome Partitioning","Binary Watch", })
    public int totalNQueens(int n) {

        char[][] board = new char[n][n];
        return buildBoard(board, 0);

    }

    public int buildBoard(char[][] board, int n) {
        int N = board.length;
        if (n == N) {
            return 1;
        }
        int count = 0;
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
            count += buildBoard(board, n + 1);
            board[n][j] = '.';
        }
        return count;
    }

    @Test
    public void test_solution_1() {
        Assert.assertEquals(2, totalNQueens(4));
    }
}
