/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ConvertToBase_2.java, v 0.1 2022年12月09日 23:04 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ConvertToBase_2 {

    @LetCodeCommit(title = "1017. Convert to Base -2")
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            if (n % -2 == 0) {
                sb.append("0");
            } else {
                n--;
                sb.append("1");
            }
            n = n / -2;
        }
        return sb.reverse().toString();
    }

    @Parameter
    public int    n;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {2, "110"},
                {3, "111"},
                {4, "100"},
                {5, "101"},
                {6, "11010"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, baseNeg2(n));
    }

    private void d(int i) {
        System.out.printf("%d -> -2 = %d....%d\n", i, i / -2, i % -2);
    }

}