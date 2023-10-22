/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : Sum4II.java, v 0.1 2022年02月26日 8:59 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class Sum4II {

    @LetCodeCommit(title = "454. 4Sum II")
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sum2CountMap12 = new HashMap<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                int key = num1 + num2;
                sum2CountMap12.merge(key, 1, Integer::sum);
            }
        }
        int ret = 0;
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                ret += sum2CountMap12.getOrDefault(-(num3 + num4), 0);
            }
        }
        return ret;
    }

    @Parameter
    public int[] nums1;
    @Parameter(1)
    public int[] nums2;
    @Parameter(2)
    public int[] nums3;
    @Parameter(3)
    public int[] nums4;
    @Parameter(4)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2]"),
                        ArrayUtils.buildArray("[-2,-1]"),
                        ArrayUtils.buildArray("[-1,2]"),
                        ArrayUtils.buildArray("[0,2]"), 2},
                {ArrayUtils.buildArray("[0]"),
                        ArrayUtils.buildArray("[0]"),
                        ArrayUtils.buildArray("[0]"),
                        ArrayUtils.buildArray("[0]"), 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, fourSumCount(nums1, nums2, nums3, nums4));
    }
}