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
 * @version : PalindromePartitioningII.java, v 0.1 2021年08月08日 8:27 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PalindromePartitioningII {

    @LetCodeCommit(title = "Palindrome Partitioning II",
            selfRemark = "这个题目真不好刷呀，学到了很多")
    public int minCut(String s) {
        boolean[][] isPal = new boolean[s.length()][s.length()];

        // cut[i] 表示最小切割数
        int[] cut = new int[s.length()];
        // i是子穿start，j是子串end
        // 这里用j表示列，i表示行，赋值是都是 isPal[i][j]
        // 把j写在外层，外层是列，里层是行
        for (int j = 0; j < s.length(); j++) {
            cut[j] = j;
            for (int i = 0; i <= j; i++) {
                // 综合了好几种情况，
                // 1.i==j
                // 2.i+1==j
                // 3.i和j相差多余1个距离
                if (s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                }
                if (isPal[i][j]) {
                    if (i == 0) {
                        cut[j] = 0;
                    } else {
                        cut[j] = Math.min(cut[j], cut[i - 1] + 1);
                    }
                }
            }
        }
        return cut[s.length() - 1];
    }

    /**
     * 超时了
     *
     * @param s
     * @param low
     * @param mem
     * @param curCut
     * @return
     */
    public int resolve(String s, int low, int[][] mem, int curCut) {
        int minCut = Integer.MAX_VALUE;
        // 无论是正切还是倒着切都是tle
        for (int i = s.length() - 1; i >= low; i--) {

            if (mem[low][i] == 0) {
                mem[low][i] = isPalindrome(s, mem, low, i);
            }
            if (mem[low][i] == 1) {
                if (i < s.length() - 1) {
                    // 还有得切
                    minCut = Math.min(minCut, resolve(s, i + 1, mem, curCut + 1));
                } else {
                    // 没得切了
                    minCut = curCut;
                }
            }
        }
        return minCut;
    }

    private int isPalindrome(String s, int[][] mem, int low, int high) {
        if (low == high) {
            return 1;
        }
        if (mem[low + 1][high - 1] == 1 && s.charAt(low) == s.charAt(high)) {
            return 1;
        }
        return -1;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{"ababababababababababababcbabababababababababababa", 0},
                //{"bb", 0},
                {"aab", 1},
                {"a", 0},
                {"ab", 1},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, minCut(s));
    }

}