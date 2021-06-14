/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : PeakIndexInAMountainArray.java, v 0.1 2021年06月15日 7:28 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PeakIndexInAMountainArray {

    @LetCodeCommit(title = "Peak Index in a Mountain Array")
    public int peakIndexInMountainArray(int[] arr) {
        return solution(arr);
    }

    /**
     * 题目已经说明，O(n)的解法是显而易见的，让找出O(log(n))的解法，本解法就是。
     * 注意细节，这里在二分时，是如何判断趋势并且如何重新赋值l和h的，这两点是一个小细节，根据题意而变化。
     * 主要，这个框架是不变的。
     *
     * @param arr
     * @return
     */
    public int solution(int[] arr) {
        int l = 0;
        int h = arr.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (arr[m] < arr[m + 1]) {
                // 上升
                l = m + 1;
            } else {
                h = m;
            }
        }
        return l;
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {0, 1, 0}, 1},
                {new int[] {0, 2, 1, 0}, 1},
                {new int[] {0, 10, 5, 2}, 1},
                {new int[] {3, 4, 5, 1}, 2},
                {new int[] {24, 69, 100, 99, 79, 78, 67, 36, 26, 19}, 2}
        };
    }

    @Test
    public void test() {
        Assert.assertEquals(expect, peakIndexInMountainArray(arr));
    }
}