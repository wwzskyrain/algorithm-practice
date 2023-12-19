package study.erik.algorithm.leetcode.grid.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/18 15:01
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Valid_Sudoku {

    @LetCodeCommit(title = "36. Valid Sudoku")
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (!add(i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private Set<String> set = new HashSet<>();

    public boolean add(int i, int j, char c) {
        String rowKey = "row-" + i + "-" + c;
        if (set.contains(rowKey)) {
            return false;
        }
        set.add(rowKey);

        String columnKey = "column-" + j + "-" + c;
        if (set.contains(columnKey)) {
            return false;
        }
        set.add(columnKey);

        String squareKey = "square-" + (i / 3) + "-" + (j / 3) + "-" + c;
        if (set.contains(squareKey)) {
            return false;
        }
        set.add(squareKey);
        return true;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {true, ArrayUtils.buildCharArr2Dimension("[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"]\n" +
                        ",[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"]\n" +
                        ",[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"]\n" +
                        ",[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"]\n" +
                        ",[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"]\n" +
                        ",[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"]\n" +
                        ",[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"]\n" +
                        ",[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"]\n" +
                        ",[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]")},
                {false, ArrayUtils.buildCharArr2Dimension("[[\"8\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"]\n" +
                        ",[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"]\n" +
                        ",[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"]\n" +
                        ",[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"]\n" +
                        ",[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"]\n" +
                        ",[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"]\n" +
                        ",[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"]\n" +
                        ",[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"]\n" +
                        ",[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]")}
        });
    }

    @Parameterized.Parameter
    public boolean expect;
    @Parameterized.Parameter(1)
    public char[][] board;


    @Test
    public void test() {
        Assert.assertEquals(expect, isValidSudoku(board));
    }

}
