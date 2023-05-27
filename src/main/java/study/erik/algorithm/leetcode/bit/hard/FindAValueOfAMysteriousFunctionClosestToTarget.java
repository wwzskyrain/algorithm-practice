/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : FindAValueOfAMysteriousFunctionClosestToTarget.java, v 0.1 2023年05月27日 12:14 yueyi Exp $
 */
public class FindAValueOfAMysteriousFunctionClosestToTarget {

    @LetCodeCommit(title = "1521. Find a Value of a Mysterious Function Closest to Target",
            selfRemark = "这个有点算bit题目，也确实是数组的子数组题目。"
                    + "数组的子数组题目比较多，要结合具体的问题来实现。"
                    + "这类题的好处，第一解法是有的，最差是O(n2)呗。"
                    + "而这里就是O(n2)的算法的优化。"
                    + "优化点是在于用空间换时间，而且这里换也不是说还换就能换的，"
                    + "而是因为子数组的连续与，其值空间是不大的，即有很大概率重合。"
                    + "这才通过记忆法节省了重复计算，才用空间换了。尔其算法思路"
                    + "还是二层循环。"
                    + ""
                    + "启示：这个题目之所以写下来是因为它确实有这么有点技巧的。")
    public int closestToTarget(int[] arr, int t) {
        int n = arr.length;
        Set<Integer> set = new HashSet<>();
        set.add(arr[0]);
        int ret = Math.abs(arr[0] - t);
        for (int i = 1; i < arr.length; i++) {
            int d = arr[i];
            ret = Math.min(ret, Math.abs(d - t));
            Set<Integer> tempSet = new HashSet<>();
            for (Integer v : set) {
                int newV = v & d;
                ret = Math.min(ret, Math.abs(newV - t));
                tempSet.add(v & d);
            }
            tempSet.add(d);
            set = tempSet;
        }
        return ret;
    }

}