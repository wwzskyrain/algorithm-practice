/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ShiftingLetters.java, v 0.1 2022年05月22日 11:48 yueyi Exp $
 */
public class ShiftingLetters {

    @LetCodeCommit(title = "848. Shifting Letters",
            diff = "m",
            selfRemark = "好水的一个题呀")
    public String shiftingLetters(String s, int[] shifts) {

        int[] postSum = new int[shifts.length];
        postSum[shifts.length - 1] = shifts[shifts.length - 1];
        for (int i = shifts.length - 2; i >= 0; i--) {
            postSum[i] = (postSum[i + 1] + shifts[i]) % 26;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int newChar = chars[i] + postSum[i];
            if (newChar > 'z') {
                newChar -= 26;
            }
            chars[i] = (char) newChar;
        }
        return String.valueOf(chars);
    }
}