/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.palindromic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : PalindromePairs.java, v 0.1 2022年02月19日 10:30 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PalindromePairs {

    @LetCodeCommit(title = "336. Palindrome Pairs",
            selfRemark = "对整体的解法没有问题，就是不够高效，超时了。等我们把马拉松算法搞定后再来优化。")
    public List<List<Integer>> palindromePairs(String[] words) {

        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        List<Integer> palindromeIndex = new ArrayList<>();
        int emptyWordIndex = -1;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            map.put(word, i);
            if (word.length() == 0) {
                emptyWordIndex = i;
                continue;
            }
            if (isP(word)) {
                palindromeIndex.add(i);
            }
        }
        if (emptyWordIndex >= 0) {
            for (Integer index : palindromeIndex) {
                result.add(Arrays.asList(emptyWordIndex, index));
                result.add(Arrays.asList(index, emptyWordIndex));
            }
        }

        map.remove("");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int endIndex = 0; endIndex < word.length(); endIndex++) {
                String headSubstring = word.substring(0, endIndex);
                if (isP(headSubstring)) {
                    String tailSubstringReversed = reverse(word.substring(endIndex));
                    if (map.containsKey(tailSubstringReversed)) {
                        Integer index = map.get(tailSubstringReversed);
                        if (index != i) {
                            result.add(Arrays.asList(index, i));
                        }
                    }
                }
            }
            for (int j = word.length() - 1; j >= 0; j--) {
                String tailSubstring = word.substring(j);
                if (isP(tailSubstring)) {
                    String headSubstringReversed = reverse(word.substring(0, j));
                    if (map.containsKey(headSubstringReversed)) {
                        Integer index = map.get(headSubstringReversed);
                        if (index != i) {
                            result.add(Arrays.asList(i, index));
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean isP(String word) {
        if (word.length() == 0) {
            return true;
        }
        int l = 0, h = word.length() - 1;
        while (l < h) {
            if (word.charAt(l++) != word.charAt(h--)) {
                return false;
            }
        }
        return true;
    }

    private String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    @Parameter
    public String[]            words;
    @Parameter(1)
    public List<List<Integer>> expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {

                {new String[] {"a", "abc", "aba", ""},
                        ArrayUtils.buildList2Dimension("[[0,3],[3,0],[2,3],[3,2]]")},
                {new String[] {"abcd", "dcba", "lls", "s", "sssll"},
                        ArrayUtils.buildList2Dimension("[[0,1],[1,0],[3,2],[2,4]]")},
                {new String[] {"bat", "tab", "cat"},
                        ArrayUtils.buildList2Dimension("[[0,1],[1,0]]")},
                {new String[] {"a", ""},
                        ArrayUtils.buildList2Dimension("[[0,1],[1,0]]")},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, palindromePairs(words));
    }

}