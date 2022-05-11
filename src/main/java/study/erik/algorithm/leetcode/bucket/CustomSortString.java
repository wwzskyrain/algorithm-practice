/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bucket;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CustomSortString.java, v 0.1 2022年05月11日 09:23 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CustomSortString {

    @LetCodeCommit(title = "791. Custom Sort String",
            diff = "m",
            selfRemark = "很直白的一个bucket题目")
    public String customSortString(String order, String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : order.toCharArray()) {
            while (count[c - 'a']-- > 0) {
                sb.append(c);
            }
        }
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                sb.append((char) (i + 'a'));
            }
        }
        return sb.toString();
    }

    @Parameter
    public String order;
    @Parameter(1)
    public String s;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"cba", "abcd", "cbad"},
                {"cbafg", "abcd", "cbad"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, customSortString(order, s));
    }
}