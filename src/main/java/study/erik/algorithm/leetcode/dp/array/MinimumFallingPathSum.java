/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumFallingPathSum.java, v 0.1 2021年11月25日 11:49 下午 yueyi Exp $
 */
public class MinimumFallingPathSum {

    @LetCodeCommit(title = "931. Minimum Falling Path Sum")
    public int minFallingPathSum(int[][] matrix) {

        for (int row = matrix.length - 2; row >= 0; row--) {
            for (int j = 0; j < matrix[row].length; j++) {
                int left = (j - 1 >= 0) ? matrix[row + 1][j - 1] : Integer.MAX_VALUE;
                int mid = matrix[row + 1][j];
                int right = (j + 1 < matrix[row].length) ? matrix[row + 1][j + 1] : Integer.MAX_VALUE;
                matrix[row][j] += Math.min(left, Math.min(mid, right));
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            min = Math.min(min, matrix[0][i]);
        }
        return min;
    }

    @Test
    public void test_() {
        int[][] matrix = ArrayUtils.buildArray2Dimension("[[2,1,3],[6,5,4],[7,8,9]]");
        Assert.assertEquals(13, minFallingPathSum(matrix));
    }

}