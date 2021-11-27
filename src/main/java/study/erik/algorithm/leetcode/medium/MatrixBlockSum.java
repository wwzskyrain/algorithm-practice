/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MatrixBlockSum.java, v 0.1 2021年11月27日 12:00 上午 yueyi Exp $
 */
public class MatrixBlockSum {

    @LetCodeCommit(title = "Matrix Block Sum")
    public int[][] matrixBlockSum(int[][] mat, int k) {

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = ((i - 1) >= 0 ? mat[i - 1][j] : 0)
                        + ((j - 1) >= 0 ? mat[i][j - 1] : 0)
                        - ((i - 1 >= 0 && j - 1 >= 0) ? mat[i - 1][j - 1] : 0)
                        + mat[i][j];
            }
        }

        int[][] sum = new int[mat.length][mat[0].length];
        for (int i = 0; i < sum.length; i++) {
            for (int j = 0; j < sum[i].length; j++) {
                int startI = Math.max(0, i - k);
                int startJ = Math.max(0, j - k);

                int endI = Math.min(sum.length - 1, i + k);
                int endJ = Math.min(sum[i].length - 1, j + k);

                sum[i][j] = mat[endI][endJ] -
                        ((startJ > 0) ? mat[endI][startJ - 1] : 0) -
                        ((startI > 0) ? mat[startI - 1][endJ] : 0) +
                        ((startI > 0 && startJ > 0) ? mat[startI - 1][startJ - 1] : 0);
            }
        }
        return sum;
    }

    @Test
    public void test_() {
        int[][] expect = ArrayUtils.buildArray2Dimension("[[12,21,16],[27,45,33],[24,39,28]]");
        int[][] mat = ArrayUtils.buildArray2Dimension("[[1,2,3],[4,5,6],[7,8,9]]");
        int[][] actual = matrixBlockSum(mat, 1);
        Assert.assertArrayEquals(expect, actual);
    }
}