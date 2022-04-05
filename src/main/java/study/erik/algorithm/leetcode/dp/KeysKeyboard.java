/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : KeysKeyboard.java, v 0.1 2022年04月05日 7:37 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class KeysKeyboard {

    @LetCodeCommit(title = "650. 2 Keys Keyboard",
            diff = "m",
            selfRemark = "可以用dp解，但是有点浪费，虽然优化了一下，只计算一半，但是成绩依然不好，34%."
                    + "讨论区有一个贪心解法，O(logN)的解法，很妙。https://leetcode.com/problems/2-keys-keyboard/discuss/105897/Loop-best-case-log(n)"
                    + "-no-DP-no-extra-space-no-recursion-with-explanation")
    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        int[] dp = new int[(n + 1) / 2 + 1];
        Arrays.fill(dp, 0x7ffffff);
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    int t = (i - j) / j + 1 + dp[j];
                    dp[i] = Math.min(dp[i], t);
                }
            }
        }
        int min = 0x7fffffff;
        for (int i = 1; i < dp.length; i++) {
            if (n % i == 0) {
                int t = (n - i) / i + 1 + dp[i];
                min = Math.min(min, t);
            }
        }
        return min;
    }

    public int solutionWithGreedyStrategy(int n) {
        int s = 0;
        for (int d = 2; d <= n; d++) {
            while (n % d == 0) {
                s += d;
                n /= d;
            }
        }
        return s;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3, 3},
                {1, 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minSteps(n));
    }
}