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
 * @version : NumberComplement.java, v 0.1 2021年12月28日 8:18 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberComplement {

    @LetCodeCommit(title = "476. Number Complement",
            selfRemark = "位操作题目，也不算，只能算是未操作基本题目。"
                    + "主要求最近的补码m")
    public int findComplement(int num) {
        int m = 1;
        while (m < num) {
            m = (m << 1) | 1;
        }
        return m ^ num;
    }

    @Parameter
    public int num;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {5, 2},
                {1, 0},
                {7, 0},
                {2147483647, 0}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findComplement(num));
    }

}