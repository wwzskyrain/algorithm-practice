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

import java.util.Arrays;

/**
 * @author yueyi
 * @version : DeleteColumnsToMakeSortedIII.java, v 0.1 2022年11月20日 10:25 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class DeleteColumnsToMakeSortedIII {

    @LetCodeCommit(title = "960. Delete Columns to Make Sorted III")
    public int minDeletionSize(String[] strs) {
        int n = strs[0].length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (le(j, i, strs)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return n - max;
    }

    public boolean le(int i, int j, String[] words) {
        for (String word : words) {
            if (word.charAt(i) > word.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    @Parameter
    public String[] strs;
    @Parameter(1)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"baabab"}, 2},
                {new String[] {"babca", "bbazb"}, 3},
                {new String[] {"edcba"}, 4},
                {new String[] {"ghi", "def", "abc"}, 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minDeletionSize(strs));
    }

}