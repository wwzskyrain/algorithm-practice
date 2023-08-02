package study.erik.algorithm.leetcode.hard.dp;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/7/31 ，时间：18:35
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class NumberOfWaysToSeparateNumbers {

    @LetCodeCommit(title = "1977. Number of Ways to Separate Numbers",
            diff = "h",
            selfRemark = "这个题目太好了——题意简单而解题思路复杂而有序。" +
                    "1.定义好dp[i][len]" +
                    "2.用前缀和思想优化dp定义，从而减小计算次数" +
                    "3.用lcs来预处理，从而优化两个字符串的大小比较效率。")
    public int numberOfCombinations(String num) {
        int n = num.length();
        int M = 1000_000_007;
        if (num.charAt(0) == '0') return 0;

        //accum[i][len]表示以i为结尾，以len=1、2、3...、len-1，作为序列中的最后一个数字的序列数的和的个数。accum[n-1][n]就是问题的解
        int[][] accum = new int[n + 2][n + 2];
        int[][] lcs = new int[n + 2][n + 2];

        //计算最长公共子串
        for (int i = n - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--) {
                if (num.charAt(i) == num.charAt(j)) {
                    lcs[i][j] = lcs[i + 1][j + 1] + 1;
                } else
                    lcs[i][j] = 0;
            }

        for (int i = 0; i < n; i++)
            for (int len = 1; len <= i + 1; len++) {
                int j = i - len + 1;
                int dp = 0;

                if (num.charAt(j) == '0')
                    dp = 0;
                else if (len == i + 1)
                    dp = 1;
                else {
                    int maxLen2 = Math.min(len, j);

                    if (len == maxLen2 && lcs[j - maxLen2][j] < len
                            && num.charAt(j - maxLen2 + lcs[j - maxLen2][j]) > num.charAt(j + lcs[j - maxLen2][j]))
                        maxLen2--;
                    while (maxLen2 >= 1 && num.charAt(j - maxLen2) == '0')
                        maxLen2--;

                    if (maxLen2 >= 1)
                        dp = accum[j - 1][maxLen2];
                }

                accum[i][len] = (accum[i][len - 1] + dp) % M;
            }

        return accum[n - 1][n];

    }

    boolean larger(String s, int a, int b, int len) {
        for (int i = 0; i < len; i++)
            if (s.charAt(a + i) != s.charAt(b + i))
                return s.charAt(a + i) > s.charAt(b + i);
        return false;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, "327"},
                {0, "094"},
                {0, "0"},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String num;

    @Test
    public void test() {
        Assert.assertEquals(expect, numberOfCombinations(num));
    }

}
