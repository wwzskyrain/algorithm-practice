/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yueyi
 * @version : CarFleet.java, v 0.1 2022年05月22日 16:40 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CarFleet {

    @LetCodeCommit(title = "853. Car Fleet",
            diff = "m",
            selfRemark = "原来是一个应用题")
    public int carFleet(int target, int[] position, int[] speed) {

        int n = position.length;
        Pair<Integer, Integer>[] pairs = new Pair[n];
        for (int i = 0; i < pairs.length; i++) {
            pairs[i] = new Pair<>(position[i], speed[i]);
        }
        Arrays.sort(pairs, Comparator.comparing(Pair::getKey));
        double time = (double) (target - pairs[n - 1].getKey()) / pairs[n - 1].getValue();
        int total = 1;
        for (int i = pairs.length - 2; i >= 0; i--) {
            double nextTime = (double) (target - pairs[i].getKey()) / pairs[i].getValue();
            if (nextTime > time) {
                total++;
                time = nextTime;
            }
        }
        return total;
    }

    @Parameter
    public int   target;
    @Parameter(1)
    public int[] position;
    @Parameter(2)
    public int[] speed;
    @Parameter(3)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {12, ArrayUtils.buildArray("[10,8,0,5,3]"), ArrayUtils.buildArray("[2,4,1,1,3]"), 3},
                {10, ArrayUtils.buildArray("[3]"), ArrayUtils.buildArray("[3]"), 1},
                {100, ArrayUtils.buildArray("[0,2,4]"), ArrayUtils.buildArray("[4,2,1]"), 1},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, carFleet(target, position, speed));
    }
}