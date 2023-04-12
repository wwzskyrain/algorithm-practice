/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

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
 * @version : CountVowelsPermutation.java, v 0.1 2023年04月09日 15:43 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CountVowelsPermutation {

    @LetCodeCommit(title = "1220. Count Vowels Permutation",
            selfRemark = "确实不是一个hard级别，应该是medium，或者easy")
    public int countVowelPermutation(int n) {
        int MOD = 1000000007;
        int[][] order = new int[][] {
                {1, 2, 4}, {0, 2}, {1, 3}, {2}, {2, 3}
        };

        long[][] dp = new long[2][5];
        Arrays.fill(dp[1], 1);
        int i = 2;
        while (i <= n) {
            int index = i % 2;
            int lastIndex = 1 - index;
            for (int j = 0; j < dp[index].length; j++) {
                int[] pre = order[j];
                long t = 0;
                for (int preJ : pre) {
                    t = (t + dp[lastIndex][preJ]) % MOD;
                }
                dp[index][j] = t;
            }
            i++;
        }
        long ret = 0;
        int lastIndex = n % 2;
        for (long l : dp[lastIndex]) {
            ret = (ret + l) % MOD;
        }
        return (int) ret;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, 5},
                {2, 10},
                {5, 68},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, countVowelPermutation(n));
    }

}