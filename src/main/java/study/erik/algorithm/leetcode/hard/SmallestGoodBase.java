/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : SmallestGoodBase.java, v 0.1 2021年06月18日 7:52 上午 yueyi Exp $
 */
public class SmallestGoodBase {

    @LetCodeCommit(title = "Smallest Good Base")
    public String smallestGoodBase(String n) {
        return "";
    }

    @Parameter
    public String n;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"13", "3"},
                {"4681", "8"},
                {"1000000000000000000", "999999999999999999"}
        };
    }

    @Test
    public void test() {
        Assert.assertEquals(expect, smallestGoodBase(n));
    }

}