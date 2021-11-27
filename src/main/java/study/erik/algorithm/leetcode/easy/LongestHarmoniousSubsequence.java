/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

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
import java.util.Map.Entry;

/**
 * @author yueyi
 * @version : LongestHarmoniousSubsequence.java, v 0.1 2021年11月26日 11:38 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LongestHarmoniousSubsequence {

    @LetCodeCommit(title = "Longest Harmonious Subsequence")
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer c = map.getOrDefault(num, 0);
            c++;
            map.put(num, c);
        }
        int max = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            Integer preValue = map.get(entry.getKey() - 1);
            if (preValue != null) {
                max = Math.max(preValue + entry.getValue(), max);
            }
            Integer nextValue = map.get(entry.getKey() + 1);
            if (nextValue != null) {
                max = Math.max(nextValue + entry.getValue(), max);
            }
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
                {ArrayUtils.buildArray("1,3,2,2,5,2,3,7"), 5},
                {ArrayUtils.buildArray("1,2,3,4"), 2},
                {ArrayUtils.buildArray("1,1,1,1"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findLHS(nums));
    }
}