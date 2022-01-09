/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumNumberOfSwapsToMakeTheStringBalanced.java, v 0.1 2022年01月07日 12:19 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumNumberOfSwapsToMakeTheStringBalanced {

    @LetCodeCommit(title = "1963. Minimum Number of Swaps to Make the String Balanced",
            selfRemark = "真实感受，这题做的挺灰心的：一个m题，我愣是没做出来。"
                    + "我还用了二位dp呢，代码看起来很好，就是没搞定，因为确实不合适。"
                    + "在看评论区的答案，是一个贪心解法，根本没有体现min最小值。",
            related = {"https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/",
                    "https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/",
                    "https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/"})
    public int minSwaps(String s) {
        int open = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                }
            }
        }
        // open 和 close 成对出现。最后不匹配的，两对儿一次swap搞定
        return (open + 1) / 2;
    }

    public int solution1(String s) {
        char[] stack = new char[s.length()];
        int stackIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']' && stackIndex > 0 && stack[stackIndex - 1] == '[') {
                stackIndex--;
            } else {
                stack[stackIndex++] = c;
            }
        }
        return (stackIndex / 2 + 1) / 2;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"[]", 0},
                {"][][", 1},
                {"]]][[[", 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minSwaps(s));
    }
}