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
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : ArrayDoubledPairs.java, v 0.1 2021年08月12日 9:07 上午 yueyi Exp $
 */

@RunWith(Parameterized.class)
public class ArrayDoubledPairs {

    @LetCodeCommit(title = "Array of Doubled Pairs",
            selfRemark = "这里还是比较简单的。因为用到了排序。")
    public boolean canReorderDoubled(int[] arr) {
        return resolve(arr);
    }

    public boolean resolve(int[] arr) {
        Map<Integer, Integer> countMap = new TreeMap<>();
        for (int i : arr) {
            countMap.put(i, countMap.getOrDefault(i, 0) + 1);
        }
        for (Integer e : countMap.keySet()) {
            if (countMap.get(e) == 0) {
                continue;
            }
            int want = e < 0 ? e / 2 : e * 2;
            if (e < 0 && e % 2 != 0 || countMap.get(e) > countMap.getOrDefault(want, 0)) {
                return false;
            }
            countMap.put(want, countMap.get(want) - countMap.get(e));
        }
        return true;
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {ArrayUtils.buildArray("[3,1,3,6]"), false},
                {ArrayUtils.buildArray("[2,1,2,6]"), false},
                {ArrayUtils.buildArray("[4,-2,2,-4]"), true},
                {ArrayUtils.buildArray("[1,2,4,16,8,4]"), false},
                };
    }

    @Test
    public void test_arr() {
        Assert.assertEquals(expect, canReorderDoubled(arr));
    }

}