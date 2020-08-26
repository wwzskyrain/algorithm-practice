package study.erik.algorithm.leetcode.string.palindromic;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import static study.erik.algorithm.util.QuestionType.DP;

/**
 * @author erik.wang
 * @date 2020-08-16 18:42
 */
public class LongestPalindromicSubsequence {

    @LetCodeCommit(no = 516, title = "Longest Palindromic Subsequence", time = 63, space = 84, diff = "m",
            types = DP,
            selfRemark = "一个简单的二位dp的题目，也是一个回文数的题目。dp的特性还不错")
    public int longestPalindromeSubseq(String s) {

        int[][] dp = new int[s.length()][s.length()];
        for (int l = 0; l < s.length(); l++) {
            for (int i = 0; i < s.length() - l; i++) {
                int j = i + l;
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.max(Math.max(dp[i + 1][j], dp[i][j - 1]),
                            dp[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 2 : 0));
                }
            }
        }
        return dp[0][dp[0].length - 1];
    }

    @Test
    public void test_solution_1() {
        Assert.assertEquals(4, longestPalindromeSubseq("bbbab"));
    }


    @Test
    public void test_solution_2() {
        Assert.assertEquals(2, longestPalindromeSubseq("cbbd"));
    }


}
