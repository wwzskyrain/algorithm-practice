/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FlipStringToMonotoneIncreasing.java, v 0.1 2021年08月14日 5:21 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FlipStringToMonotoneIncreasing {

    @LetCodeCommit(title = "Flip String to Monotone Increasing")
    public int minFlipsMonoIncr(String s) {
        //return resolveWithRecursive(s, 0);
        return resolveWithDp(s);
    }

    /**
     * 一遍过，但是成绩也太不符合预期了（10.30%，5.02%）。难道是个计算题？
     * 看了一下high vote的，也不过如此；而我这个理解起来也是相当容易的。
     * 所以，就这样啦。
     *
     * @param s
     * @return
     */
    public int resolveWithDp(String s) {
        // dp1[i] 表示 s(0,...,i) 以1结尾的最小变数，
        // dp2[i] 表示 s(0,...,i) 以0结尾的最小变数，
        // dp1[i] = min(dp1[i-1], dp2[i-1]) + (s[i] == '1' ? 0 : 1);
        // dp2[i] = dp1[i-1] + (s[i] == '1' ? 1 : 0);

        int dp1 = s.charAt(0) == '1' ? 0 : 1;
        int dp2 = s.charAt(0) == '0' ? 0 : 1;
        int i = 1;
        while (i < s.length()) {
            dp1 = Math.min(dp1, dp2) + (s.charAt(i) == '1' ? 0 : 1);
            dp2 = dp2 + (s.charAt(i) == '0' ? 0 : 1);
            i++;
        }
        return Math.min(dp1, dp2);
    }

    /**
     * 不出所料，tle了。
     *
     * @param s
     * @param start
     * @return
     */
    public int resolveWithRecursive(String s, int start) {
        int min = s.length() - start;
        int i = start;
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }
        if (i == s.length()) {
            return 0;
        }
        min = Math.min(min, 1 + resolveWithRecursive(s, i + 1));
        i++;
        int changeTo1 = 0;
        while (i < s.length()) {
            if (s.charAt(i) == '0') {
                changeTo1++;
            }
            i++;
        }
        min = Math.min(changeTo1, min);
        return min;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"00110", 1},
                {"010110", 2},
                {"00011000", 2},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, minFlipsMonoIncr(s));
    }
}