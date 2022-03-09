/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : Base7.java, v 0.1 2022年03月09日 9:01 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class Base7 {

    @LetCodeCommit(title = "504. Base 7")
    public String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        int absNum = num > 0 ? num : -num;
        while (absNum > 0) {
            sb.append(absNum % 7 + "");
            absNum /= 7;
        }
        String ret = sb.reverse().toString();
        return (num < 0 ? "-" : "") + (ret.length() == 0 ? "0" : ret);
    }

    @Parameter
    public int    num;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {100, "202"},
                {-7, "-10"},
                {0, "0"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, convertToBase7(num));
    }
}