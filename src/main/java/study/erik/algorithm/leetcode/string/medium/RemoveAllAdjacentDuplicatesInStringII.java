/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RemoveAllAdjacentDuplicatesInStringII.java, v 0.1 2023年03月12日 08:47 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RemoveAllAdjacentDuplicatesInStringII {

    @LetCodeCommit(title = "1209. Remove All Adjacent Duplicates in String II",
            selfRemark = "双栈法很直观，很容易想到")
    public String removeDuplicates(String s, int k) {
        int n = s.length();
        int[] s1 = new int[n];
        int i = 0;
        char[] s2 = new char[n];
        for (int j = 0; j < n; j++) {
            char c = s.charAt(j);
            if (i < 1) {
                i++;
                s2[i - 1] = c;
                s1[i - 1] = 1;
                continue;
            }
            if (s2[i - 1] == c) {
                s1[i - 1]++;
                if (s1[i - 1] == k) {
                    i--;
                }
            } else {
                s1[i] = 1;
                s2[i] = c;
                i++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            while (s1[j]-- > 0) {
                sb.append(s2[j]);
            }
        }
        return sb.toString();
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"abcd", 2, "abcd"},
                {"deeedbbcccbdaa", 3, "aa"},
                {"pbbcggttciiippooaais", 2, "ps"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, removeDuplicates(s, k));
    }
}