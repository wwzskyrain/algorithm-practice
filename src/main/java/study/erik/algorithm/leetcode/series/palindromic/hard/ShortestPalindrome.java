package study.erik.algorithm.leetcode.series.palindromic.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/15 ，时间：15:01
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class ShortestPalindrome {

    @LetCodeCommit(title = "214. Shortest Palindrome",
            selfRemark = "这是一个hard题目，竟然用这种方式给解决了。")
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        String t = sb.toString();
        for (int i = 0; i <= t.length(); i++) {
            String sub = t.substring(i);
            if (s.startsWith(sub)) {
                return t.substring(0, i) + s;
            }
        }
        return t + s;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
//                {"aaacecaaa", "aacecaaa"},
                {"dcbabcd", "abcd"},
        });
    }

    @Parameterized.Parameter
    public String expect;
    @Parameterized.Parameter(1)
    public String s;


    @Test
    public void test() {
        Assert.assertEquals(expect, shortestPalindrome(s));
    }

}
