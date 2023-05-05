/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LongestHappyPrefix.java, v 0.1 2023年05月05日 06:35 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LongestHappyPrefix {

    @LetCodeCommit(title = "1392. Longest Happy Prefix",
            selfRemark = "这个题目不难呀，不配成为hard。"
                    + "但是我们从这个题目中学到了一点——虽然本应该很快明白的，但是我们看了好几分钟。"
                    + "就是求后缀的时候hash时")
    public String longestPrefix(String s) {
        long h1 = 0, h2 = 0, mul = 1, len = 0, mod = 1000000007;
        for (int i = 0, j = s.length() - 1; j > 0; ++i, --j) {
            int first = s.charAt(i) - 'a', last = s.charAt(j) - 'a';
            h1 = (h1 * 26 + first) % mod;
            //h2的计算再明显不过了。h2等于所有低位的hash+'当前最高位*位势'。
            h2 = (h2 + mul * last) % mod;
            mul = mul * 26 % mod;
            if (h1 == h2) {len = i + 1;}
        }
        return s.substring(0, (int) len);
    }

    @Parameter
    public String s;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{"level","l"},
                {"ababab", "abab"},
                {"level", "l"},
                {"abcba", "a"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestPrefix(s));
    }
}