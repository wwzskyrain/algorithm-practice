/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RepeatedStringMatch.java, v 0.1 2022年04月18日 10:28 下午 yueyi Exp $
 */
public class RepeatedStringMatch {

    @LetCodeCommit(title = "686. Repeated String Match",
            diff = "e",
            selfRemark = "好简单的一个题目，我竟然想复杂了.")
    public int repeatedStringMatch(String a, String b) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length() / a.length() + 2; i++) {
            sb.append(a);
            if (sb.toString().contains(b)) {
                return i;
            }
        }
        return -1;
    }
}