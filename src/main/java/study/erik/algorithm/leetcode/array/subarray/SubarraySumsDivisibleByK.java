/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.subarray;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : SubarraySumsDivisibleByK.java, v 0.1 2022年11月25日 22:05 yueyi Exp $
 */
public class SubarraySumsDivisibleByK {

    @LetCodeCommit(title = "974. Subarray Sums Divisible by K")
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum = (preSum + num % k + k) % k;
            count+=map.getOrDefault(preSum, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

}