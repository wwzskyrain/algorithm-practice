package study.erik.algorithm.leetcode.matrix;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2026/3/17 17:45
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class LargestSubmatrixWithRearrangements {

    @LetCodeCommit(title = "1727. Largest Submatrix With Rearrangements",
            selfRemark = "先算高，再排序（排序不影响所求矩阵面积）")
    public int largestSubmatrix(int[][] matrix) {
        int ret = 0;
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        for (int i = 0; i < rowNum; i++) {

            for (int j = 0; j < colNum; j++) {
                if (i != 0) {
                    if (matrix[i][j] != 0) {
                        matrix[i][j] = matrix[i - 1][j] + 1;
                    }
                }
            }
            int[] curRow = matrix[i].clone();
            Arrays.sort(curRow);
            for (int k = 0; k < colNum; k++) {
                ret = Math.max(ret, curRow[k] * (colNum - k));
            }
        }
        return ret;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {4, ArrayUtils.buildArray2Dimension("[[0,0,1],[1,1,1],[1,0,1]]")},
                {3, ArrayUtils.buildArray2Dimension("[[1,0,1,0,1]]")},
                {2, ArrayUtils.buildArray2Dimension("[[1,1,0],[1,0,1]]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] matrix;


    @Test
    public void test() {
        Assert.assertEquals(expect, largestSubmatrix(matrix));
    }

}
