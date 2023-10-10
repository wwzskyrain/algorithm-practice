package study.erik.algorithm.leetcode.series.robber;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/10 ，时间：10:22
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class House_Robber_II {

    @LetCodeCommit(title = "213. House Robber II",
            selfRemark = "直接用两遍第一题的解法，再求max即可。" +
                    "不要觉得low，这是一种问题拆解思路，很可取的。")
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if (nums.length == 3) {
            return Math.max(nums[0], Math.max(nums[1], nums[2]));
        }

        int[] copyFrom0ToN_1 = Arrays.copyOf(nums, nums.length - 1);
        int resultFrom0ToN_1 = solutionForHouseRobber1(copyFrom0ToN_1);

        int[] copyFrom1ToN_2 = Arrays.copyOfRange(nums, 1, nums.length - 2);
        int resultFrom1ToN_2 = solutionForHouseRobber1(copyFrom1ToN_2);

        return Math.max(resultFrom0ToN_1, resultFrom1ToN_2 + nums[nums.length - 1]);
    }

    public int solutionForHouseRobber1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        if (nums.length == 3) {
            return Math.max(nums[1], nums[0] + nums[2]);
        }

        int result1 = Math.max(nums[1], nums[0] + nums[2]);
        int result2 = Math.max(nums[0], nums[1]);
        int result = 0;

        for (int i = 3; i < nums.length; i++) {
            result = Math.max(result2 + nums[i], result1);
            result2 = result1;
            result1 = result;
        }

        return result;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, ArrayUtils.buildArray("[2,3,2]")},
                {4, ArrayUtils.buildArray("[1,2,3,1]")},
                {3, ArrayUtils.buildArray("[1,2,3]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;


    @Test
    public void test() {
        Assert.assertEquals(expect, rob(nums));
    }

}
