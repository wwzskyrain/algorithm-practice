/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.slidewindow;

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
 * @version : BinarySubarraysWithSum.java, v 0.1 2022年11月05日 15:20 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BinarySubarraysWithSum {

    @LetCodeCommit(title = "930. Binary Subarrays With Sum",
            selfRemark = "前缀和能够直接求出子数组的个数，还是听厉害的。")
    public int numSubarraysWithSum(int[] nums, int goal) {
        return solutionWithPreSum(nums, goal);
    }

    public int solutionWithPreSum(int[] nums, int goal) {
        int n = nums.length;
        // preSum的最大值也就n+1了
        int[] countForPreSum = new int[n + 1];
        countForPreSum[0] = 1; //当 preSum = goal时，也算一个
        int preSum = 0;
        int res = 0;
        for (int num : nums) {
            preSum += num;
            if (preSum >= goal) {
                res += countForPreSum[preSum - goal];
            }
            //不能写在前，因为刚加入的这个不能算
            countForPreSum[preSum]++;
        }
        return res;
    }

    public int solutionWithSlidWindow(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    /**
     * 返回子数组个数，子数组的和最大为S
     *
     * @param A 数组
     * @param S S
     * @return 返回子数组个数，子数组的和最大为S
     */
    public int atMost(int[] A, int S) {
        if (S < 0) {
            return 0;
        }
        int res = 0, i = 0, n = A.length;
        for (int j = 0; j < n; j++) {
            S -= A[j];
            while (S < 0) {
                S += A[i++];
            }
            //此时[i...j]是满足S边界的。
            res += j - i + 1;
        }
        return res;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int goal;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                //{ArrayUtils.buildArray("[1,1,1,1,1]"), 5, 1},
                {ArrayUtils.buildArray("[1,0,1,0,1]"), 2, 4},
                {ArrayUtils.buildArray("[0,0,0,0,0]"), 0, 15},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numSubarraysWithSum(nums, goal));
    }
}