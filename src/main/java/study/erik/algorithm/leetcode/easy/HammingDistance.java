/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : HammingDistance.java, v 0.1 2021年08月15日 9:46 下午 yueyi Exp $
 */
public class HammingDistance {

    @LetCodeCommit(title = "Hamming Distance",
            time = 100,
            selfRemark = "easy题目，用来冲300的。一把提交，一把过")
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z != 0) {
            if ((z & 1) == 1) {
                count++;
            }
            z = z >> 1;
        }
        return count;
    }
}