/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.knapsack;

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

/**
 * @author yueyi
 * @version : TallestBillboard.java, v 0.1 2022年11月19日 16:24 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class TallestBillboard {

    @LetCodeCommit(title = "956. Tallest Billboard",
            diff = "h",
            selfRemark = "这个题一听有这几个想法"
                    + "1.分两拨，这是组合的思想，很朴素，很难解题，因为不好写代码"
                    + "2.再读题可知是每个元素3选一，所以可以有3的N次方的运算，可解题，不过很难通过——超时"
                    + "3.优化思路2，去重每次3选一的结果，这样就不是3的N次方了，可解题，如下")
    public int solution(int[] rods) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        Map<Integer, Integer> copy = new HashMap<>();
        for (int rod : rods) {
            copy.clear();
            copy.putAll(map);
            for (Integer sum : copy.keySet()) {
                // Max中的第二个参数需要用copy，因为不然就有可能用到本轮已经更新的值。
                // Max中的第一个参数恰恰不能用copy，只能用本轮的，因为它的作用是保值，而第二个参数是为了更新值
                map.put(sum + rod, Math.max(map.getOrDefault(sum + rod, 0), copy.get(sum) + rod));
                map.put(sum - rod, Math.max(map.getOrDefault(sum - rod, 0), copy.get(sum)));
            }
        }
        return map.get(0);
    }

    public int tallestBillboard(int[] rods) {
        int n = rods.length;
        boolean[][] dp = new boolean[n + 1][10001];
        int[][] max = new int[n + 1][10001];
        dp[0][5000] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 10000; j++) {
                if (j - rods[i] >= 0 && dp[i][j - rods[i]]) {
                    dp[i + 1][j] = true;
                    max[i + 1][j] = Math.max(max[i + 1][j], max[i][j - rods[i]] + rods[i]);
                }
                if (j + rods[i] <= 10000 && dp[i][j + rods[i]]) {
                    dp[i + 1][j] = true;
                    max[i + 1][j] = Math.max(max[i + 1][j], max[i][j + rods[i]]);
                }
                if (dp[i][j]) {
                    dp[i + 1][j] = true;
                    max[i + 1][j] = Math.max(max[i + 1][j], max[i][j]);
                }
            }
        }
        return max[n][5000];
    }

    @Parameter
    public int[] rods;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3,6]"), 6},
                {ArrayUtils.buildArray("[1,2,3,4,5,6]"), 10},
                {ArrayUtils.buildArray("[1,2]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, solution(rods));
    }
}