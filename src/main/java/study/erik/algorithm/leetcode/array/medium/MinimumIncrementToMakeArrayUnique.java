/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

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
 * @version : MinimumIncrementToMakeArrayUnique.java, v 0.1 2022年11月18日 14:48 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumIncrementToMakeArrayUnique {

    @LetCodeCommit(title = "945. Minimum Increment to Make Array Unique",
            diff = "m",
            selfRemark = "哇塞，看过题简单想了下感觉还挺麻烦呢，都打算看答案了。"
                    + "然后再试下，一排序就出来了。"
                    + "这是个简单的好题")
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                int t = nums[i - 1] + 1;
                res += t - nums[i];
                nums[i] = t;
            }
        }
        return res;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,2]"), 1},
                {ArrayUtils.buildArray("[3,2,1,2,1,7]"), 6},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minIncrementForUnique(nums));
    }
}