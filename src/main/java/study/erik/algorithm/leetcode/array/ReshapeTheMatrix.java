/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ReshapeTheMatrix.java, v 0.1 2021年02月17日 5:00 下午 yueyi Exp $
 */
public class ReshapeTheMatrix {

    @LetCodeCommit(title = "Reshape the Matrix", no = 566, selfRemark = "考查多维数组的本质。很简单的一个题目")
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums.length == 0) {
            return nums;
        }
        int rows = nums.length;
        int columns = nums[0].length;
        if (rows * columns != r * c) {
            return nums;
        }
        int[][] result = new int[r][c];

        for (int i = 0; i < r; i++) {
            result[i] = new int[c];
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                int no = i * columns + j;
                result[no / c][no % c] = nums[i][j];
            }
        }
        return result;
    }

    @Test
    public void test_solution_1() {
        int[][] nums = {{1, 2}, {3, 4}};
        int[][] expect = {{1, 2, 3, 4}};
        Assert.assertEquals(expect, matrixReshape(nums, 1, 4));
    }

    @Test
    public void test_solution_2() {
        int[][] nums = {{1, 2}, {3, 4}};
        Assert.assertEquals(nums, matrixReshape(nums, 2, 4));
    }

}