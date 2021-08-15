/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FinalPricesWithSpecialDiscount.java, v 0.1 2021年08月15日 9:52 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FinalPricesWithSpecialDiscount {

    @LetCodeCommit(title = "Final Prices With a Special Discount in a Shop",
            selfRemark = "严格单调递增栈。用数组来实现栈，相对于LinkedList，会提高很多，时间从3ms->1ms，排名也到了97%")
    public int[] finalPrices(int[] prices) {
        int[] result = new int[prices.length];
        int[] stack = new int[prices.length];
        int top = 0;
        for (int i = 0; i < prices.length; i++) {
            //如果不满足严格单调递增栈的定义，就出栈
            while (top > 0 && prices[stack[top - 1]] >= prices[i]) {
                result[stack[top - 1]] = prices[stack[top - 1]] - prices[i];
                top--;
            }
            stack[top++] = i;
        }
        while (top > 0) {
            result[stack[top - 1]] = prices[stack[top - 1]];
            top--;
        }
        return result;
    }

    @Parameter
    public int[] prices;
    @Parameter(1)
    public int[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[8,4,6,2,3]"), ArrayUtils.buildArray("[4,2,4,2,3]")},
                {ArrayUtils.buildArray("[1,2,3,4,5]"), ArrayUtils.buildArray("[1,2,3,4,5]")},
                {ArrayUtils.buildArray("[10,1,1,6]"), ArrayUtils.buildArray("[9,0,1,6]")},
        };
    }

    @Test
    public void test_prices() {
        Assert.assertArrayEquals(expect, finalPrices(prices));
    }

}