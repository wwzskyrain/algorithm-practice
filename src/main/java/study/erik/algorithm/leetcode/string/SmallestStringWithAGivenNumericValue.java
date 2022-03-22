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

import java.util.Arrays;

/**
 * @author yueyi
 * @version : SmallestStringWithAGivenNumericValue.java, v 0.1 2022年03月22日 12:50 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SmallestStringWithAGivenNumericValue {

    @LetCodeCommit(title = "1663. Smallest String With A Given Numeric Value",
            diff = "m",
            selfRemark = "直接按照题意构造String")
    public String getSmallestString(int n, int k) {
        char[] ret = new char[n];
        Arrays.fill(ret, 'a');
        int i = ret.length - 1;
        k -= n;
        while (k > 0) {
            ret[i--] += Math.min(k, 25);
            k -= 25;
        }
        return String.valueOf(ret);
    }

    @Parameter
    public int    n;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3, 27, "aay"},
                {5, 73, "aaszz"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, getSmallestString(n, k));
    }

}