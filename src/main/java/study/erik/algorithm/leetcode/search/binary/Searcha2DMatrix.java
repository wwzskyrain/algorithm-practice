/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : Searcha2DMatrix.java, v 0.1 2021年11月28日 12:02 上午 yueyi Exp $
 */
public class Searcha2DMatrix {

    @LetCodeCommit(title = "74. Search a 2D Matrix")
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;
        do {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        } while (i < matrix.length && j >= 0);
        return false;
    }

    @Test
    public void test_() {
        //Assert.assertEquals(true, searchMatrix(ArrayUtils.buildArray2Dimension("[[1]]"), 1));
        Assert.assertEquals(true, searchMatrix(ArrayUtils.buildArray2Dimension("[[1,3]]"), 1));

    }

}