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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author yueyi
 * @version : MaximumNumberOfOccurrencesOfASubstring.java, v 0.1 2023年04月14日 09:11 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumNumberOfOccurrencesOfASubstring {

    @LetCodeCommit(title = "1297. Maximum Number of Occurrences of a Substring",
            selfRemark = "这个题目中maxSize是多余的，这样的题目还需要主动的理解，不好")
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> map = new HashMap<>();
        int l = s.length();
        for (int i = 0; i <= l - minSize; i++) {
            String substring = s.substring(i, i + minSize);
            Integer c = map.getOrDefault(substring, 0);
            c++;
            map.put(substring, c);
        }
        int max = 0;
        for (String subStr : map.keySet()) {
            char[] chars = subStr.toCharArray();
            HashSet<Integer> set = new HashSet<>();
            for (char c : chars) {
                set.add(c - 'a');
            }
            if (set.size() <= maxLetters) {
                max = Math.max(max, map.get(subStr));
            }
        }
        return max;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    maxLetters;
    @Parameter(2)
    public int    minSize;
    @Parameter(3)
    public int    maxSize;
    @Parameter(4)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"aababcaab", 2, 3, 4, 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxFreq(s, maxLetters, minSize, maxSize));
    }

}