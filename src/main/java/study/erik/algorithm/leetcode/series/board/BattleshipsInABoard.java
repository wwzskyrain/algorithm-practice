/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.board;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BattleshipsInABoard.java, v 0.1 2022年02月21日 8:59 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BattleshipsInABoard {

    @LetCodeCommit(title = "419. Battleships in a Board",
            diff = "m",
            selfRemark = "按照follow_up的要求，就写了很简单的一个答案。")
    public int countBattleships(char[][] board) {
        int count = 0;
        char X = 'X';
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                if ((i > 0 && board[i - 1][j] == X) ||
                        (j > 0 && board[i][j - 1] == X)) {
                    continue;
                }
                count++;
            }
        }
        return count;
    }

    @Parameter
    public char[][] board;
    @Parameter(1)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new char[][] {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}}, 2},
                {new char[][] {{'X', '.', '.', 'X'}, {'.', 'X', '.', 'X'}, {'.', '.', '.', 'X'}}, 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, countBattleships(board));
    }

}