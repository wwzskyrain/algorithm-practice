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

import java.util.Arrays;

/**
 * @author yueyi
 * @version : MinimumIncompatibility.java, v 0.1 2023年06月17日 08:17 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumIncompatibility {

    @LetCodeCommit(title = "1681. Minimum Incompatibility")
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int c = n / k;
        //dp[s][i]表示s状态，以i为最后一位的最优值。
        //ans=dp[(1<<n)-1][i], i={0,1,...,n-1}
        int[][] dp = new int[1 << n][n];
        int max = 1_000_000_007;
        for (int[] d : dp) {
            Arrays.fill(d, max);
        }

        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = 0;
        }

        for (int s = 0; s < 1 << n; s++) {
            //遍历每一个状态
            for (int i = 0; i < n; i++) {
                if (((1 << i) & s) == 0) {
                    // i not in s
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (((1 << j) & s) != 0) {
                        //j already in s
                        continue;
                    }
                    int t = (1 << j) | s;
                    if (Integer.bitCount(s) % c == 0) {
                        dp[t][j] = Math.min(dp[t][j], dp[s][i]);
                    } else if (nums[j] > nums[i]) {
                        //只计算nums[j] > nums[i]情况，不用计算nums[j] < nums[i]，因为总有组合中总有顺序的排列——妙哉
                        //也不计算nums[j] = nums[i]的，因为这种不合要求
                        dp[t][j] = Math.min(dp[t][j], dp[s][i] + nums[j] - nums[i]);
                    }
                }

            }
        }
        int ans = Arrays.stream(dp[(1 << n) - 1]).min().getAsInt();
        return ans == max ? -1 : ans;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   k;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,1,4]"), 2, 4},
                {ArrayUtils.buildArray("[6,3,8,1,3,1,2,2]"), 4, 6},
                {ArrayUtils.buildArray("[5,3,3,6,3,3]"), 3, -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minimumIncompatibility(nums, k));
    }

}