/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

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
 * @version : MaxDotProductOfTwoSubsequences.java, v 0.1 2023年05月14日 16:20 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaxDotProductOfTwoSubsequences {

    @LetCodeCommit(title = "1458. Max Dot Product of Two Subsequences",
            diff = "h",
            selfRemark = "最长公共子串。"
                    + "但是我却对这个递推公式思考不明白了——难受。"
                    + "我弄混的是与以s1[i]/s2[j]为结尾的最值。"
                    + "这种dp公式是没法简单的推的。因为它不具有包含性。"
                    + "而已dp[i][j]表示解的定义，其具有包含性。")
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[][] dp = new int[n1][n2];
        // dp[i][j]表示nums1[...i],nums2[...j]的解，即最大值。
        // 有四部分组成：
        // case1：num1[i] * num2[j] 都参与，
        // case2：num1[i]不参与，
        // case3：num2[j]不参与，
        // case4：num1[i]和num2[j]都不参与，这在dp[i-1][j]、dp[i][j-1]中包含这，所以不用特意计算.
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                dp[i][j] = nums1[i] * nums2[j];
                if (i > 0 && j > 0) {
                    dp[i][j] += Math.max(dp[i - 1][j - 1], 0);
                }
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1 - 1][n2 - 1];
    }

    @Parameter
    public int[] nums1;
    @Parameter(1)
    public int[] nums2;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{ArrayUtils.buildArray("[2,1,-2,5]"), ArrayUtils.buildArray("[3,0,-6]"), 18},
                //{ArrayUtils.buildArray("[3,-2]"), ArrayUtils.buildArray("[2,-6,7]"), 21},
                {ArrayUtils.buildArray("[1,-1]"), ArrayUtils.buildArray("[1,1]"), -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxDotProduct(nums1, nums2));
    }

}