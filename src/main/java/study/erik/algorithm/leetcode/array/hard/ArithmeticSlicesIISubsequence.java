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
import study.erik.algorithm.leetcode.medium.H_Index;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : ArithmeticSlicesIISubsequence.java, v 0.1 2022年02月08日 11:16 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ArithmeticSlicesIISubsequence {

    @LetCodeCommit(title = "446. Arithmetic Slices II - Subsequence")
    public int numberOfArithmeticSlices(int[] nums) {
        int result = 0;
        HashMap<Long, Integer>[] maps = new HashMap[nums.length];
        for (int i = 0; i < maps.length; i++) {maps[i] = new HashMap<>();}
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - (long) nums[j];
                Integer subsequences = maps[j].getOrDefault(diff, 0);
                // 这里需加个1，表示从nums[j]到nums[i]本身就是一个只有两个元素的等差数列。
                maps[i].put(diff, maps[i].getOrDefault(diff, 0) + subsequences + 1);
                result += subsequences;
            }
        }
        return result;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,4,6,8,10]"), 7},
                {ArrayUtils.buildArray("[7,7,7,7,7]"), 16},
                {ArrayUtils.buildArray("[0,2,2,3,4]"), 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numberOfArithmeticSlices(nums));
    }
}