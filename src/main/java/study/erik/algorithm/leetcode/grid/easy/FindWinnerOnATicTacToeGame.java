/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FindWinnerOnATicTacToeGame.java, v 0.1 2023年03月17日 03:35 yueyi Exp $
 */
public class FindWinnerOnATicTacToeGame {

    @LetCodeCommit(title = "1275. Find Winner on a Tic Tac Toe Game",
            selfRemark = "挺不错的一个题目")
    public String tictactoe(int[][] moves) {
        int[] aRow = new int[3], aCol = new int[3];
        int[] bRow = new int[3], bCol = new int[3];
        int a1 = 0, a2 = 0;
        int b1 = 0, b2 = 0;

        int id = 0;
        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            int r = move[0], c = move[1];
            if (id % 2 == 0) {
                if (++aRow[r] == 3 || ++aCol[c] == 3 || (r == c && ++a1 == 3) || (r + c == 2 && ++a2 == 3)) {
                    return "A";
                }
            } else {
                if (++bRow[r] == 3 || ++bCol[c] == 3 || (r == c && ++b1 == 3) || (r + c == 2 && ++b2 == 3)) {
                    return "B";
                }
            }
            id++;
        }
        return moves.length == 9 ? "Draw" : "Pending";

    }

}