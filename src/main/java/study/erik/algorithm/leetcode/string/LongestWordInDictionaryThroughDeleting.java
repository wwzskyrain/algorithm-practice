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

import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : LongestWordInDictionaryThroughDeleting.java, v 0.1 2022年03月22日 4:37 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LongestWordInDictionaryThroughDeleting {

    @LetCodeCommit(title = "524. Longest Word in Dictionary through Deleting",
            diff = "m",
            selfRemark = "没意思.")
    public String findLongestWord(String s, List<String> dictionary) {
        String ret = "";
        int sL = s.length();
        for (String word : dictionary) {
            int i = 0, j = 0;
            int wordL = word.length();
            while (i < wordL && j < sL) {
                if (word.charAt(i) == s.charAt(j++)) {
                    i++;
                }
            }
            if (i == wordL) {
                if (word.length() > ret.length()) {
                    ret = word;
                } else if (word.length() == ret.length()) {
                    ret = word.compareTo(ret) < 0 ? word : ret;
                }
            }
        }
        return ret;
    }

    @Parameter
    public String       s;
    @Parameter(1)
    public List<String> dictionary;
    @Parameter(2)
    public String       expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"abpcplea", Arrays.asList("ale", "apple", "monkey", "plea"), "apple"},
                {"abpcplea", Arrays.asList("a", "b", "c"), "a"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findLongestWord(s, dictionary));
    }
}