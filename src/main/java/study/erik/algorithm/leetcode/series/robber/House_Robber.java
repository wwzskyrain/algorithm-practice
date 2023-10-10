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
 * 日期：2023/10/10 ，时间：10:14
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class House_Robber {

    @LetCodeCommit(title = "198. House Robber",
            selfRemark = "很基础的的dp问题")
    public int robSolution1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        //dp[i]表示，问题的解
        //dp[i] = max(dp[i-2]+nums[i] , dp[i-1])
        dp[0] = nums[0];
        if (n < 2) {
            return dp[0];
        }
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    /**
     * 对上一个解法的代码优化
     *
     * @param nums
     * @return
     */
    public int robSolution2(int[] nums) {
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
                {4, ArrayUtils.buildArray("[1,2,3,1]")},
                {12, ArrayUtils.buildArray("[2,7,9,3,1]")},
                {4, ArrayUtils.buildArray("[1,2,3,1]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, robSolution1(nums));
    }

}
