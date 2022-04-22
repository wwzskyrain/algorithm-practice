/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CountBinarySubstrings.java, v 0.1 2022年04月21日 10:57 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CountBinarySubstrings {

    @LetCodeCommit(title = "696. Count Binary Substrings",
    diff = "e",
    selfRemark = "这个题目挺好的；然而我这个思路还能再简化一下，简化成lee大神的解法："
            + "https://leetcode.com/problems/count-binary-substrings/discuss/108625/JavaC%2B%2BPython-Easy-and-Concise-with-Explanation")
    public int countBinarySubstrings(String s) {
        char[] chars = s.toCharArray();
        int ret = 0;
        int[] dp = new int[s.length()];
        for (int i = 0; i < chars.length; i++) {
            char c = s.charAt(i);
            int cc = 1;
            if (i > 0 && s.charAt(i - 1) == c) {
                cc += dp[i - 1];
            }
            if (i - cc >= 0 && s.charAt(i - cc) != c && dp[i - cc] >= cc) {
                ret++;
            }
            dp[i] = cc;
        }
        return ret;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"00110011", 6},
                {"10101", 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, countBinarySubstrings(s));
    }
}