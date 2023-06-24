/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.greedy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumScoreOfAGoodSubarray.java, v 0.1 2023年06月25日 07:14 yueyi Exp $
 */
public class MaximumScoreOfAGoodSubarray {

    @LetCodeCommit(title = "1793. Maximum Score of a Good Subarray",
            selfRemark = "这个贪心策略不太好证明的。"
                    + "贪心算法，要比全局遍历O(n^2)的算法优，优秀在于不用遍历全部的解。"
                    + "但是难点在于证明这个贪心策略。"
                    + "学习到了：在每日一题上：见下一个解法")
    public int maximumScore(int[] nums, int k) {
        int max = nums[k];
        int i = k;
        int j = k;
        int n = nums.length;
        int curMin = nums[k];
        while (i > 0 || j < n - 1) {
            if (i == 0) {
                j++;
            } else if (j == n - 1) {
                i--;
            } else if (nums[i - 1] < nums[j + 1]) {
                //向较大值拓展，先不要较小值，因为按住不动
                j++;
            } else {
                i--;
            }
            curMin = Math.min(curMin, Math.min(nums[i], nums[j]));
            max = Math.max(max, curMin * (j - i + 1));
        }
        return max;
    }

    //这个解法是更直观更容易解释的。
    public int maximumScoreWithExplain(int[] nums, int k) {
        int max = nums[k];
        int i = k;
        int j = k;
        int n = nums.length;
        int curMin = nums[k];
        while (i >= 0 || j < n) {
            //每一轮都极力扩展以当前curMin为最小值的最长子数组。这样就不会错过最优值了。
            while (i >= 0 && nums[i] >= curMin) {
                i--;
            }
            while (j < n && nums[j] >= curMin) {
                j++;
            }
            max = Math.max(max, curMin * (j - i - 1));
            curMin = Math.max(i >= 0 ? nums[i] : 0, j < n ? nums[j] : 0);
        }
        return max;
    }

}