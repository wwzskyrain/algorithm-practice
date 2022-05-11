/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.subarray;

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
 * @version : NumberOfSubarraysWithBoundedMaximum.java, v 0.1 2022年05月11日 20:46 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfSubarraysWithBoundedMaximum {

    @LetCodeCommit(title = "795. Number of Subarrays with Bounded Maximum",
            diff = "m",
            selfRemark = "好题，求子数组的个数。"
                    + "但是需要变通一下。")
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return numSubarrayMax(nums, right) - numSubarrayMax(nums, left - 1);
    }

    public int numSubarrayMax(int[] nums, int max) {
        int c = 0, l = 0;
        for (int num : nums) {
            l = (num <= max ? l + 1 : 0);
            c += l;
        }
        return c;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   left;
    @Parameter(2)
    public int   right;
    @Parameter(3)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,1,4,3]"), 2, 3, 3},
                {ArrayUtils.buildArray("[2,9,2,5,6]"), 2, 8, 7},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numSubarrayBoundedMax(nums, left, right));
    }
}