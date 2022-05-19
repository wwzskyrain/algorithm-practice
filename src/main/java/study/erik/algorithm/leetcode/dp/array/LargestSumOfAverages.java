/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.array;

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
 * @version : LargestSumOfAverages.java, v 0.1 2022年05月18日 22:01 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LargestSumOfAverages {

    @LetCodeCommit(title = "813. Largest Sum of Averages")
    public double largestSumOfAverages(int[] nums, int k) {

        double[] preSum = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        //dp(i,k) = max(dp(j,k-1) + ave(i, k))
        double[] dp = new double[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = (preSum[nums.length] - preSum[i]) / (nums.length - i);
        }
        for (int K = 1; K < k; K++) {
            for (int i = 0; i < nums.length; i++) {
                double max = 0;
                for (int j = i + 1; j < nums.length; j++) {
                    max = Math.max(max, dp[j] + (preSum[j] - preSum[i]) / (j - i));
                }
                dp[i] = Math.max(dp[i], max);
            }
        }
        return dp[0];
    }

    @Parameter
    public int[]  nums;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public double expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[9,1,2,3,9]"), 3, 20.00000},
                {ArrayUtils.buildArray("[1,2,3,4,5,6,7]"), 4, 20.50000},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, largestSumOfAverages(nums, k), 0.000001);
    }

}