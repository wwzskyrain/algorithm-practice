/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : QueensThatCanAttackTheKing.java, v 0.1 2023年03月12日 14:05 yueyi Exp $
 */
public class QueensThatCanAttackTheKing {

    @LetCodeCommit(title = "1222. Queens That Can Attack the King")
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Set<Integer> set = new TreeSet<>();
        for (int[] queen : queens) {
            int p = queen[0] * 8 + queen[1];
            set.add(p);
        }
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                //八个方向
                if (i == 0 && j == 0) {
                    continue;
                }
                for (int k = 1; k < 8; k++) {
                    int x = king[0] + i * k;
                    int y = king[1] + j * k;
                    if (x >= 8 || x < 0 || y >= 8 || y < 0) {
                        break;
                    }
                    if (set.contains(x * 8 + y)) {
                        ret.add(Arrays.asList(x, y));
                        break;
                    }
                }

            }
        }
        return ret;
    }
}