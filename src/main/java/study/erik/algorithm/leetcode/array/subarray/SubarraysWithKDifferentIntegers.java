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

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : SubarraysWithKDifferentIntegers.java, v 0.1 2022年11月05日 22:15 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SubarraysWithKDifferentIntegers {

    @LetCodeCommit(title = "992. Subarrays with K Different Integers")
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        if (k <= 0) {
            return 0;
        }
        int i = 0, res = 0;
        int l = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < l; j++) {
            // j表示滑动窗口的右边界
            int numJ = nums[j];
            count.put(numJ, count.getOrDefault(numJ, 0) + 1);
            Integer countNumJ = count.get(numJ);
            if (countNumJ == 1) {
                k--;
            }
            while (k < 0) {
                int numI = nums[i++];
                count.put(numI, count.get(numI) - 1);
                int countOfNumI = count.get(numI);
                if (countOfNumI == 0) {
                    k++;
                }
            }
            res += j - i + 1;
        }
        return res;
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
                //{ArrayUtils.buildArray("[1,2,1,2,3]"), 2, 7},
                {ArrayUtils.buildArray("[1,2,1,3,4]"), 3, 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, subarraysWithKDistinct(nums, k));
    }
}