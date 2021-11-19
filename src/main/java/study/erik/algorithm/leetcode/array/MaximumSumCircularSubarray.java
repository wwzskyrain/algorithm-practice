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

/**
 * @author yueyi
 * @version : MaximumSumCircularSubarray.java, v 0.1 2021年11月19日 10:28 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumSumCircularSubarray {

    @LetCodeCommit(title = "Maximum Sum Circular Subarray",
            selfRemark = "数组一循环就不会搞了。还是lee哥厉害，"
                    + "具体分析了两种情况。具体分析：https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass")
    public int maxSubarraySumCircular(int[] nums) {

        int max = nums[0];
        int min = nums[0];
        int total = nums[0];
        int curMax = nums[0];
        int curMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            curMax = Math.max(curMax + n, n);
            max = Math.max(max, curMax);
            curMin = Math.min(curMin + n, n);
            min = Math.min(min, curMin);
            total += n;
        }
        return max > 0 ? Math.max(max, total - min) : max;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {1, -2, 3, -2}, 3},
                {new int[] {5, -3, 5}, 10},
                {new int[] {3, -1, 2, -1}, 4},
                {new int[] {3, -2, 2, -3}, 3},
                {new int[] {3, 1, 3, 2, 6}, 15},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxSubarraySumCircular(nums));
    }

}