/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : LongestWordInDictionary.java, v 0.1 2022年04月23日 1:46 下午 yueyi Exp $
 */
public class LongestWordInDictionary {

    @LetCodeCommit(title = "720. Longest Word in Dictionary",
            diff = "m",
            selfRemark = "题意模糊，不算个好题。")
    public String longestWord(String[] words) {
        Arrays.sort(words);
        String ret = "";
        Set<String> set = new HashSet<>();
        for (String word : words) {
            if (word.length() == 1 || set.contains(word.substring(0, word.length() - 1))) {
                ret = ret.length() < word.length() ? word : ret;
                set.add(ret);
            }
        }
        return ret;
    }
}