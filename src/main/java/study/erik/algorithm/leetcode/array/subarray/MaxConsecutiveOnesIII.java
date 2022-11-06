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
 * @version : MaxConsecutiveOnesIII.java, v 0.1 2022年11月06日 07:45 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaxConsecutiveOnesIII {

    @LetCodeCommit(title = "1004. Max Consecutive Ones III",
            selfRemark = "lee215大神的滑动窗口")
    public int longestOnes(int[] nums, int k) {
        // 这比较简单
        // 转化为求最长子数组，其最多有k个0
        int i = 0, n = nums.length, res = 0;
        int j = 0;
        for (; j < n; j++) {
            if (nums[j] == 0) {
                k--;
            }
            if (k < 0) {
                if (nums[i] == 0) {
                    k++;
                }
                i++;
            }
        }
        return j - i;
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
                {ArrayUtils.buildArray("[1,1,1,0,0,0,1,1,1,1,0]"), 2, 6},
                {ArrayUtils.buildArray("[0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1]"), 3, 10},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestOnes(nums, k));
    }
}