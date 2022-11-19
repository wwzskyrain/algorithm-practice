/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : VerifyingAnAlienDictionary.java, v 0.1 2022年11月19日 11:21 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class VerifyingAnAlienDictionary {

    @LetCodeCommit(title = "953. Verifying an Alien Dictionary",
            diff = "e",
            selfRemark = "100%")
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < words.length - 1; i++) {
            if (!com(words[i], words[i + 1], order)) {
                return false;
            }
        }
        return true;
    }

    private boolean com(String word1, String word2, String order) {
        int i1 = 0, i2 = 0;
        int n1 = word1.length(), n2 = word2.length();
        while (i1 < n1 && i2 < n2) {
            int o1 = order.indexOf(word1.charAt(i1++));
            int o2 = order.indexOf(word2.charAt(i2++));
            if (o1 < o2) {
                return true;
            } else if (o1 > o2) {
                return false;
            } else {
                continue;
            }
        }
        return n1 <= n2;
    }

    @Parameter
    public String[] words;
    @Parameter(1)
    public String   order;
    @Parameter(2)
    public boolean  expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"hello", "hello"}, "abcdefghijklmnopqrstuvwxyz", true},
                {new String[] {"kuvp", "q"}, "ngxlkthsjuoqcpavbfdermiywz", true},
                {new String[] {"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz", true},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, isAlienSorted(words, order));
    }

}