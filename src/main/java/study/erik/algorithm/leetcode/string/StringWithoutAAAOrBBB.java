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
 * @version : StringWithoutAAAOrBBB.java, v 0.1 2022年11月26日 22:20 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StringWithoutAAAOrBBB {

    @LetCodeCommit(title = "984. String Without AAA or BBB")
    public String strWithout3a3b(int a, int b) {
        return greedy(a, b);
    }

    public String greedy(int a, int b) {
        char[] res = new char[a + b];
        int i = 0;

        while (a > 0 || b > 0) {
            boolean writeA = false;
            if (i > 1 && res[i - 1] == res[i - 2]) {
                if (res[i - 1] == 'b') {
                    writeA = true;
                }
            } else {
                if (a >= b) {
                    writeA = true;
                }
            }
            if (writeA) {
                res[i] = 'a';
                a--;
            } else {
                res[i] = 'b';
                b--;
            }
            i++;
        }
        return new String(res);
    }

    @Parameter
    public int    a;
    @Parameter(1)
    public int    b;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, 2, "abb"},
                {4, 1, "aabaa"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, strWithout3a3b(a, b));
    }
}