/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumSubarraySumWithOneDeletion.java, v 0.1 2023年02月20日 07:58 yueyi Exp $
 */
public class MaximumSubarraySumWithOneDeletion {

    @LetCodeCommit(title = "1186. Maximum Subarray Sum with One Deletion",
            selfRemark = "这又是一个最大子数组和的变体题目。"
                    + "可是我还是没想出来，最近确实脑子有点不太灵光了。"
                    + "这里左边加右边并不是左边的max——preSum和右边的max——preSum，而是左边的dp和右边的dp。"
                    + "不过也差不多啦。")
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int[] dp1 = new int[n];
        int max = arr[0];
        dp1[0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp1[i] = Math.max(arr[i], dp1[i - 1] + arr[i]);
            max = Math.max(max, dp1[i]);
        }

        int[] dp2 = new int[n];
        dp2[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp2[i] = Math.max(arr[i], dp2[i + 1] + arr[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (arr[i] < 0) {
                max = Math.max(max, dp1[i - 1] + dp2[i + 1]);
            }
        }
        return max;
    }

}