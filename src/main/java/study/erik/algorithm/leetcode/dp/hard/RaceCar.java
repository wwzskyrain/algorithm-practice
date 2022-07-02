/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RaceCar.java, v 0.1 2022年07月02日 20:32 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RaceCar {

    @LetCodeCommit(title = "818. Race Car",
            diff = "h",
            selfRemark = "这个解法堪称经典，来自lee大神。"
                    + "todo-看一个状态机的解法："
                    + "https://leetcode.com/problems/race-car/discuss/142360/Logical-Thinking-with-Clean-Java-Code")
    public int racecar(int target) {
        if (dp[target] > 0) {
            return dp[target];
        }
        int n = (int) (Math.log(target) / Math.log(2)) + 1;
        if (1 << n == target + 1) {
            dp[target] = n;
        } else {
            dp[target] = racecar((1 << n) - 1 - target) + n + 1;
            for (int m = 0; m < n - 1; m++) {
                dp[target] = Math.min(dp[target],
                        racecar(target - (1 << (n - 1)) + (1 << m)) + n - 1 + 1 + m + 1);
            }
        }
        return dp[target];
    }

    int[] dp = new int[10002];

    @Parameter
    public int target;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3, 2},
                {6, 5},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, racecar(target));
    }
}