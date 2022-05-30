/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

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
 * @version : FindAndReplacePattern.java, v 0.1 2022年05月30日 20:37 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindAndReplacePattern {

    @LetCodeCommit(title = "890. Find and Replace Pattern")
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        return Arrays.stream(words)
                .filter(word -> match(word, pattern))
                .collect(Collectors.toList());
    }

    public boolean match(String word, String pattern) {
        char[] m = new char[26];
        boolean[] used = new boolean[26];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            char mapChar = m[c - 'a'];
            if (mapChar == 0) {
                char patternC = pattern.charAt(i);
                if (used[patternC - 'a']) {
                    return false;
                }
                m[c - 'a'] = pattern.charAt(i);
                used[patternC - 'a'] = true;
            } else {
                if (mapChar != pattern.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Parameter
    public String[] words;
    @Parameter(1)
    public String   pattern;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"abc", "deq", "mee", "aqq", "dkd", "ccc"}, "abb"},
                {new String[] {"a", "b", "c"}, "a"},
        };
    }

    @Test
    public void test_() {
        System.out.println(findAndReplacePattern(words, pattern));
    }
}