/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : BinarySubarraysWithSum.java, v 0.1 2021年07月08日 1:06 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BinarySubarraysWithSum {

    @LetCodeCommit(title = "Binary Subarrays With Sum")
    public int numSubarraysWithSum(int[] nums, int goal) {
        return resolveWithDp(nums, goal);
    }

    public int resolveWithDp(int[] nums, int goal) {

        return 0;

    }

    public int numSubarraysWithSum1(int[] nums, int t) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int r = sum[i + 1], l = r - t;
            ans += map.getOrDefault(l, 0);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return ans;
    }


    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   goal;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {1, 0, 1, 0, 1}, 2, 4},
                {new int[] {0, 0, 0, 0, 0}, 0, 15}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numSubarraysWithSum(nums, goal));
    }

}