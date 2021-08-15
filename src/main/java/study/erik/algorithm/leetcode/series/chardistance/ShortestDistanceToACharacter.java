/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.chardistance;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ShortestDistanceToACharacter.java, v 0.1 2021年08月14日 9:19 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ShortestDistanceToACharacter {

    @LetCodeCommit(title = "Shortest Distance to a Character")
    public int[] shortestToChar(String s, char c) {
        return resolve(s, c);
    }

    public int[] resolve(String s, char c) {
        int[] result = new int[s.length()];
        int l1 = 0;
        while (l1 < s.length() && s.charAt(l1) != c) {
            l1++;
        }
        int l = l1;
        while (l >= 0) {
            result[l] = l1 - l;
            l--;
        }
        while (true) {
            int l2 = l1 + 1;
            while (l2 < s.length() && s.charAt(l2) != c) {
                l2++;
            }
            int l11 = l1;
            if (l2 == s.length()) {
                while (l11 < s.length()) {
                    result[l11] = l11 - l1;
                    l11++;
                }
                return result;
            } else {
                while (l11 <= l2) {
                    result[l11] = Math.min(l11 - l1, l2 - l11);
                    l11++;
                }
                l1 = l2;
            }
        }
    }

    @Parameter
    public String s;
    @Parameter(1)
    public char   c;
    @Parameter(2)
    public int[]  expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"loveleetcode", 'e', ArrayUtils.buildArray("[3,2,1,0,1,0,0,1,2,2,1,0]")},
                {"aaab", 'b', ArrayUtils.buildArray("[3,2,1,0]")},
        };
    }

    @Test
    public void test_expect() {
        Assert.assertArrayEquals(expect, shortestToChar(s, c));
    }
}