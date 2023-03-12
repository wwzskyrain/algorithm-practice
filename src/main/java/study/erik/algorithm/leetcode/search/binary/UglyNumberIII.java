/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : UglyNumberIII.java, v 0.1 2023年03月11日 21:11 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class UglyNumberIII {

    @LetCodeCommit(title = "1201. Ugly Number III",
            selfRemark = "这是一个计算题，用二分查找；而不是一个模拟题目。"
                    + "这里处处有越界，所以从经验上说一律用long吧。"
                    + "最大公约数和最小公倍数复习一下。")
    public int nthUglyNumber(int n, int a, int b, int c) {
        long h = Integer.MAX_VALUE;
        long l = 0;
        long aa = ((long) a);
        long bb = ((long) b);
        long cc = ((long) c);
        long ab = aa * bb / gcd(aa, bb);
        long bc = bb * cc / gcd(bb, cc);
        long ca = cc * aa / gcd(cc, aa);
        long abc = ab * cc / gcd(ab, cc);
        while (l < h) {
            long m = l + (h - l) / 2;
            long v = m / a + m / b + m / c - m / ab - m / bc - m / ca + m / abc;
            if (v < n) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return ((int) l);
    }

    public long gcd(long a, long b) {
        if (a < b) {
            long t = a;
            a = b;
            b = t;
        }
        while (a % b != 0) {
            long mod = a % b;
            a = b;
            b = mod;
        }
        return b;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int a;
    @Parameter(2)
    public int b;
    @Parameter(3)
    public int c;
    @Parameter(4)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, 1, 1, 1, 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, nthUglyNumber(n, a, b, c));
    }

}