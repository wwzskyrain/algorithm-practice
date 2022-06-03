/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : OrderlyQueue.java, v 0.1 2022年06月03日 10:27 yueyi Exp $
 */
public class OrderlyQueue {

    @LetCodeCommit(title = "899. Orderly Queue")
    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            String cur = s;
            for (int i = 1; i < s.length(); i++) {
                String t = s.substring(i) + s.substring(0, i);
                if (cur.compareTo(t) > 0) {
                    cur = t;
                }
            }
            return cur;
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}