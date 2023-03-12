/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : LongestArithmeticSubsequenceOfGivenDifference.java, v 0.1 2023年03月12日 14:17 yueyi Exp $
 */
public class LongestArithmeticSubsequenceOfGivenDifference {

    @LetCodeCommit(title = "1218. Longest Arithmetic Subsequence of Given Difference",
            selfRemark = "凭感觉写的，竟然都通过了，虽然才27%")
    public int longestSubsequence(int[] arr, int difference) {
        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            int lastV = v - difference;
            Integer preLength = map.getOrDefault(lastV, 0);
            Integer curLength = map.getOrDefault(v, 1);
            curLength = Math.max(preLength + 1, curLength);
            map.put(v, curLength);
            max = Math.max(max, curLength);
        }
        return max;
    }

}