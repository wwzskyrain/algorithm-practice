/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;
import study.erik.algorithm.util.QuestionType;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : ArrayPartitionI.java, v 0.1 2021年02月16日 10:44 下午 yueyi Exp $
 */
public class ArrayPartitionI {

    @LetCodeCommit(title = "Array Partition I", no = 561,
            types = {QuestionType.Array, QuestionType.MostValue},
            selfRemark = "这是一道操作题：分隔，计算，求最值。然而这种操作却有一种更简化的形式，那就是排序然后计算。"
                    + "简化形式为啥是正解呢？")
    public int arrayPairSum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int maxSum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            maxSum += nums[i];
        }
        return maxSum;
    }

    @Test
    public void test_solution_1() {
        int[] nums = {1, 4, 3, 2};
        Assert.assertEquals(4, arrayPairSum(nums));
    }

    @Test
    public void test_solution_2() {
        int[] nums = {6, 2, 6, 5, 1, 2};
        Assert.assertEquals(9, arrayPairSum(nums));
    }

}