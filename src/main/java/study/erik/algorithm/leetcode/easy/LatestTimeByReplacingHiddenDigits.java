/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LatestTimeByReplacingHiddenDigits.java, v 0.1 2021年07月24日 11:33 下午 yueyi Exp $
 */
public class LatestTimeByReplacingHiddenDigits {

    @LetCodeCommit(title = "Latest Time by Replacing Hidden Digits",
            selfRemark = "easy, 练手都不配，大一学生的都能做")
    public String maximumTime(String time) {
        char[] chars = time.toCharArray();
        if (time.startsWith("?")) {
            if (chars[1] == '?') {
                chars[0] = '2';
                chars[1] = '3';
            } else {
                if (chars[1] - '0' > 3) {
                    chars[0] = '1';
                } else {
                    chars[0] = '2';
                }
            }
        } else if (chars[1] == '?') { // x?
            if (chars[0] - '0' < 2) {
                chars[1] = '9';
            } else {
                chars[1] = '3';
            }
        }

        if (chars[3] == '?') {
            chars[3] = '5';
        }
        if (chars[4] == '?') {
            chars[4] = '9';
        }
        return String.valueOf(chars);
    }

}