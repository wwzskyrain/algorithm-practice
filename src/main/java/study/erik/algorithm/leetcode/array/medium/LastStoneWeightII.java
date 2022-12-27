/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : LastStoneWeightII.java, v 0.1 2022年12月25日 11:53 yueyi Exp $
 */
public class LastStoneWeightII {

    @LetCodeCommit(title = "1049. Last Stone Weight II",
            diff = "m",
            selfRemark = "1.要把题意转化成求给元素分配正和负两个组，使其和的绝对值最小。"
                    + "2.同样也就是求数组中有多少中组合和，其中最与sum/2接近。"
                    + "3.求有多少个组合和，用map可以，用数组也可以。")
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int preSum = 0;
        boolean[] dp = new boolean[sum];
        dp[0] = true;
        for (int stone : stones) {
            preSum += stone;
            for (int i = preSum; i >= stone; i--) {
                dp[i] |= dp[i - stone];
            }
        }
        for (int i = sum / 2; i > 0; i--) {
            if (dp[i]) {
                return sum - i - i;
            }
        }
        return 0;
    }
}