package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author erik.wang
 * @date 2020-03-09 15:41
 * @description
 */
public class WordSearchI {

    private static final Logger logger = LoggerFactory.getLogger(WordSearchI.class);


    public boolean exist(char[][] board, String word) {
        return solution1(board, word);
    }

    public enum Direction {
        RIGHT(0, 1),
        DOWN(1, 0),
        LEFT(0, -1),
        UP(-1, 0);

        private int x;
        private int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Direction[] directions() {
            return new Direction[]{RIGHT, DOWN, LEFT, UP};
        }

    }

    /**
     * @param board
     * @param word
     * @return
     */
    public boolean solution1(char[][] board, String word) {

        byte[][] visited = new byte[board.length][];
        for (int i = 0; i < board.length; i++) {
            visited[i] = new byte[board[i].length];
        }
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (resolve(board, visited, x, y, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 递归解法，非dfs的思想。
     * 成绩：29% 和 24%， 不满意
     * 这个问题，耽误了好一会时间，之前不想用visited二维数组，以为用direction时控制一下来向就可以，
     * 其实不行的。最后用了visited一下就好了。
     *
     * @param board
     * @param x
     * @param y
     * @param word
     * @return
     */
    public boolean resolve(char[][] board, byte[][] visited, int x, int y, String word) {
        if (word.length() == 1) {
            return board[x][y] == word.charAt(0);
        }
        char currentChar = word.charAt(0);
        if (board[x][y] == currentChar) {
            visited[x][y] = 1;
            for (Direction dir : Direction.directions()) {
                int dirX = x + dir.x;
                int dirY = y + dir.y;
                if (isInBoard(dirX, dirY, board) && visited[dirX][dirY] == 0) {
                    if (resolve(board, visited, dirX, dirY, word.substring(1))) {
                        return true;
                    }
                }
            }
            // 复原，这就是回溯法
            visited[x][y] = 0;
            return false;
        } else {
            return false;
        }
    }

    private boolean isInBoard(int x, int y, char[][] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[x].length;
    }


    @Test
    public void test_solution1() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
//        System.out.println(solution1(board, "ABCCED"));
//        System.out.println(solution1(board, "SEE"));
//        System.out.println(solution1(board, "ABCB"));
//
//        char[][] board1 = {{'a', 'a'}};
//        System.out.println(solution1(board1, "aaa"));

        char[][] board2 = {{'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}};
        System.out.println(solution1(board2, "aaaaaaaaaaaaa"));
    }

}
