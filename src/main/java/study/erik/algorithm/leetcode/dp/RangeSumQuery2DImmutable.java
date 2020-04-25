package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-04-24 16:13
 */
public class RangeSumQuery2DImmutable {

    /**
     * title = Range Sum Query 2D - Immutable
     * url = https://leetcode.com/problems/range-sum-query-2d-immutable/
     */
    static class NumMatrix {

        long[][] sum = null;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            sum = new long[matrix.length][matrix[0].length];
            for (int i = 0; i < sum.length; i++) {
                sum[i][0] = matrix[i][0] + ((i != 0) ? sum[i - 1][0] : 0);
            }

            for (int i = 0; i < matrix[0].length; i++) {
                sum[0][i] = matrix[0][i] + (i != 0 ? sum[0][i - 1] : 0);
            }

            for (int i = 1; i < sum.length; i++) {
                for (int j = 1; j < sum[0].length; j++) {
                    sum[i][j] = matrix[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                }
            }

        }

        /**
         * 成绩：78和33
         * 算法很简单，不需要解释了。
         * @param row1
         * @param col1
         * @param row2
         * @param col2
         * @return
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (sum == null) {
                return 0;
            }
            if (row1 == 0 && col1 == 0) {
                return (int) sum[row2][col2];
            }
            if (col1 == 0) {
                return (int) (sum[row2][col2] - sum[row1 - 1][col2]);
            }
            if (row1 == 0) {
                return (int) (sum[row2][col2] - sum[row2][col1 - 1]);
            }
            return (int) (sum[row2][col2] - sum[row2][col1 - 1] - sum[row1 - 1][col2] + sum[row1 - 1][col1 - 1]);
        }

    }

    public static void print(int[][] data) {
        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
    }

    public static void print(long[][] data) {
        for (long[] datum : data) {
            System.out.println(Arrays.toString(datum));
        }
    }

    @Test
    public void test_() {

        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        print(matrix);
        System.out.println();
        NumMatrix numMatrix = new NumMatrix(matrix);
        print(numMatrix.sum);
        Assert.assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));
        Assert.assertEquals(11, numMatrix.sumRegion(1, 1, 2, 2));
        Assert.assertEquals(12, numMatrix.sumRegion(1, 2, 2, 4));

        Assert.assertEquals(38, numMatrix.sumRegion(0, 0, 4, 3));
        Assert.assertEquals(6, numMatrix.sumRegion(0, 3, 0, 4));


    }


}
