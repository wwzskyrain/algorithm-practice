/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : DivideArrayInSetsOfKConsecutiveNumbers.java, v 0.1 2023年04月16日 08:14 yueyi Exp $
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {

    @LetCodeCommit(title = "1296. Divide Array in Sets of K Consecutive Numbers",
            selfRemark = "这个题目，有点技巧的。")
    public boolean isPossibleDivide(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 记录这个位置开始了几个
        Queue<Integer> start = new LinkedList<>();
        int lastCheck = -1;
        int open = 0;
        for (Integer n : map.keySet()) {
            if (open > 0 && n > lastCheck + 1 || open > map.get(n)) {return false;}
            start.add(map.get(n) - open);
            lastCheck = n;
            open = map.get(n);
            if (start.size() == k) {
                open -= start.remove();
            }
        }
        return open == 0;
    }

}