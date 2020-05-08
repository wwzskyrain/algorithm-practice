package study.erik.algorithm.leetcode.divide;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-05 21:25
 */
public class Search2DMatrixII {

    @Test
    public void test_solution() {
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        Assert.assertTrue(searchMatrix(matrix, 5));

        Assert.assertFalse(searchMatrix(matrix, 27));

        Assert.assertTrue(searchMatrix(matrix, 1));

        Assert.assertFalse(searchMatrix(matrix, 0));
    }


    /**
     * title = Search a 2D Matrix II
     * 成绩：100 和 94 ，不错的成绩；但是不用太高兴，因为这个解法是'剑指offer'中的。
     * 不过值得肯定的是，我们的思路和code都是一边下来的，很熟练了
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {

            if (target == matrix[i][j]) {
                return true;
            } else if (target < matrix[i][j]) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }


}
