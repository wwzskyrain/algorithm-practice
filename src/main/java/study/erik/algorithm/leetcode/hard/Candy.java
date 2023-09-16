/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author yueyi
 * @version : Candy.java, v 0.1 2021年12月19日 10:58 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class Candy {

    @LetCodeCommit(title = "135. Candy",
            selfRemark = "分糖果。" +
                    "这解法也太简单了吧。")
    public int candy(int[] ratings) {
        if (ratings.length == 0) {
            return 0;
        }
        int[] c = new int[ratings.length];
        Arrays.fill(c, 1);
        for (int i = 1; i < c.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                c[i] = c[i - 1] + 1;
            }
        }
        for (int i = c.length - 1; i > 0; i--) {
            if (ratings[i] < ratings[i - 1]) {
                c[i - 1] = Math.max(c[i - 1], c[i] + 1);
            }
        }
        int n = 0;
        for (int i : c) {
            n += i;
        }
        return n;
    }

    @Parameter
    public int[] ratings;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {ArrayUtils.buildArray("[1,0,2]"), 5},
                {ArrayUtils.buildArray("[1,2,2]"), 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, candy(ratings));
    }
}