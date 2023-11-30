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

import java.util.Arrays;

/**
 * @author yueyi
 * @version : NumberOfWaysToFormATargetStringGivenADictionary.java, v 0.1 2023年06月11日 09:07 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfWaysToFormATargetStringGivenADictionary {

    @LetCodeCommit(title = "1639. Number of Ways to Form a Target String Given a Dictionary",
            selfRemark = "这个问题最好的理解就是solution2")
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


    /**
     * 第二种解法：用dp来分析，用递归来实现
     *
     * @param words
     * @param target
     * @return
     */
    public int numWaysSolution2(String[] words, String target) {
        int wordLength = words[0].length();
        int[][] cnt = new int[wordLength][26];
        for (String word : words) {
            for (int i = 0; i < wordLength; i++) {
                cnt[i][word.charAt(i) - 'a']++;
            }
        }
        long[][] memo = new long[wordLength][target.length()];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return (int) dfs(cnt, target, wordLength, 0, 0, memo);
    }

    private int MOD = (int) 1e9 + 7;

    /**
     * 从第i位开始匹配target[j...]的成功次数。
     * 则问题的解为i=0，j=0.
     * dp[i][j]=case1+case2；
     * case1:当前i位中有几个能匹配target[j]的，记作count，然后问题递归到i+1,j+1，其结果*count。
     * case2:从第i+1位来匹配target[j...]。
     */
    public long dfs(int[][] cnt, String target, int wordLength, int i, int j, long[][] memo) {
        if (j == target.length()) {
            return 1;
        }
        if (wordLength - i < target.length() - j) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        long ret = 0;
        //case 1: match i to j
        char c = target.charAt(j);
        int count = cnt[i][c - 'a'];
        if (count != 0) {
            ret = count * dfs(cnt, target, wordLength, i + 1, j + 1, memo);
            ret = (ret % MOD);
        }
        //case 2: ignore i
        ret += dfs(cnt, target, wordLength, i + 1, j, memo);
        ret = (ret % MOD);

        return memo[i][j] = ret;
    }


    @Parameter
    public String[] words;
    @Parameter(1)
    public String target;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {677452090, new String[]{"cbabddddbc", "addbaacbbd", "cccbacdccd", "cdcaccacac", "dddbacabbd", "bdbdadbccb", "ddadbacddd", "bbccdddadd", "dcabaccbbd", "ddddcddadc", "bdcaaaabdd", "adacdcdcdd", "cbaaadbdbb", "bccbabcbab", "accbdccadd", "dcccaaddbc", "cccccacabd", "acacdbcbbc", "dbbdbaccca", "bdbddbddda", "daabadbacb", "baccdbaada", "ccbabaabcb", "dcaabccbbb", "bcadddaacc", "acddbbdccb", "adbddbadab", "dbbcdcbcdd", "ddbabbadbb", "bccbcbbbab", "dabbbdbbcb", "dacdabadbb", "addcbbabab", "bcbbccadda", "abbcacadac", "ccdadcaada", "bcacdbccdb"}, "bcbbcccc"},
                {6, new String[]{"acca", "bbbb", "caca"}, "aba"},
                {4, new String[]{"abba", "baab"}, "bab"},
                {1, new String[]{"abcd"}, "abcd"},
                {16, new String[]{"abcd", "abcd"}, "abcd"},
                {16, new String[]{"abab", "baba", "abba", "baab"}, "abba"}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numWays(words, target));
        Assert.assertEquals(expect, numWaysSolution2(words, target));
    }
}