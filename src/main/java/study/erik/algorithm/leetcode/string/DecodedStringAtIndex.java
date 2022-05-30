/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
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
 * @version : DecodedStringAtIndex.java, v 0.1 2022年05月30日 12:55 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class DecodedStringAtIndex {

    @LetCodeCommit(title = "880. Decoded String at Index",
            diff = "m",
            selfRemark = "首先按照题意操作来解题，找到了答案，但是空间超出了。"
                    + "然后看答案，竟然用了计算加backward。"
                    + "其中k%=size，也是很novel的")
    public String decodeAtIndex(String s, int k) {
        long size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '2' && c <= '9') {
                size *= c - '0';
            } else {
                size++;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            k %= size;
            if (k == 0 && c >= 'a' && c <= 'z') {
                return c + "";
            }
            if (c >= '2' && c <= '9') {
                size /= c - '0';
            } else {
                size--;
            }
        }
        return null;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"leet2code3", 10, "o"},
                {"ha22", 5, "h"},
                {"a2345678999999999999999", 1, "a"},
                {"y959q969u3hb22odq595", 222280369, "y"},
                {"a23", 6, "a"}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, decodeAtIndex(s, k));
    }
}