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

/**
 * @author yueyi
 * @version : JumpGameV.java, v 0.1 2021年08月03日 8:56 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class JumpGameV {

    @LetCodeCommit(title = "Jump Game V")
    public int maxJumps(int[] arr, int d) {
        return resolve(arr, d);
    }

    /**
     * dp+备忘录
     *
     * @param arr
     * @param d
     * @return
     */
    public int resolve(int[] arr, int d) {
        // dp[i] 表示i出发最大访问下标出
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            resolveWithDfs(arr, d, i, dp);
        }
        int maxVisited = 0;
        for (int i = 0; i < dp.length; i++) {
            maxVisited = Math.max(dp[i], maxVisited);
        }
        return maxVisited;
    }

    /**
     * 从curIndex开始，能访问的最多的index
     *
     * @param arr
     * @param d
     * @param curIndex
     * @return
     */
    private int resolveWithDfs(int[] arr, int d, int curIndex, int[] mem) {
        if (mem[curIndex] > 0) {
            return mem[curIndex];
        }
        //表示当前访问了curIndex
        int max = 1;
        int left = curIndex - 1;
        while (left >= 0 && left >= curIndex - d && arr[left] < arr[curIndex]) {
            max = Math.max(max, resolveWithDfs(arr, d, left, mem) + 1);
            left--;
        }
        int right = curIndex + 1;
        while (right < arr.length && right <= curIndex + d && arr[right] < arr[curIndex]) {
            max = Math.max(max, resolveWithDfs(arr, d, right, mem) + 1);
            right++;
        }
        return mem[curIndex] = max;
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int   d;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[6,4,14,6,8,13,9,7,10,6,12]"), 2, 4},
                {ArrayUtils.buildArray("[3,3,3,3,3]"), 3, 1},
                {ArrayUtils.buildArray("[7,6,5,4,3,2,1]"), 1, 7},
                {ArrayUtils.buildArray("[7,1,7,1,7,1]"), 2, 2},
                {ArrayUtils.buildArray("66"), 1, 1},
        };
    }

    @Test
    public void test_arr() {
        Assert.assertEquals(expect, maxJumps(arr, d));
    }
}