/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FindTheDifference.java, v 0.1 2021年12月21日 9:49 上午 yueyi Exp $
 */
public class FindTheDifference {

    @LetCodeCommit(title = "389. Find the Difference",
            selfRemark = "e题，又是一个文不加点的。")
    public char findTheDifference(String s, String t) {
        int[] map = new int[26];
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']--;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 1) {
                return (char) (i + 'a');
            }
        }
        throw new RuntimeException();
    }

}