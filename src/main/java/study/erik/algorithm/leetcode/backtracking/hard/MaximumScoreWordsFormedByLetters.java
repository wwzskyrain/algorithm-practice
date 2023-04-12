/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.backtracking.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumScoreWordsFormedByLetters.java, v 0.1 2023年04月09日 17:44 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumScoreWordsFormedByLetters {

    @LetCodeCommit(title = "1255. Maximum Score Words Formed by Letters",
            selfRemark = "回溯法。都可以是hard了")
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] count = new int[26];
        for (char c : letters) {
            count[c - 'a']++;
        }
        return backTracking(words, 0, count, score);
    }

    public int backTracking(String[] words, int index, int[] count, int[] score) {
        int l = words.length;
        if (index == l) {
            return 0;
        }
        int totalScore = 0;
        for (int i = index; i < l; i++) {
            String word = words[i];
            boolean foundWord = true;
            char[] wordChars = word.toCharArray();
            int wordScore = 0;
            for (char wordChar : wordChars) {
                if ((--count[wordChar - 'a']) < 0) {
                    foundWord = false;
                }
                wordScore += score[wordChar - 'a'];
            }
            if (foundWord) {
                // 如果发现了word，才(就)下一层.
                // 注意，这里是从i处下，刚才顺手写了index+1，死活不通过；然后就是找不同了：
                // 一点一点改，改到最后了，真的是最后呀，才发现传参错误啊，真是大意。
                wordScore += backTracking(words, i + 1, count, score);
                totalScore = Math.max(totalScore, wordScore);
            }
            for (char wordChar : wordChars) {
                //归还count
                ++count[wordChar - 'a'];
            }
        }
        return totalScore;
    }

    @Parameter
    public String[] words;
    @Parameter(1)
    public char[]   letters;
    @Parameter(2)
    public int[]    score;
    @Parameter(3)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"add", "dda", "bb", "ba", "add"},
                        ArrayUtils.buildCharArr(
                                new String[] {"a", "a", "a", "a", "b", "b", "b", "b", "c", "c", "c", "c", "c", "d", "d", "d"}),
                        ArrayUtils.buildArray("[3,9,8,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]"),
                        51
                }
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxScoreWords(words, letters, score));
    }
}

