package study.erik.algorithm.leetcode.backtracking.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/28 09:08
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1349. Maximum Students Taking Exam")
public class Maximum_Students_Taking_Exam {

    Map<Integer, Integer> memo = new HashMap<>();

    //  这个题目，backtrace解法很自然，但是要AC，就要做状态压缩。
    //  单纯的状态压缩。
    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        int mx = 0;
        for (int i = 0; i < 1 << n; i++) {
            mx = Math.max(mx, dp(seats, m - 1, i));
        }
        return mx;
    }

    public int dp(char[][] seats, int row, int status) {
        int n = seats[0].length;
        int key = (row << n) + status;
        if (!memo.containsKey(key)) {
            if (!isSingleRowCompliant(seats, status, n, row)) {
                //先做本行检查
                memo.put(key, Integer.MIN_VALUE);
                return Integer.MIN_VALUE;
            }
            int students = Integer.bitCount(status);
            if (row == 0) {
                //递归出口
                memo.put(key, students);
                return students;
            }
            int mx = 0;
            for (int upperRowStatus = 0; upperRowStatus < 1 << n; upperRowStatus++) {
                if (isCrossRowsCompliant(status, upperRowStatus, n)) {
                    //再做跨行检查
                    mx = Math.max(mx, dp(seats, row - 1, upperRowStatus));
                }
            }
            memo.put(key, students + mx);
        }
        return memo.get(key);
    }

    public boolean isSingleRowCompliant(char[][] seats, int status, int n, int row) {
        for (int j = 0; j < n; j++) {
            if (((status >> j) & 1) == 1) {
                if (seats[row][j] == '#') {
                    return false;
                }
                if (j > 0 && ((status >> (j - 1)) & 1) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCrossRowsCompliant(int status, int upperRowStatus, int n) {
        for (int j = 0; j < n; j++) {
            if (((status >> j) & 1) == 1) {
                if (j > 0 && ((upperRowStatus >> (j - 1)) & 1) == 1) {
                    return false;
                }
                if (j < n - 1 && ((upperRowStatus >> (j + 1)) & 1) == 1) {
                    return false;
                }
            }
        }
        return true;
    }


    @Parameterized.Parameter
    public char[][] seats;
    @Parameterized.Parameter(1)
    public int expect;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {ArrayUtils.buildArray2DimensionToChar("[[\"#\",\".\",\"#\",\"#\",\".\",\"#\"],[\".\",\"#\",\"#\",\"#\",\"#\",\".\"],"
                        + "[\"#\",\".\",\"#\",\"#\",\".\",\"#\"]]"), 4},
                {ArrayUtils.buildArray2DimensionToChar("[[\".\",\"#\"],[\"#\",\"#\"],[\"#\",\".\"],[\"#\",\"#\"],[\".\",\"#\"]]"), 3},
                {ArrayUtils.buildArray2DimensionToChar("[[\"#\",\".\",\".\",\".\",\"#\"],[\".\",\"#\",\".\",\"#\",\".\"],[\".\",\".\","
                        + "\"#\",\".\",\".\"],[\".\",\"#\",\".\",\"#\",\".\"],[\"#\",\".\",\".\",\".\",\"#\"]]"), 10},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxStudents(seats));
    }

}
