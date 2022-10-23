/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author yueyi
 * @version : SnakesAndLadders.java, v 0.1 2022年10月23日 11:14 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SnakesAndLadders {

    @LetCodeCommit(title = "909. Snakes and Ladders",
            selfRemark = "这个题真让人狠，真辛苦；解法我早就知道，但是题意太细节，主要耗时在这个蛇形编号上了."
                    + "当然，我还尝试了优先级队列，无用，再也不用考虑了。")
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> p = new LinkedList<>();
        p.add(1);
        int step = 0;
        Set<Integer> v = new HashSet<>();
        while (!p.isEmpty()) {
            int size = p.size();
            step++;
            while (size > 0) {
                size--;
                Integer num = p.poll();
                if (v.contains(num)) {
                    continue;
                }
                for (int i = 1; i <= 6; i++) {
                    int nexNum = num + i;
                    int r = row(nexNum, n);
                    int c = col(nexNum, n);
                    if (r < 0 || c < 0 || r >= n || c >= n) {
                        continue;
                    }
                    nexNum = board[r][c] != -1 ? board[r][c] : nexNum;
                    if (nexNum >= n * n) {
                        return step;
                    }
                    p.add(nexNum);
                }
                v.add(num);
            }
        }
        return -1;
    }

    private int row(int num, int n) {
        int r = (num - 1) / n;
        return n - 1 - r;
    }

    private int col(int num, int n) {
        int r = (num - 1) / n;
        int m = (num - 1) % n;
        if (r % 2 == 0) {
            return m;
        } else {
            return n - 1 - m;
        }
    }

    @Parameter
    public int[][] board;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[-1,4,-1],[6,2,6],[-1,3,-1]]"), 2},
                {ArrayUtils.buildArray2Dimension("[[-1,-1,19,10,-1],[2,-1,-1,6,-1],[-1,17,-1,19,-1],[25,-1,20,-1,-1],[-1,-1,-1,-1,15]]"),
                        2},
                {ArrayUtils.buildArray2Dimension("[[1,1,-1],[1,1,1],[-1,1,1]]"), -1},
                {ArrayUtils.buildArray2Dimension("[[-1,7,-1],[-1,6,9],[-1,-1,2]]"),
                        1},
                {ArrayUtils.buildArray2Dimension(
                        "[[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,"
                                + "-1,-1,-1]]"),
                        4},
                {ArrayUtils.buildArray2Dimension(
                        "[[-1,-1],[-1,3]]"),
                        1},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, snakesAndLadders(board));
    }
}