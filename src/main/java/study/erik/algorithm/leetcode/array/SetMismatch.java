/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : SetMismatch.java, v 0.1 2021年07月04日 1:12 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SetMismatch {

    @LetCodeCommit(title = "Set Mismatch")
    public int[] findErrorNums(int[] nums) {
        return resolve(nums);
    }

    public int[] resolve(int[] nums) {
        Arrays.sort(nums);
        int[] result = new int[2];
        // result[0] = 重复的数字
        // result[1] = 缺失的数字
        int i = 0;
        while (nums[i] == i + 1) {
            i++;
        }
        if (nums[i] < i + 1) {
            // 多了一个数字，即找到了重复的数字
            result[0] = nums[i];
            while (i < nums.length && nums[i] != i + 1) {
                i++;
            }
            result[1] = i;
        } else {
            // 跳了一个数字，找到了缺失的数字
            result[1] = i + 1;
            while (i < nums.length && nums[i] != i + 1) {
                i++;
            }
            result[0] = i + 1;
        }

        return result;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int[] except;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {2, 2}, new int[] {2, 1}},
                {new int[] {3, 2, 3, 4, 6, 5}, new int[] {3, 1}},
                {new int[] {1, 2, 2, 4}, new int[] {2, 3}},
                {new int[] {1, 1}, new int[] {1, 2}},
        };
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(except, findErrorNums(nums));
    }

}