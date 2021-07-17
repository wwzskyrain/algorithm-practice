/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.sword;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaxSubArray.java, v 0.1 2021年07月17日 7:02 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaxSubArray {

    @LetCodeCommit(title = "42 连续子数组的最大和")
    public int maxSubArray(int[] nums) {
        return resolveWithDp(nums);
    }

    /**
     * 动态规划的方式求最大子数组和
     * dp[i] 表示已nums[i]为结尾的子数组的最大和
     * dp[i] = dp[i-1] > 0 ？ dp[i-1] + nums[i] : nums[i];
     * 优化：用currentMaxSuffixSum表示当前以nums[i]为结尾的最大后缀和
     *
     * @param nums
     * @return
     */
    public int resolveWithDp(int[] nums) {
        int currentMaxSuffixSum = nums[0];
        int max = currentMaxSuffixSum;
        for (int i = 1; i < nums.length; i++) {
            currentMaxSuffixSum = currentMaxSuffixSum > 0 ? currentMaxSuffixSum + nums[i] : nums[i];
            max = Math.max(currentMaxSuffixSum, max);
        }
        return max;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6}
        };
    }

    @Test
    public void test_nums() {
        Assert.assertEquals(expect, maxSubArray(nums));
    }
}