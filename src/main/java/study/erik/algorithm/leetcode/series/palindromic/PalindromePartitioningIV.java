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
 * @version : PalindromePartitioningIV.java, v 0.1 2021年08月10日 8:02 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PalindromePartitioningIV {

    @LetCodeCommit(title = "Palindrome Partitioning IV")
    public boolean checkPartitioning(String s) {
        return resolve(s);
    }

    /**
     * 这个hard题目有点简单，特别是第二步，竟然是指定的三个子串，那就直接两层循环搞定。
     * 虽然成绩不是很优秀： 32.24%，32.24%
     *
     * @param s
     * @return
     */
    public boolean resolve(String s) {
        boolean[][] isPal = new boolean[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                boolean iEqualJ = s.charAt(i) == s.charAt(j);
                if (i + 1 >= j - 1) {
                    isPal[i][j] = iEqualJ;
                } else {
                    isPal[i][j] = iEqualJ && isPal[i + 1][j - 1];
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length() - 1; j++) {
                if (isPal[0][i] && isPal[i + 1][j] && isPal[j + 1][s.length() - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Parameter
    public String  s;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"bbab", true},
                {"abcbdd", true},
                {"bcbddxy", false},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, checkPartitioning(s));
    }
}