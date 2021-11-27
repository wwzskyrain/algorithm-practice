package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;

/**
 * @author erik.wang
 * @date 2020-03-10 22:40
 * @description
 */
public class UniquePathII {

    /**
     * title = Unique Paths II
     * url = https://leetcode.com/problems/unique-paths-ii/
     * 这个题目，解法思想和I一样，就是要细细的分析'不可达'的情况。我们就不写了。
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] mat = new int[m][n];
        mat[0][0] = 1 - obstacleGrid[0][0];
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                mat[0][j] = 0;
                continue;
            }
            mat[0][j] = mat[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = (obstacleGrid[i][j] == 1) ?
                        0
                        : (obstacleGrid[i - 1][j] == 0 ? mat[i - 1][j] : 0) + ((j > 0 && obstacleGrid[i][j - 1] == 0) ? mat[i][j - 1] : 0);
            }
        }
        return mat[m - 1][n - 1];
    }

    @Test
    public void test_() {

        //int[][] obstacleGrid = ArrayUtils.buildArray2Dimension("[[0,0]]");
        //Assert.assertEquals(1, uniquePathsWithObstacles(obstacleGrid));

        //int[][] obstacleGrid = ArrayUtils.buildArray2Dimension("[[1,0]]");
        //Assert.assertEquals(0, uniquePathsWithObstacles(obstacleGrid));

        int[][] obstacleGrid = ArrayUtils.buildArray2Dimension("[[0,1,0,0,0],[1,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0]]");
        Assert.assertEquals(0, uniquePathsWithObstacles(obstacleGrid));

    }

}
