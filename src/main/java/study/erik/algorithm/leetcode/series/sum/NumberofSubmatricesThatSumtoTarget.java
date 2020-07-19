package study.erik.algorithm.leetcode.series.sum;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-07-18 13:47
 * title = Number of Submatrices That Sum to Target
 */
public class NumberofSubmatricesThatSumtoTarget {


    @Test
    public void test_() {

        int[][] matrix1 = {{1, -1}, {-1, 1}};
        Assert.assertEquals(5, numSubmatrixSumTarget1(matrix1, 0));

        int[][] matrix = {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
        Assert.assertEquals(4, numSubmatrixSumTarget1(matrix, 0));
    }


    /**
     * 成绩：5和89，再后来这个这个耗时就会超时了。
     * 这个题目的这个解法不难，而且代码也很有型。
     * 我用了四层循环；我暂时不想看其他的解法。
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {

        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 && j == 0) {
                    //nothing.
                } else if (i == 0) {
                    matrix[i][j] += matrix[i][j - 1];
                } else if (j == 0) {
                    matrix[i][j] += matrix[i - 1][j];
                } else {
                    matrix[i][j] += (matrix[i - 1][j] + matrix[i][j - 1] - matrix[i - 1][j - 1]);
                }
            }
        }

        int sum;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                for (int k = i; k < matrix.length; k++) {
                    for (int l = j; l < matrix[k].length; l++) {

                        if (i == 0 && j == 0) {
                            sum = matrix[k][l];
                        } else if (i == 0) {
                            sum = matrix[k][l] - matrix[k][j - 1];
                        } else if (j == 0) {
                            sum = matrix[k][l] - matrix[i - 1][l];
                        } else {
                            sum = matrix[k][l] - matrix[k][j - 1] - matrix[i - 1][l] + matrix[i - 1][j - 1];
                        }
                        if (sum == target) {
                            count++;
                        }

                    }
                }
            }
        }
        return count;
    }


    /**
     * 大神lee215的思路
     * 也没有快多少哈。成绩20和5
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget1(int[][] matrix, int target) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = i; j < matrix[0].length; j++) {

                map.clear();
                //缺少这个初始值的话，结果是不会对的，哈哈。
                map.put(0, 1);
                int sum = 0;
                for (int k = 0; k < matrix.length; k++) {
                    sum += matrix[k][j] - (i > 0 ? matrix[k][i - 1] : 0);
                    count += map.getOrDefault(sum - target, 0);
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return count;

    }


}
