/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : OnesAndZeroes.java, v 0.1 2021年12月25日 5:04 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class OnesAndZeroes {

    @LetCodeCommit(title = "474. Ones and Zeroes",
            selfRemark = "这个题目很不错，至少对我来讲如此。这个题目很实际，很有点小学五年级老师爱出的应用题。"
                    + "这个题目的递推式非常简单，用递归+备忘录的形式也能解答，但是TLE了。"
                    + "然后就是如下的解法。首先这个题目是求最大子集，但是还真不能用组合发去解答，会超时。"
                    + "而是遍历strs，每次遍历都在解空间m*n中更新。"
                    + "更新m*n时还不是从小打到，而是从大到小。")
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int count0 = 0;
            int count1 = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    count0++;
                } else {
                    count1++;
                }
            }
            for (int i = dp.length - 1; i >= count0; i--) {
                for (int j = dp[i].length - 1; j >= count1; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - count0][j - count1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    @Parameter
    public String[] stars;
    @Parameter(1)
    public int      m;
    @Parameter(2)
    public int      n;
    @Parameter(3)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"10", "0001", "111001", "1", "0"}, 5, 3, 4},
                {new String[] {"10", "0", "1"}, 1, 1, 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findMaxForm(stars, m, n));
    }

}