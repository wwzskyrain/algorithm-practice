/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.TreeMap;

/**
 * @author yueyi
 * @version : OddEvenJump.java, v 0.1 2022年12月04日 10:41 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class OddEvenJump {

    @LetCodeCommit(title = "975. Odd Even Jump",
    diff = "m",
    selfRemark = "这个题目有意思，思路清晰。"
            + "不过这里用tree确实有点慢了，。"
            + "我最初的思路是用排序+二分查找，但是不保证很快，因为排序毕竟会O(NlogN)")
    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        int res = 0;
        boolean[] higher = new boolean[n];
        boolean[] lower = new boolean[n];
        higher[n - 1] = lower[n - 1] = true;
        // key=n, value = index
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(arr[n - 1], n - 1);
        for (int i = n - 1; i >= 0; i--) {
            int num = arr[i];
            Integer higherKey = map.ceilingKey(num);
            Integer lowerKey = map.floorKey(num);
            if (higherKey != null) {
                higher[i] = lower[map.get(higherKey)];
                if (higher[i]) {
                    res++;
                }
            }
            if (lowerKey != null) {
                lower[i] = higher[map.get(lowerKey)];
            }
            map.put(num, i);
        }
        return res;
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[10,13,12,14,15]"), 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, oddEvenJumps(arr));
    }
}