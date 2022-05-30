/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.fibonacci;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author yueyi
 * @version : LengthOfLongestFibonacciSubsequence.java, v 0.1 2022年05月29日 17:08 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LengthOfLongestFibonacciSubsequence {

    @LetCodeCommit(title = "873. Length of Longest Fibonacci Subsequence",
            diff = "m",
            selfRemark = "为什么没有用dp做出来呢？很难受。其实就快写出来了。还是没有想清楚.")
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> index = new HashMap<>();
        IntStream.range(0, arr.length).forEach(i -> index.put(arr[i], i));
        int[][] dp = new int[arr.length][arr.length];
        // dp[i][j] 是以arr[i]/arr[j]为结尾的斐波那契额数列的最大长度.
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int c = arr[i] + arr[j];
                Integer idx = index.getOrDefault(c, -1);
                if (idx > j) {
                    dp[j][idx] = Math.max(dp[j][idx], Math.max(dp[i][j], 2) + 1);
                    max = Math.max(max, dp[j][idx]);
                }
            }
        }
        return max;
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,4,7,8,9,10,14,15,18,23,32,50]"), 5},
                {ArrayUtils.buildArray("[1,2,3,4,5,6,7,8]"), 5},
                {ArrayUtils.buildArray("[1,3,7,11,12,14,18]"), 3},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, lenLongestFibSubseq(arr));
    }

}