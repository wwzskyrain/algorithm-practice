/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LongestChunkedPalindromeDecomposition.java, v 0.1 2023年03月12日 12:38 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LongestChunkedPalindromeDecomposition {

    @LetCodeCommit(title = "1147. Longest Chunked Palindrome Decomposition",
            selfRemark = "贪心算法，一定要证明之后才能用。这个题目就是这样。这样的题目很好玩的。"
                    + "当然在证明了贪心之后，还可以换一种形式写，如solution2")
    public int longestDecomposition(String text) {
        int ret = 0;
        int n = text.length();
        String l = "";
        String r = "";
        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            l = l + c;
            r = text.charAt(n - 1 - i) + r;
            if (l.equals(r)) {
                ret++;
                l = r = "";
            }
        }
        return ret;
    }

    @Parameter
    public String text;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"ghiabcdefhelloadamhelloabcdefghi", 7},
                {"merchant", 1},
                {"antaprezatepzapreanta", 11},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestDecomposition(text));
    }

    /**
     * 尾递归。效率十分的高的。为什么，因为尾调用是不需要返回调用函数的。
     *
     * @param text
     * @return
     */
    public int solution2(String text) {
        int n = text.length();
        for (int i = 0; i < n / 2; i++) {
            if (text.substring(0, i + 1).equals(text.substring(n - 1 - i, n))) {
                return 2 + solution2(text.substring(i + 1, n - 1 - i));
            }
        }
        return (n == 0) ? 0 : 1;
    }

}