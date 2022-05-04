/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ToeplitzMatrix.java, v 0.1 2022年05月04日 16:30 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ToeplitzMatrix {

    @LetCodeCommit(title = "766. Toeplitz Matrix")
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}