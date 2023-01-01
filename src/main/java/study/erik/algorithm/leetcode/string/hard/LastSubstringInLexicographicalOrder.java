/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
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
 * @version : LastSubstringInLexicographicalOrder.java, v 0.1 2022年12月31日 22:36 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LastSubstringInLexicographicalOrder {

    @LetCodeCommit(title = "1163. Last Substring in Lexicographical Order",
            diff = "h",
            selfRemark = "这是一个很有意思的字符串题目，我也花了很久才想明白的。")
    public String lastSubstring(String s) {
        int i = 0, j = 1, k = 0;
        int n = s.length();
        while (j + k < n) {
            char iK = s.charAt(i + k);
            char jK = s.charAt(j + k);
            if (iK == jK) {
                k++;
                continue;
            } else if (iK > jK) {
                j = j + k + 1;
            } else {
                // 这里是最精彩的地方。
                // 这里i不是简单的去j，如果这样取了，会超时。Time Limit Exceeded
                // 所以，这个优化是必须的。
                // 那问题就是"什么时候取i+k+1呢？"
                // 就是发生多次循环的时候，即在k的长度内，发生了多次循环，比如
                // fedcbafedcbafedcba...fedcc, 省略号代表多次[fedcba].
                // 这里可以看出，比s[0:]更优的子字符串就是结尾处的[fedcc]
                // 这个更有子字符串，必定是比多次循环的[fedcba]在结构上就更优，也即是更大.
                // 话说回来，为什么必定是多次(至少2次)循环呢？这很容易看出来
                // 因为如果i+k>j，假设i+d=j，那么k>d，此时j+k>j+d,而j+d第三个循环的开始：第一个是从i到j-1，第二个是从j到j+d-1
                // 这里更进一步去了i+k+1，而不是i+k。当然i+k也是可以AC的，而且比i+k+1慢不了多少。
                i = Math.max(i + k + 1, j);
                j = i + 1;
            }
            k = 0;
        }
        return s.substring(i);
    }

    @Parameter
    public String s;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"abab", "bab"},
                {"dbbbdbbbdc", "dc"},
                {"aaaaaaaaaab", "b"},
                {"leetcode", "tcode"},
                {"zaazaabcdezaazb", "zb"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, lastSubstring(s));
    }

}