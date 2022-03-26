/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ReverseWordsInAStringIII.java, v 0.1 2022年03月25日 11:17 上午 yueyi Exp $
 */
public class ReverseWordsInAStringIII {
    @LetCodeCommit(title = "557. Reverse Words in a String III",
            selfRemark = "easy的题目还要求什么呢")
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int l = 0, f = 0;
        while (f <= chars.length) {
            if (f == chars.length || chars[f] == ' ') {
                int r = f - 1;
                while (l < r) {
                    char t = chars[l];
                    chars[l++] = chars[r];
                    chars[r--] = t;
                }
                f++;
                l = f;
            } else {
                f++;
            }
        }
        return String.valueOf(chars);
    }
}