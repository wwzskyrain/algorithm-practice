/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.backtracking.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumStudentsTakingExam.java, v 0.1 2023年05月02日 12:02 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumStudentsTakingExam {

    @LetCodeCommit(title = "1349. Maximum Students Taking Exam",
            selfRemark = "这个题目是一个很backtracking的题目。"
                    + "但是这样写明显超时了。原因很明显，一维回溯，行和列最大是8，所以8*8=64了。"
                    + "思路是把每一行作为一个状态来回溯，这样大概就8行，每行也就2的8次方的变化。"
                    + "具体可以看这个代码：https://leetcode.com/problems/maximum-students-taking-exam/solutions/503491/java-top-down-dp-solution/"
                    + "它是一个自顶向下的思路。效率高了很多的。"
                    + "还有一个自底向上的解法，https://leetcode.com/problems/maximum-students-taking-exam/solutions/503686/a-simple-tutorial-on-this-bitmasking-problem/"
                    + "其思路也是差不多的。")
    public int maxStudents(char[][] seats) {
        int[][] cheat = new int[][] {{-1, -1}, {0, -1}, {-1, 1}, {0, 1}};
        return backtracking(seats, 0, 0, cheat);
    }

    public int backtracking(char[][] seats, int x, int y, int[][] cheat) {

        int n = seats.length;
        int m = seats[0].length;
        int position = x * m + y;
        if (position == n * m) {
            return 0;
        }
        position++;
        int nextX = position / m;
        int nextY = position % m;
        if (seats[x][y] == '#') {
            return backtracking(seats, nextX, nextY, cheat);
        } else {
            boolean canSeat = true;
            for (int[] dir : cheat) {
                int cheatX = x + dir[0];
                int cheatY = y + dir[1];
                if (cheatX >= 0 && cheatX < n && cheatY >= 0 && cheatY < m && seats[cheatX][cheatY] == 'S') {
                    canSeat = false;
                    break;
                }
            }
            if (!canSeat) {
                return backtracking(seats, nextX, nextY, cheat);
            } else {
                int ret = backtracking(seats, nextX, nextY, cheat);
                seats[x][y] = 'S';
                ret = Math.max(ret, backtracking(seats, nextX, nextY, cheat) + 1);
                seats[x][y] = '.';
                return ret;
            }
        }
    }

    @Parameter
    public char[][] seats;
    @Parameter(1)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
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