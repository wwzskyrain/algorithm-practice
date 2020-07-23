package study.erik.algorithm.leetcode.backtracking;

/**
 * @author erik.wang
 * @date 2019/06/01
 **/
public class Sudoku {

    public void solveSudoku(char[][] board) {

        if (board == null || board.length == 0)
            return;
        searchRes(board);

    }

    private boolean searchRes(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char num = '1'; num <= '9'; num++) {
                    //尝试
                    if (!isValid(board, i, j, num)) {
                        continue;
                    }
                    board[i][j] = num;
                    if (searchRes(board)) {
                        return true;
                    } else {
                        board[i][j] = '.';
                        //回退之前的状态,本轮搜索失败,回退的时候也是递归的
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int i, int j, char num) {
        // 行有相同的,说明尝试错误.
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == num) {
                return false;
            }
        }
        // 列有相同的,说明尝试错误.
        for (int col = 0; col < 9; col++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // 9宫格中有相同的,说明尝试错误.
        for (int row = i / 3 * 3; row < i / 3 * 3 + 3; row++) {
            for (int col = j / 3 * 3; col < j / 3 * 3 + 3; col++) {
                if (board[row][col] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 利用search函数；难道search的解法不能解答所有的数据问题吗？为什么结果不对。
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {

        if (board == null || board.length == 0)
            return false;
        return searchRes(board);

    }

}
