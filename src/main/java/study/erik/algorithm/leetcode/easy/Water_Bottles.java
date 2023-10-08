package study.erik.algorithm.leetcode.easy;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/8 ，时间：11:43
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Water_Bottles {

    @LetCodeCommit(title = "1518. Water Bottles")
    public int numWaterBottles(int numBottles, int numExchange) {
        int total = numBottles;
        int empty = numBottles;
        while (empty >= numExchange) {
            int water = empty / numExchange;
            total += water;
            empty = empty % numExchange + water;
        }
        return total;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {13, 9, 3},
                {19, 15, 4},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int numBottles;
    @Parameterized.Parameter(2)
    public int numExchange;

    @Test
    public void test() {
        Assert.assertEquals(expect, numWaterBottles(numBottles, numExchange));
    }

}
