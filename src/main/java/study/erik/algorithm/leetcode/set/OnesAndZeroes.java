/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : OnesAndZeroes.java, v 0.1 2021年06月06日 2:59 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class OnesAndZeroes {

    @Parameter(0)
    public String[] str;
    @Parameter(1)
    public Integer  m;
    @Parameter(2)
    public Integer  n;
    @Parameter(3)
    public int      expect;

    @Parameterized.Parameters
    public static Object[][] init() {
        return new Object[][] {
                new Object[] {new String[] {"10", "0001", "111001", "1", "0"}, 5, 3, 4},
                new Object[] {new String[] {"10", "0", "1"}, 1, 1, 2},
        };
    }

    @LetCodeCommit(title = "Ones and Zeroes")
    public int findMaxForm(String[] strs, int m, int n) {

        int goodsCount = strs.length; //货物个数
        int[][] c = new int[goodsCount + 1][];
        for (int i = 0; i < strs.length; i++) {
            c[i + 1] = resolve(strs[i]);
        }

        int[][][] dp = new int[goodsCount + 1][m + 1][n + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                //注意j/k的取值区间，首先j和k必须有0的情况，这一点在一维容积的时候怪怪的，因为这时候价值直接为0就好了。
                //但是在二维容积时，就会存在价值非0的情况。比如j=0，而这时候k!=0，则这时候有个物品，其只有1的维度，这时候
                //就可以装进了，所以总价值就不为0了.
                for (int k = 0; k < dp[i][j].length; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j - c[i][0] >= 0 && k - c[i][1] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - c[i][0]][k - c[i][1]] + 1);
                    }
                }
            }
        }
        return dp[goodsCount][m][n];
    }

    public int[] resolve(String str) {
        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                zeroCount++;
            } else {
                oneCount++;
            }
        }
        return new int[] {zeroCount, oneCount};
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, findMaxForm(str, m, n));
    }

}