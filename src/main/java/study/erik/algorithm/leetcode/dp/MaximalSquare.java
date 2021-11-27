package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-22 15:12
 */
public class MaximalSquare {

    /**
     * title = Maximal Square //最大的正方形-不是矩形
     * url = https://leetcode.com/problems/maximal-square/
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        return solution(matrix);
    }

    /**
     * 成绩：84和94
     * 解法：二位数组edge[i][j](简称e[i][j]) 代表以位置[i][j]为右下角的正方向的最长的边。
     * case1: [i][j] 是'1', e[i][j] = min{e[i-1][j-1], e[i-1][j], e[i][j-1]} + 1
     * case2: [i][j] 是'0', 0.
     *
     * 注意，请好好思考case1的那个递推公式
     * 解法来源：leetcode的diss区域
     *
     * extension：如果要求的是'矩形'而不是放行，就需要另外一种解法，而不是dp了。--单调栈
     *
     * @param matrix
     * @return
     */
    public int solution(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int maxEdge = 0;
        int[][] mat = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < mat[0].length; j++) {
            mat[0][j] = matrix[0][j] == '0' ? 0 : 1;
            maxEdge = Math.max(maxEdge, mat[0][j]);
        }
        for (int i = 1; i < matrix.length; i++) {
            mat[i][0] = matrix[i][0] == '0' ? 0 : 1;
            maxEdge = Math.max(maxEdge, mat[i][0]);
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                mat[i][j] = (matrix[i][j] == '0') ?
                        0 :
                        (Math.min(mat[i - 1][j - 1], Math.min(mat[i - 1][j], mat[i][j - 1])) + 1);

                maxEdge = Math.max(maxEdge, mat[i][j]);
            }
        }
        return maxEdge * maxEdge;
    }

    @Test
    public void test_() {

        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};

        Assert.assertEquals(4, maximalSquare(matrix));
    }
}
