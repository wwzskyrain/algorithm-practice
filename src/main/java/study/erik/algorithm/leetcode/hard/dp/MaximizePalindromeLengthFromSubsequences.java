/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximizePalindromeLengthFromSubsequences.java, v 0.1 2023年06月24日 20:04 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximizePalindromeLengthFromSubsequences {

    @LetCodeCommit(title = "1771. Maximize Palindrome Length From Subsequences",
            diff = "h",
            selfRemark = "学到了"
                    + "1.复习了lps，而且其自底向上的写法，不用len的那种，只需要两重循环")
    public int longestPalindrome(String word1, String word2) {
        String s = word1 + word2;
        int[][] dp = getDp(s);
        int ret = 0;
        int m = word1.length();
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    //因为两个子序列都是非空，所以这里必须只取word1.charAt(i) == word2.charAt(j)的时候，才能lps.
                    //如果没有非空限制，那直接就是lps原问题了。
                    //这一点我起初没有看清楚。
                    ret = Math.max(ret, dp[i + 1][m + j - 1] + 2);
                }
            }
        }
        return ret;
    }

    public int[][] getDp(String s) {
        // 直接求出LPS的dp数组。
        int l = s.length();
        int[][] dp = new int[l][l];
        for (int i = l - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < l; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }

    @Parameter
    public String word1;
    @Parameter(1)
    public String word2;
    @Parameter(2)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"cfe", "ef", 4},
                {"cacb", "cbba", 5},
                {"ab", "ab", 3},
                {"aa", "bb", 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestPalindrome(word1, word2));
    }

}