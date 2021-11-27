/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import study.erik.algorithm.util.ArrayUtils;

/**
 * @author yueyi
 * @version : NumMatrix.java, v 0.1 2021年11月27日 12:27 上午 yueyi Exp $
 */
public class NumMatrix {

    int[][] sum;

    public NumMatrix(int[][] matrix) {
        sum = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum[i][j] = (i > 0 ? sum[i - 1][j] : 0)
                        + (j > 0 ? sum[i][j - 1] : 0)
                        - (i > 0 && j > 0 ? sum[i - 1][j - 1] : 0)
                        + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2][col2] -
                ((col1 > 0) ? sum[row2][col1 - 1] : 0) -
                ((row1 > 0) ? sum[row1 - 1][col2] : 0) +
                ((row1 > 0 && col1 > 0) ? sum[row1 - 1][col1 - 1] : 0);
    }

    public static void main(String[] args) {

        int[][] matrix = ArrayUtils.buildArray2Dimension(
                "[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]");
        NumMatrix numMatrix = new NumMatrix(matrix);
        int rangSum = numMatrix.sumRegion(1, 1, 2, 2);
        Assert.assertEquals(11, rangSum);
    }

}