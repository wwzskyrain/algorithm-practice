/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

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
 * @version : MinimumSizeSubarraySum.java, v 0.1 2021年11月28日 12:15 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumSizeSubarraySum {

    @LetCodeCommit(title = "209. Minimum Size Subarray Sum",
    related = "https://leetcode.com/problems/minimum-window-substring/")
    public int minSubArrayLen(int target, int[] nums) {
        //    用滑动窗口
        int i = 0;
        int l = nums.length;
        // 为什么初始化为l+1而不是l？因为l是特殊case；当然也可以初始化为l+2、3、4
        int min = l + 1;
        for (int j = 0; j < l; j++) {
            target -= nums[j];
            while (target <= 0) {
                min = Math.min(j - i + 1, min);
                target += nums[i++];
            }
        }
        return min % (l + 1);

    }

    @Parameter
    public int   target;
    @Parameter(1)
    public int[] nums;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {7, ArrayUtils.buildArray("[2,3,1,2,4,3]"), 2},
                {4, ArrayUtils.buildArray("[1,4,4]"), 1},
                {11, ArrayUtils.buildArray("[1,1,1,1,1,1,1,1]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minSubArrayLen(target, nums));
    }

}