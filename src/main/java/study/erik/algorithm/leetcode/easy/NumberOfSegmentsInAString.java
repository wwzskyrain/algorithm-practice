/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumberOfSegmentsInAString.java, v 0.1 2022年02月24日 9:18 上午 yueyi Exp $
 */
public class NumberOfSegmentsInAString {

    @LetCodeCommit(title = "434. Number of Segments in a String")
    public int countSegments(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        boolean isSpace = true;
        for (char c : chars) {
            if (c != ' ') {
                if (isSpace) {
                    count++;
                }
                isSpace = false;
            } else {
                isSpace = true;
            }
        }
        return count;
    }
}