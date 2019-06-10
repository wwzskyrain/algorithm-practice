package study.erik.algorithm.leetcode.topic;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/06/08
 **/
public class Palindromic {


    /**
     * Valid Palindrome，easy level
     * https://leetcode.com/problems/valid-palindrome/
     * 要求:s中字母字符形成一个'回文'，而且'ignoring cases'。注意，定义空字符串是有效回文。
     * 不写，算法太简单，实现有点麻烦。
     *
     * @param s
     * @return
     */
    public boolean validPalindromeI(String s) {

        return true;

    }

    @Test
    public void test_validPalindromeI() {
        Assert.assertTrue(validPalindromeI("aba"));
        Assert.assertTrue(validPalindromeI("aaaa"));
        Assert.assertTrue(validPalindromeI("abba"));
        Assert.assertFalse(validPalindromeI("abca"));
    }


    /**
     * Valid Palindrome II , easy level
     * https://leetcode.com/problems/valid-palindrome-ii/
     * 输入一个非空字符串，问是否可以通过'最多删除一个字符'将其改造为'回文'
     * 解答通过，注意2点
     *
     * @param s
     * @return
     */
    public boolean validPalindromeII(String s) {

        int length = s.length();
        int lower = 0;
        int high = length - 1;

        while (lower < high) {
            if (s.charAt(lower) != s.charAt(high)) {
                break;
            }
            lower++;
            high--;
        }
        //注意点1：这里lower == high - 1是对该问题的一种优化
        if (lower > high || lower == high - 1) {
            return true;
        }

        //注意点2：这里是两种情况的'或'
        return validPalindrome(s, lower + 1, high) || validPalindrome(s, lower, high - 1);
    }

    public boolean validPalindrome(String s, int lower, int high) {
        int length = s.length();
        if (lower < 0 || high >= length) {
            return false;
        }

        while (lower < high) {
            if (s.charAt(lower) != s.charAt(high)) {
                return false;
            }
            lower++;
            high--;
        }
        return true;
    }

    @Test
    public void test_validPalindromeII() {

        Assert.assertTrue(validPalindromeII("aba"));
        Assert.assertTrue(validPalindromeII("abca"));
        Assert.assertTrue(validPalindromeII("deeee"));
        Assert.assertTrue(validPalindromeII("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }

    /**
     * 5. Longest Palindromic Substring
     * https://leetcode.com/problems/longest-palindromic-substring/
     * 问题描述：找到最长回文子串
     * 解法描述：动态规划吧，定义d[i,j]=1表示str[i,,,j]是'回文'；
     * d[i,j]=or{d[i+1,j-1] , str[i]==str[j];  0, str[i]!=str[j]}
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {

        if (s.length() == 0) {
            return s;
        }
        int longestLength = 1;
        int lower = 0;
        int high = 0;
        int length = s.length();
        int[][] d = new int[length][];
        for (int i = 0; i < d.length; i++) {
            d[i] = new int[length];
            d[i][i] = 1;
        }

        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                d[i][i + 1] = 1;
                longestLength = 2;
                lower = i;
                high = i + 1;
            } else {
                d[i][i + 1] = 0;
            }
        }

        for (int span = 2; span < length; span++) {
            for (int i = 0; i < length - span; i++) {
                int x = i;
                int y = x + span;
                if (s.charAt(x) == s.charAt(y)) {
                    d[x][y] = d[x + 1][y - 1];

                    if (d[x][y] == 1 && y - x + 1 > longestLength) {
                        lower = x;
                        high = y;
                        longestLength = y - x + 1;
                    }

                } else {
                    d[x][y] = 0;
                }
            }
        }

        return s.substring(lower, high + 1);

    }

    @Test
    public void test_longestPalindrome1() {

        Assert.assertEquals("a", longestPalindrome1("abcda"));
        Assert.assertEquals("bb", longestPalindrome1("cbbd"));
    }


}
