/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : MinimumCostToCutAStick.java, v 0.1 2023年05月27日 18:47 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumCostToCutAStick {

    @LetCodeCommit(title = "1547. Minimum Cost to Cut a Stick",
            diff = "h",
            selfRemark = "合并相邻的石子，dp题，也可以top-bottom")
    public int minCost(int n, int[] cuts) {
        List<Integer> list = new LinkedList<>();
        for (int cut : cuts) {
            list.add(cut);
        }
        list.add(0);
        list.add(n);
        Collections.sort(list);
        int k = list.size();
        int[][] dp = new int[k][k];
        //dp[i][j]表示把list中第i到第j的元素merge的最小值。
        // 从d=2开始而不是从d=1开始，因为木头是成段的，不是点
        for (int d = 2; d < k; d++) {
            for (int i = 0; i + d < dp.length; i++) {
                dp[i][i + d] = 1000000000;
                int v = list.get(i + d) - list.get(i);
                for (int j = i + 1; j < i + d; j++) {
                    dp[i][i + d] = Math.min(dp[i][i + d], dp[i][j] + dp[j][i + d] + v);
                }
            }
        }
        return dp[0][k - 1];
    }

    //很明显，这种递归调用的方法效率很低
    public int minCostWithTopToBottom(int n, int[] cuts) {
        List<Integer> list = new LinkedList<>();
        for (int cut : cuts) {
            list.add(cut);
        }
        list.add(0);
        list.add(n);
        Collections.sort(list);
        int k = list.size();
        Integer[][] dp = new Integer[k][k];
        return dfs(0, k - 1, dp, list);
    }

    public int dfs(int i, int j, Integer[][] dp, List<Integer> list) {
        if (i + 1 == j) {
            dp[i][j] = 0;
            return 0;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        int ret = 100000000;
        int thisMergeValue = list.get(j) - list.get(i);
        for (int k = i + 1; k < j; k++) {
            ret = Math.min(ret, dfs(i, k, dp, list) + dfs(k, j, dp, list) + thisMergeValue);
        }
        dp[i][j] = ret;
        return ret;
    }

    @Parameter
    public int   n;
    @Parameter(1)
    public int[] cuts;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {7, ArrayUtils.buildArray("[1,3,4,5]"), 16},
                {7, ArrayUtils.buildArray("[1,3,4,5]"), 16},
                {9, ArrayUtils.buildArray("[5,6,1,4,2]"), 22},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minCostWithTopToBottom(n, cuts));
    }
}