/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.subsequence;

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
 * @version : WiggleSubsequence.java, v 0.1 2021年12月01日 10:51 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class WiggleSubsequence {

    @LetCodeCommit(title = "376. Wiggle Subsequence")
    public int wiggleMaxLength(int[] nums) {
        int[] dpUp = new int[nums.length];
        int[] dpDown = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dpDown[i] = 1;
            dpUp[i] = 1;
            max = Math.max(max, dpDown[i]);
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dpDown[j] + 1 > dpUp[i]) {
                    dpUp[i] = dpDown[j] + 1;
                }
                if (nums[j] > nums[i] && dpUp[j] + 1 > dpDown[i]) {
                    dpDown[i] = dpUp[j] + 1;
                }
            }
            max = Math.max(max, dpUp[i]);
            max = Math.max(max, dpDown[i]);
        }
        return max;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,7,4,9,2,5]"), 6},
                {ArrayUtils.buildArray("[1,17,5,10,13,15,10,5,16,8]"), 7},
                {ArrayUtils.buildArray("[1,2,3,4,5,6,7,8,9]"), 2},
                {ArrayUtils.buildArray("[84]"), 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, wiggleMaxLength(nums));
    }

}