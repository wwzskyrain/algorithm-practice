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
 * @version : LongestWellPerformingInterval.java, v 0.1 2023年01月18日 23:41 yueyi Exp $
 */
public class LongestWellPerformingInterval {

    @LetCodeCommit(title = "1124. Longest Well-Performing Interval",
            diff = "m",
            selfRemark = "前缀和方法")
    public int longestWPI(int[] hours) {
        int res = 0, score = 0, n = hours.length;
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            score += hours[i] > 8 ? 1 : -1;
            if (score > 0) {
                res = i + 1;
            } else {
                seen.putIfAbsent(score, i);
                if (seen.containsKey(score - 1)) {res = Math.max(res, i - seen.get(score - 1));}
            }
        }
        return res;
    }
}