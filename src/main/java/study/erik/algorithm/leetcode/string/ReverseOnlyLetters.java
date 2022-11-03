/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ReverseOnlyLetters.java, v 0.1 2022年10月30日 15:57 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ReverseOnlyLetters {

    @LetCodeCommit(title = "917. Reverse Only Letters")
    public String reverseOnlyLetters(String s) {
        int l = 0;
        int r = s.length() - 1;
        char[] chars = s.toCharArray();

        while (l < r) {
            while (l < s.length() && !isLetter(chars[l])) {
                l++;
            }
            if (l == s.length()) {
                break;
            }
            while (r >= 0 && !isLetter(chars[r])) {
                r--;
            }
            if (r == -1) {
                break;
            }
            if (l < r) {
                char t = chars[r];
                chars[r] = chars[l];
                chars[l] = t;
                l++;
                r--;
            }
        }
        return String.valueOf(chars);

    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z');
    }

    @Parameter
    public String s;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"?6C40E", "?6E40C"},
                {"ab-cd", "dc-ba"},
                {"a-bC-dEf-ghIj", "j-Ih-gfE-dCba"},
                {"Test1ng-Leet=code-Q!", "Qedo1ct-eeLg=ntse-T!"},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, reverseOnlyLetters(s));
    }
}