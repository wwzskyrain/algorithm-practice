/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : GreatestCommonDivisorOfStrings.java, v 0.1 2022年12月10日 22:35 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class GreatestCommonDivisorOfStrings {

    @LetCodeCommit(title = "1071. Greatest Common Divisor of Strings")
    public String gcdOfStrings(String str1, String str2) {
        String l = str1.length() > str2.length() ? str1 : str2;
        String s = str1.length() > str2.length() ? str2 : str1;
        int sL = s.length();
        int nCopy = 1;
        while (nCopy <= sL) {
            if (sL % nCopy == 0) {
                int copyLength = sL / nCopy;
                String copy = s.substring(0, copyLength);
                if (check(s, copy) && check(l, copy)) {
                    return copy;
                }
            }
            nCopy++;
        }
        return "";
    }

    private boolean check(String s, String r) {
        if (s.length() % r.length() != 0) {
            return false;
        }
        int n = s.length() / r.length();
        int rL = r.length();
        for (int i = 0; i < rL; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(j * rL + i) != r.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Parameter
    public String str1;
    @Parameter(1)
    public String str2;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"ABCABC", "ABC", "ABC"},
                {"ABABAB", "ABAB", "AB"},
                {"LEET", "CODE", ""}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, gcdOfStrings(str1, str2));
    }
}