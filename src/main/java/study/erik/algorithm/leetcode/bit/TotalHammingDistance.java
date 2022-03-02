/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

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
 * @version : TotalHammingDistance.java, v 0.1 2022年03月01日 10:10 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class TotalHammingDistance {

    @LetCodeCommit(title = "477. Total Hamming Distance",
            selfRemark = "这是个位技巧题目，思路还是换一个维度")
    public int totalHammingDistance(int[] nums) {

        int total = 0;
        int[] oneBitCounts = new int[31];
        for (int num : nums) {
            for (int i = 0; i < oneBitCounts.length; i++) {
                oneBitCounts[i] += (num >> i) & 1;
            }
        }
        for (int oneBitCount : oneBitCounts) {
            total += oneBitCount * (nums.length - oneBitCount);
        }
        return total;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[4,14,2]"), 6},
                {ArrayUtils.buildArray("[4,14,4]"), 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, totalHammingDistance(nums));
    }
}