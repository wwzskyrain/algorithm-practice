/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : ContiguousArray.java, v 0.1 2021年06月03日 8:37 上午 yueyi Exp $
 */
public class ContiguousArray {

    @LetCodeCommit(title = "Contiguous Array")
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                preSum--;
            } else {
                preSum++;
            }
            Integer firstIndex = map.get(preSum);
            if (firstIndex != null) {
                max = Math.max(max, i - firstIndex);
            } else {
                map.put(preSum, i);
            }
        }
        return max;
    }

    @Test
    public void test_1() {
        int[] nums = {0, 1};
        int maxLength = findMaxLength(nums);
        Assert.assertEquals(2, maxLength);
    }

    @Test
    public void test_2() {
        int[] nums = {0, 1, 0};
        int maxLength = findMaxLength(nums);
        Assert.assertEquals(2, maxLength);
    }
}