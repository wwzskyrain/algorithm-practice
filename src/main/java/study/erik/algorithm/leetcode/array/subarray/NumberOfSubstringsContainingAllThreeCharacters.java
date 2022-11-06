/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.subarray;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumberOfSubstringsContainingAllThreeCharacters.java, v 0.1 2022年11月05日 16:05 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfSubstringsContainingAllThreeCharacters {

    @LetCodeCommit(title = "1358. Number of Substrings Containing All Three Characters")
    public int numberOfSubstrings(String s) {
        int count[] = {0, 0, 0}, res = 0, i = 0, n = s.length();
        for (int j = 0; j < n; ++j) {
            ++count[s.charAt(j) - 'a'];
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                --count[s.charAt(i++) - 'a'];
            }
            //
            res += i;
        }
        return res;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"abcbcaa", 10},
                {"aaccbb", 10},
                {"abccba", 10},
                {"abcabc", 10},
                {"aaacb", 3},
                {"abc", 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numberOfSubstrings(s));
    }
}