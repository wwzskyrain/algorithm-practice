package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @Date 2019-11-22
 */
public class MaximalRectangle {

    /**
     * title: 85. Maximal Rectangle
     * url = https://leetcode.com/problems/maximal-rectangle/
     * difficulty = hard
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        return 1;
    }

    public int solution(char[][] matrix) {

        return 1;
    }

    @Test
    public void test_solution() {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};

        Assert.assertEquals(6, solution(matrix));
    }
}
