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

import java.util.Arrays;

/**
 * @author yueyi
 * @version : AllocateMailboxes.java, v 0.1 2023年05月14日 17:54 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class AllocateMailboxes {

    @LetCodeCommit(title = "1478. Allocate Mailboxes",
            diff = "h",
            selfRemark = "没费太多事儿。独立做出来hard"
                    + "有两个点，一要弄清楚k=1的时候，如何计算所有最小距离。"
                    + "第二个点就是问题分解了。"
                    + "也可以有第三个点，那就是用备忘录呗。")
    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        Arrays.sort(houses);
        Integer[][][] dp = new Integer[k + 1][n][n];
        return dfs(houses, 0, n - 1, k, dp);
    }

    public int dfs(int[] houses, int l, int r, int k, Integer[][][] dp) {
        if (k == 1) {
            int ll = l;
            int rr = r;
            int total = 0;
            while (ll < rr) {
                total += houses[rr--] - houses[ll++];
            }
            return total;
        }
        if (dp[k][l][r] != null) {
            return dp[k][l][r];
        }
        int min = Integer.MAX_VALUE;
        for (int i = l + k - 1 - 1; i < r; i++) {
            int total = 0;
            total += dfs(houses, l, i, k - 1, dp);
            total += dfs(houses, i + 1, r, 1, dp);
            min = Math.min(min, total);
        }
        dp[k][l][r] = min;
        return min;
    }

    @Parameter
    public int[] houses;
    @Parameter(1)
    public int   k;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[7,4,6,1]"), 1, 8},
                {ArrayUtils.buildArray("[1,4,8,10,20]"), 3, 5},
                {ArrayUtils.buildArray("[2,3,5,12,18]"), 2, 9},
                {ArrayUtils.buildArray("[1,4,8,10,20]"), 3, 5},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minDistance(houses, k));
    }

}