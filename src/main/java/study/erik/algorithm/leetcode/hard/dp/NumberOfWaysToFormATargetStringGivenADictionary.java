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
 * @version : NumberOfWaysToFormATargetStringGivenADictionary.java, v 0.1 2023年06月11日 09:07 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfWaysToFormATargetStringGivenADictionary {

    @LetCodeCommit(title = "1639. Number of Ways to Form a Target String Given a Dictionary")
    public int numWays(String[] words, String target) {
        int MOD = 1_000_000_007;
        int nn = words[0].length();
        int m = target.length();
        int[][] count = new int[nn][26];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int length = word.length();
            for (int j = 0; j < length; j++) {
                count[j][word.charAt(j) - 'a']++;
            }
        }
        //nn+1和m+1都有作用的。
        long[][] dp = new long[nn + 1][m + 1];
        //dp[i][m+1]表示words还有，而targe匹配完了，所以为1
        for (int i = 0; i < nn + 1; i++) {
            dp[i][m] = 1;
        }
        //dp[nn][j]表示words没了，而target还没匹配完成，所以为0
        for (int i = nn - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (nn - i < m - j) {
                    dp[i][j] = 0;
                    continue;
                }
                int c = count[i][target.charAt(j) - 'a'];
                long a = ((c * dp[i + 1][j + 1]) % MOD);
                a = (a + dp[i + 1][j]) % MOD;
                dp[i][j] = a;
            }
        }
        return ((int) dp[0][0]);
    }

    @Parameter
    public String[] words;
    @Parameter(1)
    public String   target;
    @Parameter(2)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"acca", "bbbb", "caca"}, "aba", 6},
                {new String[] {"abba", "baab"}, "bab", 4},
                {new String[] {"acca", "bbbb", "caca"}, "aba", 6},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numWays(words, target));
    }
}