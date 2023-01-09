/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
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

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : MakeArrayStrictlyIncreasing.java, v 0.1 2023年01月02日 22:30 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MakeArrayStrictlyIncreasing {

    @LetCodeCommit(title = "1187. Make Array Strictly Increasing",
            selfRemark = "这个题目的解析回头在写")
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        int n = arr1.length;
        if (n == 1) {return 0;}
        TreeSet<Integer> set = new TreeSet<>();
        Arrays.stream(arr2).forEach(set::add);
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // dp[i][j] 表示交换i次使得arr1[:j]严格单调的最小值
        dp[0][0] = Integer.MIN_VALUE;
        for (int j = 1; j <= n; ++j) {
            for (int i = 0; i <= j; ++i) {
                if (arr1[j - 1] > dp[i][j - 1]) {
                    dp[i][j] = arr1[j - 1];
                }
                if (i > 0) {
                    Integer higher = set.higher(dp[i - 1][j - 1]);
                    if (higher != null) {
                        dp[i][j] = Math.min(dp[i][j], higher);
                    }
                }
                if (j == n && dp[i][j] != Integer.MAX_VALUE) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Parameter
    public int[] arr1;
    @Parameter(1)
    public int[] arr2;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,5,3,6,7]"), ArrayUtils.buildArray("[1,3,2,4]"), 1},
                {ArrayUtils.buildArray("[1,5,3,6,7]"), ArrayUtils.buildArray("[4,3,1]"), 2},
                {ArrayUtils.buildArray("[1,5,3,6,7]"), ArrayUtils.buildArray("[1,6,3,3]"), -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, makeArrayIncreasing(arr1, arr2));
    }
}