/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BinaryNumberWithAlternatingBits.java, v 0.1 2022年04月21日 10:42 下午 yueyi Exp $
 */
public class BinaryNumberWithAlternatingBits {

    @LetCodeCommit(title = "693. Binary Number with Alternating Bits",
            diff = "e",
            selfRemark = "水一个.")
    public boolean hasAlternatingBits(int n) {
        // l 表示最后一位
        int l = (n & 1);
        n = n >> 1;
        do {
            if (l == (n & 1)) {
                // 如果当前最后一位和上一次最后一位值相同，那完蛋false了.
                return false;
            }
            l = (n & 1);
            n >>= 1;
        } while (n > 0);
        return true;
    }
}