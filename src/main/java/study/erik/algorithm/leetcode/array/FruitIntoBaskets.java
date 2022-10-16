/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : FruitIntoBaskets.java, v 0.1 2022年10月16日 20:54 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FruitIntoBaskets {

    @LetCodeCommit(title = "904. Fruit Into Baskets")
    public int totalFruit(int[] fruits) {

        int l = 0, r = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (r < fruits.length) {
            int type = fruits[r++];
            map.put(type, map.getOrDefault(type, 0) + 1);
            if (map.size() > 2) {
                // 这里只需要对l做处理就可以了。
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0) {
                    map.remove(fruits[l]);
                }
                l++;
            }
        }
        return r - l;
    }

    @Parameter
    public int[] fruits;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {1, 0, 1, 4, 1, 4, 1, 2, 3}, 5},
                {new int[] {1, 1}, 2},
                {new int[] {1, 2, 1}, 3},
                {new int[] {0, 1, 2, 2}, 3},
                {new int[] {1, 2, 3, 2, 2}, 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, totalFruit(fruits));
    }
}