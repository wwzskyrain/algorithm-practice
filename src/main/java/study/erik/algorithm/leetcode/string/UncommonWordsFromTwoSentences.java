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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : UncommonWordsFromTwoSentences.java, v 0.1 2022年05月30日 17:03 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class UncommonWordsFromTwoSentences {

    @LetCodeCommit(title = "884. Uncommon Words from Two Sentences")
    public String[] uncommonFromSentences(String s1, String s2) {
        List<String> res = new ArrayList();
        Map<String, Integer> m1 = new HashMap();
        String[] split1 = s1.split(" ");
        for (int i = 0; i < split1.length; i++) {
            String s = split1[i];
            m1.put(s, m1.getOrDefault(s, 0) + 1);
        }

        Map<String, Integer> m2 = new HashMap();
        String[] split2 = s2.split(" ");
        for (int i = 0; i < split2.length; i++) {
            String s = split2[i];
            m2.put(s, m2.getOrDefault(s, 0) + 1);
        }

        m1.forEach((k, v) -> {
            if (v == 1) {
                if (!m2.containsKey(k)) {
                    res.add(k);
                }
            }
        });

        m2.forEach((k, v) -> {
            if (v == 1) {
                if (!m1.containsKey(k)) {
                    res.add(k);
                }
            }
        });
        return res.toArray(new String[0]);
    }

    @Parameter
    public String s1;
    @Parameter(1)
    public String s2;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"this apple is sweet", "this apple is sour"},
                {"apple apple", "banana"},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(uncommonFromSentences(s1, s2)));
    }
}