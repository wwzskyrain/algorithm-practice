/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.palindromic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BreakPalindrome.java, v 0.1 2021年08月14日 2:21 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BreakPalindrome {

    @LetCodeCommit(title = "Break a Palindrome")
    public String breakPalindrome(String palindrome) {
        return resolve(palindrome);
    }

    /**
     * 啊，知道是个细节题，但是也太简单了吧，一把提交就过啦，虽然成绩不太好。
     * 优化：我这已经是O(n)了，在优化，就是优化成O(n/2).
     * 额，优化了，越优化越简单，而且成绩是100%和5%。
     * 在优化一下空间吧。
     * 优化失败：用substring代替了chars，算了。
     *
     * @param palindrome
     * @return
     */
    public String resolve(String palindrome) {
        //1.找第一个不等于a也不在计数中间的字符，该为a
        if (palindrome.length() < 2) {
            return "";
        }
        char[] chars = palindrome.toCharArray();
        int l = 0, h = chars.length - 1;
        while (l < h) {
            if (chars[l] != 'a') {
                chars[l] = 'a';
                return String.valueOf(chars);
            }
            l++;
            h--;
        }
        chars[chars.length - 1] = 'b';
        return String.valueOf(chars);
    }

    @Parameter
    public String palindrome;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"abccba", "aaccba"},
                {"a", ""},
                {"aa", "ab"},
                {"aba", "abb"},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, breakPalindrome(palindrome));
    }
}