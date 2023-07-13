/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.array;

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
 * @version : MinimumXORSumOfTwoArrays.java, v 0.1 2023年07月08日 16:58 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumXORSumOfTwoArrays {

    @LetCodeCommit(title = "1879. Minimum XOR Sum of Two Arrays",
            diff = "h",
            selfRemark = "这个题目思路并不难，我指的是第二种思路。"
                    + "先说第一种思路——傻子也能想到。就是遍历nums2每一种排列。估计也行，因为数据量不大。"
                    + "再说第二种思路，那就是很正常的dfs：num1[0]与num2[0],num2[1],...,num2[n-1]。"
                    + "然后每一种情况都dfs下去。这里难点在于对当前遍历的状态的表示，可以是一个boolean[n]的数组。"
                    + "这里用了一个mask.")
    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] dp = new Integer[1 << n];
        return dfs(dp, nums1, nums2, 0, 0);
    }

    public int dfs(Integer[] dp, int[] num1, int[] num2, int mask, int i) {
        if (dp[mask] == null) {
            if (i == num1.length) {
                return 0;
            }
            dp[mask] = Integer.MAX_VALUE;
            for (int j = 0; j < num2.length; j++) {
                if ((mask & (1 << j)) == 0) {
                    dp[mask] = Math.min(dp[mask], (num1[i] ^ num2[j]) + dfs(dp, num1, num2, mask | (1 << j), i + 1));
                }
            }
        }
        return dp[mask];
    }

    @Parameter
    public int[] nums1;
    @Parameter(1)
    public int[] nums2;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[72,97,8,32,15]"), ArrayUtils.buildArray("[63,97,57,60,83]"), 152},
                {ArrayUtils.buildArray("[1,0,3]"), ArrayUtils.buildArray("[5,3,4]"), 8},
                {ArrayUtils.buildArray("[1,2]"), ArrayUtils.buildArray("[2,3]"), 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minimumXORSum(nums1, nums2));
    }

}