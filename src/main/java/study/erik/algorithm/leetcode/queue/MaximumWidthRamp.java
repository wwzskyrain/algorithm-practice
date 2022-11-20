/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.queue;

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
 * @version : MaximumWidthRamp.java, v 0.1 2022年11月20日 11:05 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumWidthRamp {

    @LetCodeCommit(title = "962. Maximum Width Ramp")
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        // 单调递减栈
        int[] stack = new int[n];
        int top = 0;
        stack[top++] = 0;
        int max = 0;
        for (int i = 1; i < n; i++) {
            int peek = top - 1;
            int num = nums[i];
            if (nums[stack[peek]] > num) {
                // 递减了，进栈吧
                stack[top++] = i;
                continue;
            }
            while (peek >= 0 && nums[stack[peek]] <= num) {
                peek--;
            }
            // 计算 width
            max = Math.max(max, i - stack[peek + 1]);
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
                {ArrayUtils.buildArray("[3,4,1,2]"), 1},
                {ArrayUtils.buildArray("[6,0,8,2,1,5]"), 4},
                {ArrayUtils.buildArray("[9,8,1,0,1,9,4,0,4,1]"), 7},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxWidthRamp(nums));
    }

}