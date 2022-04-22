/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : TopKFrequentWords.java, v 0.1 2022年04月21日 10:12 下午 yueyi Exp $
 */
public class TopKFrequentWords {

    @LetCodeCommit(title = "692. Top K Frequent Words",
    selfRemark = "很简单的一个题目，统计加排序。可以用桶排序会更高效.")
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> m = new HashMap<>();
        Arrays.stream(words).forEach(word -> m.put(word, m.getOrDefault(word, 0) + 1));
        Comparator<String> c =
                (w1, w2) -> m.get(w1).equals(m.get(w2)) ? w1.compareTo(w2) : m.get(w2) - m.get(w1);
        return m.keySet().stream().sorted(c).limit(k).collect(Collectors.toList());
    }
}