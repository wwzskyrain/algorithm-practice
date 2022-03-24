/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : KDiffPairsInAnArray.java, v 0.1 2022年03月24日 7:05 下午 yueyi Exp $
 */
public class KDiffPairsInAnArray {

    @LetCodeCommit(title = "532. K-diff Pairs in an Array")
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.compute(num, (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
        }
        int ret = 0;
        for (Integer key : countMap.keySet()) {
            if (k > 0) {
                ret = ret + (countMap.containsKey(key + k) ? 1 : 0);
            } else if (k == 0) {
                ret = ret + (countMap.getOrDefault(key, 0) > 1 ? 1 : 0);
            }
        }
        return ret;
    }
}