/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ToLowerCase.java, v 0.1 2021年08月11日 11:00 下午 yueyi Exp $
 */
public class ToLowerCase {

    @LetCodeCommit(title = "To Lower Case")
    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        int distance = 'a' - 'A';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            c = c <= 'Z' && c >= 'A' ? ((char) (c + distance)) : c;
            sb.append(c);
        }
        return sb.toString();
    }

    @Test
    public void test_() {

        Assert.assertEquals("al&phabet", toLowerCase("al&phaBET"));
        Assert.assertEquals("hello", toLowerCase("Hello"));
        Assert.assertEquals("lovely", toLowerCase("LOVELY"));
        Assert.assertEquals("here", toLowerCase("here"));
    }
}