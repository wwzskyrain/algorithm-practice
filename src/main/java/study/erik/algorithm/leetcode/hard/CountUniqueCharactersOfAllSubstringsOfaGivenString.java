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
 * @version : CountUniqueCharactersOfAllSubstringsOfaGivenString.java, v 0.1 2022年07月09日 15:05 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CountUniqueCharactersOfAllSubstringsOfaGivenString {

    @LetCodeCommit(title = "Count Unique Characters of All Substrings of a Given String")
    public int uniqueLetterString(String s) {
        int[][] lastPos = new int[26][2];
        for (int[] lastPo : lastPos) {
            Arrays.fill(lastPo, -1);
        }
        char[] chars = s.toCharArray();
        int N = chars.length;
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int idx = c - 'A';
            if (lastPos[idx][1] == -1) {
                lastPos[idx][1] = i;
                continue;
            }
            ans = ans + (i - lastPos[idx][1]) * (lastPos[idx][1] - lastPos[idx][0]);
            lastPos[idx][0] = lastPos[idx][1];
            lastPos[idx][1] = i;
        }
        for (int[] lastPo : lastPos) {
            if (lastPo[1] != -1) {
                ans = ans + (N - lastPo[1]) * (lastPo[1] - lastPo[0]);
            }
        }
        return ans;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"ABC", 10},
                {"ABA", 8},
                {"LEETCODE", 92},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, uniqueLetterString(s));
    }

}