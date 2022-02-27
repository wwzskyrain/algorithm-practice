/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

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
 * @version : SortCharactersByFrequency.java, v 0.1 2022年02月26日 9:57 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SortCharactersByFrequency {

    @LetCodeCommit(title = "451. Sort Characters By Frequency")
    public String frequencySort(String s) {
        int[][] count = new int[128][];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (count[c] == null) {
                count[c] = new int[] {c, 0};
            }
            count[c][1]++;
        }

        StringBuilder sb = new StringBuilder();
        Arrays.sort(count, (a, b) -> {
            if (a == null && b == null) {
                return 0;
            }
            if (a == null) {
                return 0 - b[1];
            }
            if (b == null) {
                return a[1] - 0;
            }
            return a[1] - b[1];
        });
        for (int i = count.length - 1; i > 0; i--) {
            int[] cArr = count[i];
            if (cArr == null) {
                break;
            }
            while (cArr[1]-- > 0) {
                sb.append((char) cArr[0]);
            }
        }
        return sb.toString();
    }

    @Parameter
    public String s;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"tree", "eert"},
                {"cccaaa", "aaaccc"},
                {"Aabb", "bbAa"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, frequencySort(s));
    }

}