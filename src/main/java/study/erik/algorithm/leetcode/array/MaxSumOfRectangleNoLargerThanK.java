/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.TreeSet;

/**
 * @author yueyi
 * @version : MaxSumOfRectangleNoLargerThanK.java, v 0.1 2022年02月19日 4:30 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaxSumOfRectangleNoLargerThanK {

    @LetCodeCommit(title = "363. Max Sum of Rectangle No Larger Than K",
            selfRemark = "关键是找集合中两个数的差值最接近K的那对。插入并排序在[二分]查找，就行了")
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rowMax = matrix.length;
        int colMax = matrix[0].length;
        int[][] areas = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                int sum = matrix[i][j];
                if (i > 0) {sum += areas[i - 1][j];}
                if (j > 0) {sum += areas[i][j - 1];}
                if (i > 0 && j > 0) {sum -= areas[i - 1][j - 1];}
                areas[i][j] = sum;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int row1 = 0; row1 < rowMax; row1++) {
            for (int row2 = row1; row2 < rowMax; row2++) {
                TreeSet<Integer> treeSet = new TreeSet<>();
                treeSet.add(0);
                for (int j = 0; j < colMax; j++) {
                    int areaJ = areas[row2][j] - (row1 > 0 ? areas[row1 - 1][j] : 0);
                    Integer ceiling = treeSet.ceiling(areaJ - k);
                    if (ceiling != null) {
                        max = Math.max(max, areaJ - ceiling);
                    }
                    treeSet.add(areaJ);
                }
            }
        }
        return max;
    }

    @Parameter
    public int[][] matrix;
    @Parameter(1)
    public int     k;
    @Parameter(2)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,0,1],[0,-2,3]]"), 2, 2},
                {ArrayUtils.buildArray2Dimension("[[2,2,-1]]"), 3, 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxSumSubmatrix(matrix, k));
    }

}