/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

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
 * @version : Heaters.java, v 0.1 2022年03月01日 9:27 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class Heaters {

    @LetCodeCommit(title = "475. Heaters",
            selfRemark = "对每一个房子，循环找最近的heater，计算其半径."
                    + "也可以用双指针。"
                    + "所以这个题目不难，有点low了")
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int max = 0;
        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index >= 0) {
                continue;
            }
            index = -(index + 1);
            int distance;
            if (index == 0) {
                distance = heaters[0] - house;
            } else if (index == heaters.length) {
                distance = house - heaters[heaters.length - 1];
            } else {
                int left = heaters[index - 1];
                int right = heaters[index];
                distance = Math.min(right - house, house - left);
            }
            max = Math.max(max, distance);
        }
        return max;
    }

    @Parameter
    public int[] houses;
    @Parameter(1)
    public int[] heaters;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923]"),
                        ArrayUtils.buildArray(
                                "[823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612]"),
                        161834419},
                {ArrayUtils.buildArray("[1,2,3]"), ArrayUtils.buildArray("[2]"), 1},
                {ArrayUtils.buildArray("[1,2,3,4]"), ArrayUtils.buildArray("[1,4]"), 1},
                {ArrayUtils.buildArray("[1,5]"), ArrayUtils.buildArray("[2]"), 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findRadius(houses, heaters));
    }
}