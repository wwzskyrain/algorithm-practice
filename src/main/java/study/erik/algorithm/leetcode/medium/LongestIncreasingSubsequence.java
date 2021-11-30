/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LongestIncreasingSubsequence.java, v 0.1 2021年11月30日 11:12 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LongestIncreasingSubsequence {

    @LetCodeCommit(title = "300. Longest Increasing Subsequence",
            selfRemark = "很顺手呢，而且代码也简洁多了，因为我们发现16年我们做过呢")
    public int lengthOfLIS(int[] nums) {

        int max = 1;
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    max = Math.max(max, dp[i]);
                }
            }
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
                {ArrayUtils.buildArray("[10,9,2,5,3,7,101,18]"), 4},
                {ArrayUtils.buildArray("[0,1,0,3,2,3]"), 4},
                {ArrayUtils.buildArray("[7,7,7,7,7,7,7]"), 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, lengthOfLIS(nums));
    }

}