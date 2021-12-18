/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FactorialTrailingZeroes.java, v 0.1 2021年12月18日 9:54 上午 yueyi Exp $
 */
public class FactorialTrailingZeroes {

    @LetCodeCommit(title = "172. Factorial Trailing Zeroes",
            selfRemark = "这个题目去年字节二面的时候做过，当时就做出来了，当时还以为很容易，今天看确实也很容易。"
                    + "原来字节随手都是那力扣的题目做呀")
    public int trailingZeroes(int n) {
        int ret = 0;
        while (n >= 5) {
            n /= 5;
            ret += n;

        }
        return ret;
    }

}