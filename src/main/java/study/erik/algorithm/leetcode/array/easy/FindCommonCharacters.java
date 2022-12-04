/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : FindCommonCharacters.java, v 0.1 2022年12月04日 11:39 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindCommonCharacters {

    @LetCodeCommit(title = "1002. Find Common Characters")
    public List<String> commonChars(String[] words) {

        int[] c = new int[26];
        Arrays.fill(c, 100);
        int[] c1 = new int[26];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                c1[word.charAt(j) - 'a']++;
            }

            for (int k = 0; k < c.length; k++) {
                c[k] = Math.min(c[k], c1[k]);
                c1[k] = 0;
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < c.length; i++) {
            while (c[i]-- > 0) {
                res.add(String.valueOf((char) (i + 'a')));
            }
        }
        return res;
    }

    @Parameter
    public String[] words;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"bella", "label", "roller"}},
        };
    }

    @Test
    public void test_() {
        System.out.println(commonChars(words));
    }
}