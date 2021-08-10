/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.palindromic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : PalindromePartitioningIII.java, v 0.1 2021年08月08日 9:54 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PalindromePartitioningIII {
    @LetCodeCommit(title = "Palindrome Partitioning III")
    public int palindromePartition(String s, int k) {
        return resolve(s, k);
    }

    public int resolve(String s, int k) {
        int[][] cost = new int[s.length()][s.length()];
        /**
         * dp[i][j] =
         * 1. if i==j dp[i][j]=0;
         * 2. if i+1==j dp[i][j]=s[i]==s[j]?0:1
         * 3. dp[i][j] = dp[i+1][j-1] + s[i]==s[j]?0:1
         */
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    cost[i][j] = 0;
                } else if (i + 1 == j) {
                    cost[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                } else {
                    cost[i][j] = cost[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 0 : 1);
                }
            }
        }
        //横竖都多申请一个
        int[][] mem = new int[s.length() + 1][k + 1];
        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[i].length; j++) {
                mem[i][j] = -1;
            }
        }
        return dfs(cost, 0, k, mem);
    }

    /**
     * 超时了，然后就加一个备忘录，就90.99%了。
     * 加备忘录，超简单，更本不影响该函数的结构——因为该函数的result就是要备忘的东西
     * 注意：可以写成自底向上的二层循环。todo
     * @param cost  把s[i,...,j]变成palindrome需要的cost
     * @param i   当前子数组的开始
     * @param k   待切割的k
     * @param mem 备忘
     * @return
     */
    private int dfs(int[][] cost, int i, int k, int[][] mem) {
        if (mem[i][k] >= 0) {
            return mem[i][k];
        }
        if (k == 0) {
            if (i < cost.length) {
                return cost.length;
            } else {
                return 0;
            }
        }
        int min = cost.length;
        for (int j = i; j < cost.length; j++) {
            min = Math.min(min, cost[i][j] + dfs(cost, j + 1, k - 1, mem));
        }
        return mem[i][k] = min;
    }
    // dp[k][end] 表示把s[0...end]分k段的最小代价
    // dp[3][5] = min ( dp[2][4]+cost[5][5], dp[2][3]+cost[4][5], dp[2][2]+cost[3][5], dp[2][1]+cost[2][5], dp[2][0]+cost[1][5] )
    // TODO: 2021/8/10 8-14 周末来写自底向上的写法。

    @Parameter
    public String s;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"tcymekt", 4, 2},
                {"abc", 2, 1},
                {"aabbc", 3, 0},
                {"leetcode", 8, 0},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, palindromePartition(s, k));
    }

}