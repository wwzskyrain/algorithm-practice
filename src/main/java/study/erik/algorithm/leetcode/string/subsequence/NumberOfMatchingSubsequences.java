/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.subsequence;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

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

        Queue<int[]>[] qs = new Queue[26];
        for(int i = 0;i < qs.length; i++) {
            qs[i] = new LinkedList<>();
        }
        for(int i = 0;i < words.length; i++) {
            qs[words[i].charAt(0) - 'a'].add(new int[]{i, 0});
        }
        int ret = 0;
        //只需要遍历一遍s。大家都看着s的指针来更新自己的指针。挺有意思的。
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Queue<int[]> q = qs[c - 'a'];
            int size = q.size();
            while(size-- > 0) {
                int[] arr = q.poll(); // arr[0]是word的idx，arr[1]是当前word的当前字符指针
                if(arr[1] == words[arr[0]].length() - 1) {
                    ret++;
                }else {
                    arr[1]++;
                    qs[words[arr[0]].charAt(arr[1]) - 'a'].add(arr);
                }
            }
        }
        return ret;
    }
}