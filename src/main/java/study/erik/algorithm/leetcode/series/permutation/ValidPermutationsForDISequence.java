/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.permutation;

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
 * @version : ValidPermutationsForDISequence.java, v 0.1 2022年10月09日 22:24 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ValidPermutationsForDISequence {

    @LetCodeCommit(title = "903. Valid Permutations for DI Sequence")
    public int numPermsDISequence(String s) {
        int n = s.length();
        // 这里的dp并不是动态规划的意思，就是一个二维数组名称而已。
        // 该答案版本完全是参考lee215的 https://leetcode.com/problems/valid-permutations-for-di-sequence/discuss/168278/C%2B%2BJavaPython-DP-Solution-O(N2)
        // dp[i][j]的定义是在位置i(从0开始)放置剩下的数字中第j(从0开始，第0小表示一般语言上的第1小，也就是最小)小时，可能的permutation的个数
        // 比如dp[0][3]，表示基于当前[1234四个数字]在第0个位置放置第3小数字，其实也就是4；那么在此基础上，
        // 如果接下来要D，则它可以作用于dp[1][2]/dp[1][1]/dp[1][0]
        // 如果接下来是I，则它没有作用了。
        // 在比如dp[1][1]
        // 如果接下来是D，则它可以作用于dp[2][0]
        // 如果接下来是I，则它可以作用于dp[2][1]，因为剩下2个数，所以不能再大了
        int[][] dp = new int[n + 1][n + 1];
        int mod = 1000000007;
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < n; i++) {
            int cur = 0;
            if (s.charAt(i) == 'I') {
                // 前缀和
                for (int j = 0; j < n - i; j++) {
                    cur = (cur + dp[i][j]) % mod;
                    dp[i + 1][j] = cur;
                }
            } else {
                // 后缀和
                for (int j = n - i - 1; j >= 0; j--) {
                    cur = (cur + dp[i][j + 1]) % mod;
                    dp[i + 1][j] = cur;
                }
            }
        }
        return dp[n][0];
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"DID", 5},
                {"IDDDIIDIIIIIIIIDIDID", 853197538},
                {"D", 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numPermsDISequence(s));
    }
}