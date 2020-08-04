package study.erik.algorithm.leetcode.medium;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-08-04 10:26
 */
public class RotateImage {

    @LetCodeCommit(title = "Rotate Image", time = 6, space = 5,
            types = {LetCodeCommit.Type.Detail, LetCodeCommit.Type.Matrix})
    public void rotate(int[][] matrix) {

        int length = matrix.length;

        for (int i = 0; i < (length / 2 + length % 2); i++) {
            int L = length - 2 * i;
            for (int l = 0; l < L - 1; l++) {
                int x = i, y = i + l;
                int temp = matrix[x][y];
                // 左侧
                int x1 = i + L - l - 1;
                int y1 = i;
                matrix[x][y] = matrix[x1][y1];
                x = x1;
                y = y1;

//              bottom
                x1 = i + L - 1;
                y1 = i + L - l - 1;

                matrix[x][y] = matrix[x1][y1];
                x = x1;
                y = y1;

//              right
                x1 = i + l;
                y1 = i + L - 1;
                matrix[x][y] = matrix[x1][y1];

                matrix[x1][y1] = temp;
            }
        }
        for (int[] array : matrix) {
            System.out.println(Arrays.toString(array));
        }

    }

    @Test
    public void test_case_1() {

        int[][] matrix =
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                };
        rotate(matrix);
    }

    @Test
    public void test_case_2() {

        int[][] matrix =
                {
                        {5, 1, 9, 11},
                        {2, 4, 8, 10},
                        {13, 3, 6, 7},
                        {15, 14, 12, 16}
                };
        rotate(matrix);
    }

}
