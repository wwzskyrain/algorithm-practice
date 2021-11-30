/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CellswithOddValuesinaMatrix.java, v 0.1 2021年11月30日 11:33 下午 yueyi Exp $
 */
public class CellswithOddValuesinaMatrix {

    @LetCodeCommit(title = "1252. Cells with Odd Values in a Matrix")
    public int oddCells(int m, int n, int[][] indices) {
        // 记录row[i]到底被增加过几次
        int[] row = new int[m];
        int[] col = new int[n];
        for (int[] index : indices) {
            row[index[0]]++;
            col[index[1]]++;
        }
        int row0 = 0;
        int row1 = 0;
        for (int i : row) {
            if (i % 2 == 0) {
                row0++;
            } else {
                row1++;
            }
        }
        int col0 = 0, col1 = 0;
        for (int i : col) {
            if (i % 2 == 0) {
                col0++;
            } else {
                col1++;
            }
        }
        return row0 * col1 + row1 * col0;
    }

}