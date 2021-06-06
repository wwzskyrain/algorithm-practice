/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.permutation;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : NextPermutation.java, v 0.1 2021年05月30日 12:53 下午 yueyi Exp $
 */
public class NextPermutation {

    @LetCodeCommit(title = "Next Permutation", time = 97, space = 58)
    public void nextPermutation(int[] nums) {

        int maxIndex = nums.length - 1;
        if (maxIndex == 0) {
            return;
        }
        // 1.找到第一个升序对
        int index = maxIndex;
        int preIndex = index - 1;
        while (preIndex >= 0 && nums[preIndex] >= nums[index]) {
            index = preIndex;
            preIndex -= 1;
        }
        // 2.如果第一个升序对不存在，即都是降序，比如 [5，4，3，2，1]，则直接排序
        if (preIndex == -1) {
            Arrays.sort(nums, 0, nums.length);
            return;
        }
        // 3.找到后面的大于当前value的最小值，并交换value和nextValue;
        int nextValue = nums[index];
        int nextIndex = index;
        int value = nums[preIndex];
        while (index <= maxIndex) {
            if (nums[index] > value && nums[index] <= nextValue) {
                nextIndex = index;
                nextValue = nums[index];
            }
            index++;
        }
        // 4.交换value和nextValue;
        int temp = nums[preIndex];
        nums[preIndex] = nums[nextIndex];
        nums[nextIndex] = temp;
        // 5.把value之后的重新排序，字典序，也是数字的的升序
        Arrays.sort(nums, preIndex + 1, nums.length);
    }

    @Test
    public void test_1() {
        int[] nums = {1, 2, 3};
        int[] expectNums = {1, 3, 2};
        nextPermutation(nums);
        Assert.assertArrayEquals(expectNums, nums);
    }

    @Test
    public void test_2() {
        int[] nums = {3, 2, 1};
        int[] expectNums = {1, 2, 3};
        nextPermutation(nums);
        Assert.assertArrayEquals(expectNums, nums);
    }

    @Test
    public void test_3() {
        int[] nums = {1, 1, 5};
        int[] expectNums = {1, 5, 1};
        nextPermutation(nums);
        Assert.assertArrayEquals(expectNums, nums);
    }

    @Test
    public void test_4() {
        int[] nums = {5, 1, 1};
        int[] expectNums = {1, 1, 5};
        nextPermutation(nums);
        Assert.assertArrayEquals(expectNums, nums);
    }

}