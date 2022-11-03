/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.subset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : WordSubsets.java, v 0.1 2022年10月30日 15:25 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class WordSubsets {

    @LetCodeCommit(title = "916. Word Subsets",
            selfRemark = "虽然不是我自己想出来的，但也差不多，明白了题意之后，直接就写出了")
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] totalCountB = new int[26];
        for (String s : words2) {
            int[] count = count(s);
            for (int j = 0; j < count.length; j++) {
                totalCountB[j] = Math.max(totalCountB[j], count[j]);
            }
        }

        return Arrays.stream(words1)
                .filter(word -> {
                    int[] count = count(word);
                    for (int i = 0; i < count.length; i++) {
                        if (count[i] < totalCountB[i]) {
                            return false;
                        }
                    }
                    return true;
                })
                .collect(Collectors.toList());

    }

    private int[] count(String s) {
        int[] c = new int[26];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
        }
        return c;
    }

    @Parameter
    public String[] word1;
    @Parameter(1)
    public String[] word2;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"amazon", "apple", "facebook", "google", "leetcode"}, new String[] {"e", "o"}},
                {new String[] {"amazon", "apple", "facebook", "google", "leetcode"}, new String[] {"l", "e"}},
        };
    }

    @Test
    public void test_() {
        System.out.println(wordSubsets(word1, word2));
    }
}