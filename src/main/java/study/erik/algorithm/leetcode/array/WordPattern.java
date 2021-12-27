/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yueyi
 * @version : WordPattern.java, v 0.1 2021年12月26日 9:06 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class WordPattern {

    @LetCodeCommit(title = "290. Word Pattern")
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String key1 = pattern.charAt(i) + "_";
            String key2 = words[i];
            if (!Objects.equals(map.put(key1, i), map.put(key2, i))) {
                return false;
            }
        }
        return true;
    }

    @Parameter
    public String  pattern;
    @Parameter(1)
    public String  s;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"abba", "dog cat cat dog", true},
                {"abba", "dog cat cat fish", false},
                {"aaaa", "dog cat cat dog", false},
                {"abba", "dog dog dog dog", false},
                {"a", "a", true},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, wordPattern(pattern, s));
    }

}