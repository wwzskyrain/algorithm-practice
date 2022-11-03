/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumberOfMusicPlaylists.java, v 0.1 2022年10月30日 18:16 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfMusicPlaylists {

    @LetCodeCommit(title = "920. Number of Music Playlists")
    public int numMusicPlaylists(int n, int goal, int k) {
        int MOD = 1000000007;
        long[][] dp = new long[goal + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= goal; i++) {
            for (int j = 1; j <= n; j++) {
                //未重复的
                dp[i][j] += dp[i - 1][j - 1] * (n - j + 1);
                //重复出现的
                dp[i][j] += dp[i - 1][j] * Math.max(j - k, 0);
                dp[i][j] %= MOD;
            }
        }
        return (int) dp[goal][n];
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int goal;
    @Parameter(2)
    public int k;
    @Parameter(3)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3, 3, 1, 6},
                {2, 3, 0, 6},
                {2, 3, 1, 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numMusicPlaylists(n, goal, k));
    }
}

