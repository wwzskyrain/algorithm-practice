/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
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
 * @version : RotateArray.java, v 0.1 2021年12月14日 11:23 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RotateArray {

    @LetCodeCommit(title = "189. Rotate Array")
    public void rotate(int[] nums, int k) {
        if (nums.length == 0) {
            return;
        }
        k = k % nums.length;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            swap(nums, l++, h--);
        }
        l = 0;
        h = k - 1;
        while (l < h) {
            swap(nums, l++, h--);
        }
        l = k;
        h = nums.length - 1;
        while (l < h) {
            swap(nums, l++, h--);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   k;
    @Parameter(2)
    public int[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3,4,5,6,7]"), 3, ArrayUtils.buildArray("[5,6,7,1,2,3,4]")},
                {ArrayUtils.buildArray("[-1,-100,3,99]"), 2, ArrayUtils.buildArray("[3,99,-1,-100]")},

        };
    }

    @Test
    public void test_() {
        rotate(nums, k);
        Assert.assertArrayEquals(expect, nums);
    }

}