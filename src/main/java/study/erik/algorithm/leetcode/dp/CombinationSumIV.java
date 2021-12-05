/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : CombinationSumIV.java, v 0.1 2021年12月05日 8:06 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CombinationSumIV {

    /**
     * @param nums
     * @param target
     * @return
     * @see CoinChangeII
     */
    @LetCodeCommit(title = "377. Combination Sum IV",
            selfRemark = "靠，文不加点，一遍过。有了coinChange1/2的加持，果然有效果。"
                    + "这个和题目CoinChange2完全是一对呀，那里求组合，这里求排列")
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == 0) {
                continue;
            }
            for (int num : nums) {
                int nextIndex = i + num;
                if (nextIndex >= 0 && nextIndex < dp.length) {
                    dp[i + num] += dp[i];
                }
            }
        }
        return dp[target];
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   target;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3]"), 4, 7},
                {ArrayUtils.buildArray("[9]"), 3, 0},
                {ArrayUtils.buildArray("[1,2,3]"), 4, 7},
                {ArrayUtils.buildArray("[1,2,3]"), 4, 7},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, combinationSum4(nums, target));
    }

}