/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CountSquareSubmatricesWithAllOnes.java, v 0.1 2023年03月18日 08:43 yueyi Exp $
 */
public class CountSquareSubmatricesWithAllOnes {

    @LetCodeCommit(title = "1277. Count Square Submatrices with All Ones")
    public int countSquares(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > 0 && i > 0 && j > 0) {
                    matrix[i][j] = Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1])) + 1;
                }
                ret += matrix[i][j];
            }
        }
        return ret;
    }

}