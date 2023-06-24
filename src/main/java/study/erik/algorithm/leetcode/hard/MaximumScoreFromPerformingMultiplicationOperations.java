/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

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
 * @version : MaximumScoreFromPerformingMultiplicationOperations.java, v 0.1 2023年06月24日 10:40 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumScoreFromPerformingMultiplicationOperations {

    @LetCodeCommit(title = "1770. Maximum Score from Performing Multiplication Operations",
            diff = "h",
            selfRemark = "很简单的dp。我记录下我的过程。"
                    + "首先直接手写一个三位dp，内存超时。"
                    + "再把第三维拿掉，用前两维度推。还是内存超，因为nums.length 是10^5。"
                    + "再把二个维度换成m，就不超内存l")
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        Integer[][] dp = new Integer[m][m];
        return dfs(0, 0, nums, multipliers, dp);
    }

    public int dfs(int m, int i, int[] nums, int[] multi, Integer[][] dp) {
        if (m == multi.length) {
            return 0;
        }
        int j = nums.length - (m - i) - 1;
        if (i == j) {
            return nums[i] * multi[m];
        }
        if (dp[m][i] != null) {
            return dp[m][i];
        }
        int v1 = nums[i] * multi[m] + dfs(m + 1, i + 1, nums, multi, dp);
        int v2 = nums[j] * multi[m] + dfs(m + 1, i, nums, multi, dp);
        return dp[m][i] = Math.max(v1, v2);
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int[] multipliers;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3]"), ArrayUtils.buildArray("[3,2,1]"), 14},
                {ArrayUtils.buildArray("[-5,-3,-3,-2,7,1]"), ArrayUtils.buildArray("[-10,-5,3,4,6]"), 102},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maximumScore(nums, multipliers));
    }

}