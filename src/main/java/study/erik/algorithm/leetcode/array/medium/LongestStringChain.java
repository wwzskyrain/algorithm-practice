/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : LongestStringChain.java, v 0.1 2022年12月19日 09:18 yueyi Exp $
 */
public class LongestStringChain {

    @LetCodeCommit(title = "1048. Longest String Chain")
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparing(String::length));
        Map<String, Integer> preMap = new HashMap<>();
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); i++) {
                String pre = word.substring(0, i) + word.substring(i + 1);
                Integer time = preMap.getOrDefault(pre, 0);
                time++;
                best = Math.max(best, time);
            }
            preMap.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }
}