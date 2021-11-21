/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BestSightseeingPair.java, v 0.1 2021年11月21日 7:34 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BestSightseeingPair {

    @LetCodeCommit(title = "Best Sightseeing Pair")
    /**
     * 必须解释一下：
     * 先解释代码，bestIIndex就是前面的最好的i的索引。
     * 本质上，这个题目是让一左一右的两个数字的和最大化。左边为i右边为j吧。
     * 这两个数字不属于同一个数组，可以把values按照题意变成两个数组：
     * a1 = value[i] + i
     * a2 = value[j] - j
     * 问题是max{a1[i] + a2[j]}, 其中i<j。
     * 假设a1[i] + a2[j]的时候，a1[j] > a1[i],
     * 这个时候，a1[i] + a2[j+1之后]的和，都会比a1[j] + a2[j+1之后]小，所以要更新一下，用j代表最好的i
     *
     */
    public int maxScoreSightseeingPair(int[] values) {
        int res = values[0];
        int bestIIndex = 0;
        for (int j = 1; j < values.length; j++) {
            res = Math.max(res, (values[bestIIndex] + bestIIndex) + (values[j] - j));
            if (values[j] + j > values[bestIIndex] + bestIIndex) {
                bestIIndex = j;
            }
        }
        return res;
    }

    @Parameter
    public int[] values;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {8, 1, 5, 2, 6}, 11},
                {new int[] {1, 2}, 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxScoreSightseeingPair(values));
    }
}