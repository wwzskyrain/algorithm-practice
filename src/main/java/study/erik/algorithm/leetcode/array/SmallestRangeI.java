/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

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
 * @version : SmallestRangeI.java, v 0.1 2022年10月23日 14:59 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SmallestRangeI {

    @LetCodeCommit(title = "908. Smallest Range I")
    public int smallestRangeI(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return Math.max(max - k - min - k, 0);
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
                {ArrayUtils.buildArray("[1]"), 0, 0},
                {ArrayUtils.buildArray("[0,10]"), 2, 6},
                {ArrayUtils.buildArray("[1,3,6]"), 3, 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, smallestRangeI(nums, k));
    }

}