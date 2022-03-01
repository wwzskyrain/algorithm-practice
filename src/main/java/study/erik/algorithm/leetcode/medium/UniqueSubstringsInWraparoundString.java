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
import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : UniqueSubstringsInWraparoundString.java, v 0.1 2022年02月27日 9:46 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class UniqueSubstringsInWraparoundString {

    @LetCodeCommit(title = "467. Unique Substrings in Wraparound String",
            diff = "m",
            selfRemark = ""
                    + "1.这道题目，bruteforce的话，超时。"
                    + "2.这道题目很佩服高手，看透了substring和的精髓——每一个字母为结尾的每个subString的个数",
            related = {"1434. Number of Ways to Wear Different Hats to Each Other",
                    "1830. Minimum Number of Operations to Make String Sorted"})
    public int findSubstringInWraproundString(String p) {

        // count[i]表示已字母 a+i结尾的subString的最大长度。
        int[] count = new int[26];

        int maxL = 1;
        count[p.charAt(0) - 'a'] = 1;
        for (int i = 1; i < p.length(); i++) {
            char lastC = p.charAt(i - 1);
            char c = p.charAt(i);
            if (lastC + 1 == c || lastC - 26 + 1 == c) {
                maxL++;
            } else {
                maxL = 1;
            }
            int curCount = count[c - 'a'];
            count[c - 'a'] = Math.max(curCount, maxL);
        }
        return Arrays.stream(count).sum();
    }

    public int solutionWithBruteForce(String p) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            Set<String> tempSet = new HashSet<>();
            for (String s : set) {
                char lastChar = s.charAt(s.length() - 1);
                if (lastChar + 1 == c || lastChar - 26 + 1 == c) {
                    tempSet.add(s + c);
                }
            }
            set.addAll(tempSet);
            tempSet.clear();
            set.add(c + "");
        }
        return set.size();
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{"zaba", 6},
                //{"a", 1},
                {"cac", 2},
                {"zab", 6},
                {"zabd", 7},
                {"zabde", 9},
                {"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz",
                        33475}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findSubstringInWraproundString(s));
    }
}