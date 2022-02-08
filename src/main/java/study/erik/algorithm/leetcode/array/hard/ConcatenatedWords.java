/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yueyi
 * @version : ConcatenatedWords.java, v 0.1 2022年02月08日 10:56 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ConcatenatedWords {

    @LetCodeCommit(title = "472. Concatenated Words")
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparing(String::length));
        List<String> result = new ArrayList<>();
        Set<String> preWordSet = new HashSet<>();
        for (String word : words) {
            // dp[i]表示word[0...i]是在字典中的
            if (word.length() == 0) {
                continue;
            }
            boolean[] dp = new boolean[word.length()];
            for (int i = 0; i < word.length(); i++) {
                for (int j = i; j >= 0; j--) {
                    //注意一定要把dp[j-1]在contains之前判断，这样可以更大的使用之前的contains结果，即之前的子问题的解
                    if ((j == 0 || dp[j - 1]) && preWordSet.contains(word.substring(j, i + 1))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            if (dp[word.length() - 1]) {
                result.add(word);
            }
            preWordSet.add(word);
        }
        return result;
    }

    @Parameter
    public String[]     words;
    @Parameter(1)
    public List<String> expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"},
                        Arrays.asList("catsdogcats", "dogcatsdog", "ratcatdogcat")},
                {new String[] {"cat", "dog", "catdog"},
                        Collections.singletonList("catdog")},
                {new String[] {""},
                        Collections.emptyList()},
        };
    }

    @Test
    public void test_() {
        Assert.assertThat(expect, new ListValueEqualMatcher(findAllConcatenatedWordsInADict(words)));
    }

    public class ListValueEqualMatcher extends BaseMatcher<List<String>> {
        private List<String> actualList;

        public ListValueEqualMatcher(List<String> actualList) {
            this.actualList = actualList;
        }

        @Override
        public boolean matches(Object item) {
            if (item instanceof List) {
                List<?> item1 = (List<?>) item;
                for (Object o : item1) {
                    if (o instanceof String && actualList.contains(o)) {
                        continue;
                    }
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override
        public void describeTo(Description description) {

        }
    }

}