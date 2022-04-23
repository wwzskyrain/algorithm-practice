/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FindSmallestLetterGreaterThanTarget.java, v 0.1 2022年04月23日 5:44 下午 yueyi Exp $
 */
public class FindSmallestLetterGreaterThanTarget {

    @LetCodeCommit(title = "744. Find Smallest Letter Greater Than Target",
            diff = "e",
            selfRemark = "在线手写二分法.")
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0;
        int h = letters.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            // 把==的情况也归并到这里.
            if (target >= letters[m]) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return letters[l % letters.length];
    }
}