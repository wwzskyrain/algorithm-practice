/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumProductOfWordLengths.java, v 0.1 2021年12月21日 9:35 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumProductOfWordLengths {

    @LetCodeCommit(title = "318. Maximum Product of Word Lengths",
            selfRemark = "醉了，m题，一遍过，连修改都没有，怕了。"
                    + "这也说明我们的基本java代码能力是有了")
    public int maxProduct(String[] words) {
        int len = words.length;
        if (len < 2) {
            return 0;
        }
        int[] bitsArray = new int[len];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int bits = 0;
            for (int j = 0; j < word.length(); j++) {
                int shift = word.charAt(j) - 'a';
                bits |= (1 << shift);
            }
            bitsArray[i] = bits;
        }
        int ret = 0;
        for (int i = 0; i < bitsArray.length; i++) {
            for (int j = i + 1; j < bitsArray.length; j++) {
                if ((bitsArray[i] & bitsArray[j]) == 0) {
                    ret = Math.max(ret, words[i].length() * words[j].length());
                }
            }
        }
        return ret;
    }

    @Parameter
    public String[] words;
    @Parameter(1)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}, 16},
                {new String[] {"a", "ab", "abc", "d", "cd", "bcd", "abcd"}, 4},
                {new String[] {"a", "aa", "aaa", "aaaa"}, 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxProduct(words));
    }
}