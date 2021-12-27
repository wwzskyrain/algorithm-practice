/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : GameOfLife.java, v 0.1 2021年12月26日 8:28 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class GameOfLife {

    @LetCodeCommit(title = "289. Game of Life",
            selfRemark = "难点在于在原数组board上操作。"
                    + "所以这里考虑用中间状态来做")
    public void gameOfLife(int[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int c = oneCount(board, i, j);
                if (board[i][j] == 0) {
                    if (c == 3) {
                        board[i][j] = zoneToOne;
                    }
                } else {
                    if (c < 2 || c > 3) {
                        board[i][j] = oneToZone;
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int n = board[i][j];
                if (n == oneToZone) {
                    board[i][j] = 0;
                } else if (n == zoneToOne) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private int[][] directions = new int[][] {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };
    int zoneToOne = 2;
    int oneToZone = -1;

    private int oneCount(int[][] board, int i, int j) {
        int c = 0;
        for (int[] direction : directions) {
            int x = direction[0];
            int y = direction[1];
            if (x + i >= 0 && x + i < board.length
                    && y + j >= 0 && y + j < board[x + i].length
                    && (board[x + i][y + j] == 1 || board[x + i][y + j] == oneToZone)) {
                c++;
            }
        }
        return c;
    }

    @Parameter
    public int[][] board;
    @Parameter(1)
    public int[][] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]"),
                        ArrayUtils.buildArray2Dimension("[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]")},

                {ArrayUtils.buildArray2Dimension("[[1,1],[1,0]]"),
                        ArrayUtils.buildArray2Dimension("[[1,1],[1,1]]")},
        };
    }

    @Test
    public void test_() {
        gameOfLife(board);
        Assert.assertArrayEquals(expect, board);
    }

    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String key = s.charAt(i) + "";
            String w = map.get(key);
            if (w == null) {
                map.put(key, words[i]);
            } else {
                if (!w.equals(words[i])) {
                    return false;
                }
            }
        }
        return true;
    }



}