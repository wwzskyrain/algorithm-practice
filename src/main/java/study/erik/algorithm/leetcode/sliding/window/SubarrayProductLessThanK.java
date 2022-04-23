/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.sliding.window;

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
 * @version : SubarrayProductLessThanK.java, v 0.1 2022年04月23日 10:34 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SubarrayProductLessThanK {

    @LetCodeCommit(title = "713. Subarray Product Less Than K",
            diff = "m",
            selfRemark = "连续子数组的个数，这种计数是有技巧，而技巧在于返璞归真，也就不能算技巧了。"
                    + "这个技巧还是从lee大神处学的。")
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0, j = 0;
        int p = nums[j];
        int sum = 0;
        while (true) {
            if (p < k) {
                // 这一块看不懂吧，哈哈
                sum += j - i + 1;
                j++;
                if (j == nums.length) {
                    break;
                }
                p *= nums[j];
            } else {
                p /= nums[i];
                i++;
                if (i == nums.length) {
                    break;
                }
                if (i > j && i < nums.length) {
                    j = i;
                    p = nums[j];
                }
            }
        }
        return sum;
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
                {ArrayUtils.buildArray("[10,5,2,6]"), 100, 8},
                {ArrayUtils.buildArray("[1,2,3]"), 0, 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numSubarrayProductLessThanK(nums, k));
    }
}