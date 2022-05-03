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
 * @version : FindPivotIndex.java, v 0.1 2022年05月03日 10:47 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindPivotIndex {

    @LetCodeCommit(title = "724. Find Pivot Index",
            selfRemark = "算个不错的题，写起来比较麻烦而已.")
    public int pivotIndex(int[] nums) {
        int[] preSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            preSum[i] = nums[i] + (i > 0 ? preSum[i - 1] : 0);
        }

        int[] subSum = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            subSum[i] = nums[i] + (i < nums.length - 1 ? subSum[i + 1] : 0);
        }

        for (int i = 0; i < preSum.length; i++) {
            int preS = (i > 0 ? preSum[i - 1] : 0);
            int subS = (i < subSum.length - 1 ? subSum[i + 1] : 0);
            if (preS == subS) {
                return i;
            }
        }
        return -1;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,7,3,6,5,6]"), 3},
                {ArrayUtils.buildArray("[1,2,3]"), -1},
                {ArrayUtils.buildArray("[2,1,-1]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, pivotIndex(nums));
    }
}