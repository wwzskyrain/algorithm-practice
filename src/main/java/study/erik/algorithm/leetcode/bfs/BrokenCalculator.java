/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BrokenCalculator.java, v 0.1 2022年03月23日 10:48 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BrokenCalculator {

    @LetCodeCommit(title = "991. Broken Calculator",
            diff = "m",
            tag = "好题",
            selfRemark = "期初用了bfs，太慢了。仔细捉摸下，原来有问题的递归性。"
                    + "当然这里也可以用迭代写法.")
    public int brokenCalc(int startValue, int target) {
        if (startValue >= target) {
            return startValue - target;
        }
        int half = (target + 1) / 2;
        int ret = brokenCalc(startValue, half);
        return ret + (target % 2 == 0 ? 1 : 2);
    }

    @Parameter
    public int startValue;
    @Parameter(1)
    public int target;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {2, 3, 2},
                {5, 8, 2},
                {3, 10, 3},
                {1, 1000000000, 39}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, brokenCalc(startValue, target));
    }

}