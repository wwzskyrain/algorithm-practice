/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : CountTheNumberOfBeautifulSubarrays.java, v 0.1 2023年03月12日 18:32 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CountTheNumberOfBeautifulSubarrays {

    @LetCodeCommit(title = "2588. Count the Number of Beautiful Subarrays")
    public long beautifulSubarrays(int[] nums) {
        Map<Integer, Integer> preXorCountMap = new HashMap<>();
        preXorCountMap.put(0, 1);
        int preXor = 0;
        int ret = 0;
        for (int num : nums) {
            preXor ^= num;
            Integer c = preXorCountMap.getOrDefault(preXor, 0);
            ret += c;
            c++;
            preXorCountMap.put(preXor, c);
        }
        return ret;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public long  expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[4,3,1,2,4]"), 2},
                {ArrayUtils.buildArray("[1,10,4]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, beautifulSubarrays(nums));
    }

}