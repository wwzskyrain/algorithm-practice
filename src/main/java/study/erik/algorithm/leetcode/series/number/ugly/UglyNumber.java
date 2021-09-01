/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.number.ugly;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : UglyNumber.java, v 0.1 2021年08月22日 8:50 上午 yueyi Exp $
 */
public class UglyNumber {

    @LetCodeCommit(title = "Ugly Number")
    public boolean isUgly(int n) {
        //如果没有这句话，等值tle吧，因为 -231 <= n <= 231 - 1
        if (n <= 0) { return false; }
        if (n == 1) { return true; }
        int[] divide = new int[] {2, 3, 5};
        for (int div : divide) {
            while (n % div == 0) {
                n = n / div;
            }
        }
        return n == 1;
    }

    public boolean is(int n) {
        if (n <= 0) { return false; }
        if (n == 1) { return true; }
        while (n >= 2 && n % 2 == 0) { n /= 2; }
        while (n >= 3 && n % 3 == 0) { n /= 3; }
        while (n >= 5 && n % 5 == 0) { n /= 5; }
        return n == 1;
    }

}