/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.subsequence;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yueyi
 * @version : NumberOfMatchingSubsequences.java, v 0.1 2022年05月11日 17:24 yueyi Exp $
 */
public class NumberOfMatchingSubsequences {

    @LetCodeCommit(title = "792. Number of Matching Subsequences",
            diff = "m",
            selfRemark = "单纯就考subSequence?"
                    + "看的diss区的解法，直接就直接写了")
    public int numMatchingSubseq(String s, String[] words) {

        Map<Character, Deque<String>> map = new HashMap<>();
        for (String word : words) {
            Deque<String> queue = map.getOrDefault(word.charAt(0), new LinkedList<>());
            queue.addLast(word);
            map.put(word.charAt(0), queue);
        }
        int count = 0;
        for (char c : s.toCharArray()) {
            // 为目标word逐个与s匹配.
            Deque<String> queue = map.getOrDefault(c, new LinkedList<>());
            int size = queue.size();
            while (size-- > 0) {
                String subStr = queue.removeFirst();
                if (subStr.length() == 1) {
                    count++;
                    continue;
                }
                char c1 = subStr.charAt(1);
                Deque<String> q = map.getOrDefault(c1, new LinkedList<>());
                q.addLast(subStr.substring(1));
                map.put(c1, q);
            }
        }
        return count;
    }
}