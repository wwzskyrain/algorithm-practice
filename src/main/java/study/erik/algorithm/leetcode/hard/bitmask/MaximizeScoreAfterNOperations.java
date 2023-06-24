/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.bitmask;

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
 * @version : MaximizeScoreAfterNOperations.java, v 0.1 2023年06月24日 21:22 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximizeScoreAfterNOperations {

    @LetCodeCommit(title = "1799. Maximize Score After N Operations",
            diff = "h",
            selfRemark = "知道是bitmask解法，但是还没有研究出具体的解法。"
                    + "看了花花酱的视频，根据其递归公式，很快就写出来了。")
    public int maxScore(int[] nums) {
        int n = nums.length / 2;
        Integer[] dp = new Integer[1 << 2 * n];
        return dfs((1 << 2 * n) - 1, dp, n * 2, nums);
    }

    /**
     * dp
     *
     * @param s    当前待计算的nums对应的状态值
     * @param dp   备忘录
     * @param n    n
     * @param nums nums
     * @return dp
     */
    public int dfs(int s, Integer[] dp, int n, int[] nums) {
        if (s == 0) {
            return 0;
        }
        if (dp[s] != null) {
            return dp[s];
        }
        dp[s] = 0;
        int bitNum = Integer.bitCount(s);
        int yin = (n - bitNum) / 2 + 1; //计算出当前因子
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //调出两个在s中的数来计算；这里是枚举所有的i,j的组合
                if ((s & (1 << i)) != 0 && (s & (1 << j)) != 0) {
                    int nextState = s ^ (1 << i) ^ (1 << j);
                    dp[s] = Math.max(dp[s], dfs(nextState, dp, n, nums) + yin * gcd(nums[i], nums[j]));
                }
            }
        }
        return dp[s];
    }

    public int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{ArrayUtils.buildArray("[1,2]"), 1},
                {ArrayUtils.buildArray("[3,4,6,8]"), 11},
                {ArrayUtils.buildArray("[1,2,3,4,5,6]"), 14},
                {ArrayUtils.buildArray("[1,2]"), 1},
                {ArrayUtils.buildArray("[1,2]"), 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxScore(nums));
    }
}