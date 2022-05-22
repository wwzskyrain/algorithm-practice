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
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : MostProfitAssigningWork.java, v 0.1 2022年05月21日 09:39 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MostProfitAssigningWork {

    @LetCodeCommit(title = "826. Most Profit Assigning Work",
            diff = "m",
            selfRemark = "文不加点一遍过.")
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {

        Pair<Integer, Integer>[] pairs = new Pair[difficulty.length];
        for (int i = 0; i < difficulty.length; i++) {
            pairs[i] = new Pair<>(difficulty[i], profit[i]);
        }
        Arrays.sort(pairs, Comparator.comparing(Pair::getKey));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(pairs[0].getKey(), pairs[0].getValue());
        for (int i = 1; i < pairs.length; i++) {
            Pair<Integer, Integer> pair = pairs[i];
            Integer preKey = pairs[i - 1].getKey();
            Integer preValue = map.get(preKey);
            map.put(pair.getKey(), Math.max(pair.getValue(), preValue));
        }

        int t = 0;
        for (int i : worker) {
            Integer floorKey = map.floorKey(i);
            t += floorKey != null ? map.get(floorKey) : 0;
        }
        return t;
    }

    @Parameter
    public int[] difficulty;
    @Parameter(1)
    public int[] profit;
    @Parameter(2)
    public int[] worker;
    @Parameter(3)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,4,6,8,10]"), ArrayUtils.buildArray("[10,20,30,40,50]"), ArrayUtils.buildArray("[4,5,6,7]"), 100},
                {ArrayUtils.buildArray("[85,47,57]"), ArrayUtils.buildArray("[24,66,99]"), ArrayUtils.buildArray("[40,25,25]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxProfitAssignment(difficulty, profit, worker));
    }
}