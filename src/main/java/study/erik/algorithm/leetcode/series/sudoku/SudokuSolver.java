/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.sudoku;

import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : SudokuSolver.java, v 0.1 2021年08月21日 11:41 下午 yueyi Exp $
 */
public class SudokuSolver {

    @LetCodeCommit(title = "Sudoku Solver",
            selfRemark = "解数独，哈哈，我早就自己试验过，还尝试着用动画把他算出来呢。")
    public void solveSudoku(char[][] board) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                count += (board[i][j] == '.' ? 1 : 0);
            }
        }
        int x = 0, y = 0;
        do {
            x = x + (y + 1) / 9;
            y = (y + 1) % 9;
        } while (board[x][y] != '.');
        dfs(x, y, count, board);
    }

    /**
     * 解法很简单，就是标准的回溯法。
     * 注意1：因为要找到一个解，所以不能简单的做完全便利，需要在找到解之后，
     * 返回true来即使输出该解并制止这一步回溯，因为一回溯，这个解就又被复原了
     *
     * @param x
     * @param y
     * @param count
     * @param board
     * @return
     */
    private boolean dfs(int x, int y, int count, char[][] board) {
        for (char i = '1'; i <= '9'; i++) {
            if (checkRow(board, x, i) &&
                    checkColumn(board, y, i) &&
                    checkBox(board, x, y, i)) {
                board[x][y] = i;
                if (count == 1) {
                    return true;
                }
                int xNext = x;
                int yNext = y;
                do {
                    xNext = (xNext + (yNext + 1) / 9) % 9;
                    yNext = (yNext + 1) % 9;
                } while (board[xNext][yNext] != '.');
                if (dfs(xNext, yNext, count - 1, board)) {
                    return true;
                }
                board[x][y] = '.';
            }
        }
        return false;
    }

    private boolean checkRow(char[][] board, int x, char v) {
        for (char c : board[x]) {
            if (c == v) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(char[][] board, int y, char v) {
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == v) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBox(char[][] board, int x, int y, char v) {
        int x1 = (x / 3) * 3;
        int y1 = (y / 3) * 3;
        for (int i = x1; i < x1 + 3; i++) {
            for (int j = y1; j < y1 + 3; j++) {
                if (board[i][j] == v) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test_1() {
        String boardStr =
                "[[\".\",\".\",\"9\",\"7\",\"4\",\"8\",\".\",\".\",\".\"],[\"7\",\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\"],[\".\","
                        + "\"2\",\".\",\"1\",\".\",\"9\",\".\",\".\",\".\"],[\".\",\".\",\"7\",\".\",\".\",\".\",\"2\",\"4\",\".\"],[\""
                        + ".\",\"6\",\"4\",\".\",\"1\",\".\",\"5\",\"9\",\".\"],[\".\",\"9\",\"8\",\".\",\".\",\".\",\"3\",\".\",\".\"],"
                        + "[\".\",\".\",\".\",\"8\",\".\",\"3\",\".\",\"2\",\".\"],[\".\",\".\",\".\",\".\",\".\",\".\",\".\",\".\","
                        + "\"6\"],[\".\",\".\",\".\",\"2\",\"7\",\"5\",\"9\",\".\",\".\"]]";

        char[][] board = ArrayUtils.buildArray2DimensionToChar(boardStr);
        System.out.println(Arrays.deepToString(board));
        solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }

}