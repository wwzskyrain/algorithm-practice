/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
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

/**
 * @author yueyi
 * @version : ReducingDishes.java, v 0.1 2023年05月05日 08:32 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ReducingDishes {

    @LetCodeCommit(title = "1402. Reducing Dishes",
    diff = "h",
    selfRemark = "看这个解法，你不会相信这是个hard题目——O(n)的时间和O(1)的空间"
            + "这个题目看上去很难的：要挑选数字，并且乘上一个自然系数。"
            + "但是结合其求值：可以想想把最大值乘以最大系数。所以排序就有了。"
            + "最大系数是逐次数加的，于是每次加上当前的total就有了。"
            + "最后，total小于0了就对最终的值没有正贡献了，就结束了。")
    public int maxSatisfaction(int[] A) {
        Arrays.sort(A);
        int res = 0, total = 0, n = A.length;
        for (int i = n - 1; i >= 0 && A[i] > -total; --i) {
            total += A[i];
            res += total;
        }
        return res;
    }

    @Parameter
    public int[] A;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[-1,-8,0,5,-9]"), 14},
                {ArrayUtils.buildArray("[4,3,2]"), 20},
                {ArrayUtils.buildArray("[-1,-4,-5]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxSatisfaction(A));
    }
}