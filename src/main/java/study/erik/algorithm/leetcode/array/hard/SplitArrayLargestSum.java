/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

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
 * @version : SplitArrayLargestSum.java, v 0.1 2022年02月11日 9:54 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SplitArrayLargestSum {

    @LetCodeCommit(title = "410. Split Array Largest Sum",
            selfRemark = "零零碎碎的写好了，竟然通过了，可是让我哭笑不得，只有5%的成绩。"
                    + "我以为这是个dp题目，却有人用binarySearch给搞定了，真tm的厉害")
    public int splitArray(int[] nums, int m) {
        int[] preSum = new int[nums.length];

        for (int i = 0; i < preSum.length; i++) {
            preSum[i] = (i == 0 ? 0 : preSum[i - 1]) + nums[i];
        }

        int[][] dp = new int[m + 1][nums.length];
        for (int j = 0; j < dp[1].length; j++) {
            dp[1][j] = preSum[j];
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                int min = preSum[nums.length - 1];
                for (int k = 1; j - k + 1 >= i - 1; k++) {
                    int sum = preSum[j] - preSum[j - k];
                    int max = Math.max(dp[i - 1][j - k], sum);
                    min = Math.min(min, max);
                }
                dp[i][j] = min;
            }
        }
        return dp[m][nums.length - 1];
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   m;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[7,2,5,10,8]"), 2, 18},
                {ArrayUtils.buildArray("[7,2,5,10,8]"), 3, 14},
                {ArrayUtils.buildArray("[1,2,3,4,5]"), 2, 9},
                {ArrayUtils.buildArray("[1,4,4]"), 3, 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, splitArray(nums, m));
    }
}