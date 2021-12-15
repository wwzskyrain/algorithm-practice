/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ShortestCompletingWord.java, v 0.1 2021年12月10日 8:35 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ShortestCompletingWord {

    @LetCodeCommit(title = "748. Shortest Completing Word")
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] hash = hashTrim(licensePlate);
        String ret = null;
        for (String word : words) {
            int[] hash1 = hash(word);
            int i = 0;
            while (i < hash.length) {
                if (hash[i] > hash1[i]) {
                    break;
                }
                i++;
            }
            if (i == hash.length) {
                if (ret == null) {
                    ret = word;
                } else if (ret.length() > word.length()) {
                    ret = word;
                }
            }
        }
        return ret;
    }

    public int[] hashTrim(String word) {
        word = word.toLowerCase();
        int[] h = new int[26];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {h[c - 'a']++;}
        }
        return h;
    }

    public int[] hash(String word) {
        int[] h = new int[26];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            h[c - 'a']++;
        }
        return h;
    }

    @Parameter
    public String   licensePlate;
    @Parameter(1)
    public String[] words;
    @Parameter(2)
    public String   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"B687015", new String[] {"born", "give", "would", "nice", "in", "understand", "blue", "force", "have", "that"}, "born"},
                {"1s3 456", new String[] {"looks", "pest", "stew", "show"}, "pest"},
                {"1s3 PSt", new String[] {"step", "steps", "stripe", "stepple"}, "steps"},
                {"Ah71752", new String[] {"suggest", "letter", "of", "husband", "easy", "education", "drug", "prevent", "writer", "old"},
                        "husband"},
                {"OgEu755", new String[] {"enough", "these", "play", "wide", "wonder", "box", "arrive", "money", "tax", "thus"}, "enough"},
                {"iMSlpe4",
                        new String[] {"claim", "consumer", "student", "camera", "public", "never", "wonder", "simple", "thought", "use"},
                        "simple"},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, shortestCompletingWord(licensePlate, words));
    }

}