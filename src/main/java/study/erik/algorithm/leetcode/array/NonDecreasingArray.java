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
 * @version : NonDecreasingArray.java, v 0.1 2022年04月17日 10:53 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NonDecreasingArray {

    @LetCodeCommit(title = "665. Non-decreasing Array",
            diff = "m",
            selfRemark = "直接找这种模式."
                    + "算法看上去笨了点，但是不难想到，而且效果不错。84%和99%")
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                count++;
                index = i;
                if (count > 1) {
                    return false;
                }
            }
        }
        if (index == 0) {
            return true;
        }
        if (index == nums.length - 1 || index == 1) {
            return true;
        }
        if (nums[index - 1] <= nums[index + 1]) {
            return true;
        }
        if (nums[index - 2] <= nums[index]) {
            return true;
        }
        return false;
    }

    @Parameter
    public int[]   nums;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3]"), true},
                {ArrayUtils.buildArray("[5,7,1,8]"), true},
                {ArrayUtils.buildArray("[3,4,2,3]"), false},
                {ArrayUtils.buildArray("[4,2,3]"), true},
                {ArrayUtils.buildArray("[4,2,1]"), false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, checkPossibility(nums));
    }
}