/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ReorganizeString.java, v 0.1 2022年05月04日 16:43 yueyi Exp $
 */
public class ReorganizeString {

    @LetCodeCommit(title = "767. Reorganize String",
            selfRemark = "校验本身是比较容易的额。"
                    + "构造也不需太麻烦，用这种跳格法，从头到尾两趟就可以了.")
    public String reorganizeString(String s) {
        int[] map = new int[26];
        char[] chars = s.toCharArray();
        int maxCountIndex = 0;
        for (char c : chars) {
            int i = c - 'a';
            map[i]++;
            if (map[i] > chars.length / 2 + 1) {
                return "";
            }
            if (map[i] > map[maxCountIndex]) {
                maxCountIndex = i;
            }
        }
        char[] ret = new char[chars.length];
        int retIndex = 0;
        while (map[maxCountIndex] > 0) {
            ret[retIndex] = (char) (maxCountIndex + 'a');
            retIndex += 2;
            map[maxCountIndex]--;
        }

        for (int i = 0; i < 26; i++) {
            while (map[i] > 0) {
                ret[retIndex] = (char) (i + 'a');
                map[i]--;
                retIndex += 2;
                if (retIndex > chars.length) {
                    retIndex = 1;
                }
            }
        }

        return String.valueOf(ret);
    }
}