/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : NumberOfSquarefulArrays.java, v 0.1 2022年12月03日 19:07 yueyi Exp $
 */
@LetCodeCommit(title = "996. Number of Squareful Arrays")
@RunWith(Parameterized.class)
public class NumberOfSquarefulArrays {

    int count = 0;

    public int numSquarefulPerms(int[] nums) {
        backtrace(nums, 0);
        return count;
    }

    public void backtrace(int[] nums, int i) {
        if (i == nums.length - 1) {
            if (isSqart(nums[i-1], nums[i])) {
                count++;
            }
            return;
        }
        for (int j = i; j < nums.length; j++) {
            boolean isRepeat = false;
            for (int k = i; k < j; k++) {
                if (nums[k] == nums[j]) {
                    isRepeat = true;
                    break;
                }
            }
            if (isRepeat) {
                continue;
            }
            if (i > 0) {
                if (isSqart(nums[i-1], nums[j])) {
                    swap(nums, i, j);
                    backtrace(nums, i + 1);
                    swap(nums, i, j);
                }
            } else {
                swap(nums, i, j);
                backtrace(nums, i + 1);
                swap(nums, i, j);
            }
        }
    }

    private boolean isSqart(int a, int b) {
        int sqrt = (int) Math.sqrt(a + b);
        return sqrt * sqrt == (a + b);
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,1]"), 0},
                {ArrayUtils.buildArray("[1,17,8]"), 2}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numSquarefulPerms(nums));
    }

}