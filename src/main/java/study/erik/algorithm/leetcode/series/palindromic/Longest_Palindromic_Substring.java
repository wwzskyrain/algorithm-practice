package study.erik.algorithm.leetcode.series.palindromic;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/22 ，时间：09:40
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Longest_Palindromic_Substring {

    @LetCodeCommit(title = "5. Longest Palindromic Substring")
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        //dp[i][j] 表示 s[i...j]是回文数
        //dp[i][j] = (s[i]==s[j] && dp[i+1][j-1])
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        String max = s.charAt(0) + "";
        for (int l = 1; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                dp[i][j] = s.charAt(i) == s.charAt(j);
                if (l > 2) {
                    dp[i][j] = (dp[i][j] && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > max.length()) {
                    max = s.substring(i, j + 1);
                }
            }
        }
        return max;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
//                {"bab", "babad"},
{"bb", "cbbd"},
});
    }

    @Parameterized.Parameter
    public String expect;
    @Parameterized.Parameter(1)
    public String s;


    @Test
    public void test() {
        Assert.assertEquals(expect, longestPalindrome(s));
    }

}
