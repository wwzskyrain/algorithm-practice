/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ThreeEqualParts.java, v 0.1 2021年07月17日 5:10 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ThreeEqualParts {

    @LetCodeCommit(title = "Three Equal Parts",
            selfRemark = "看到讨论区的答案也是长篇大论，我就知道这个题目没什么好招数。"
                    + "看题意也没啥好歪点子使。本解法没什么高明之处，就是简单的二重循环。"
                    + "可以再循环次数上再优化下，以便早点返回")
    public int[] threeEqualParts(int[] arr) {
        return resolve(arr);
    }

    public int[] resolve(int[] arr) {
        int[] notFound = {-1, -1};
        int iStart = 0;
        while (iStart < arr.length && arr[iStart] != 1) {
            iStart++;
        }
        if (iStart >= arr.length) {
            return new int[] {0, arr.length - 1};
        }
        for (int i = iStart; i < arr.length; i++) {
            int jStart = i + 1;
            while (jStart < arr.length && arr[jStart] != 1) {
                jStart++;
            }
            if (jStart >= arr.length) {
                continue;
            }
            if (!isEqual(arr, iStart, i - iStart + 1, jStart)) {
                continue;
            }
            int j = jStart + (i - iStart) + 1;
            int kStart = j;
            while (kStart < arr.length && arr[kStart] != 1) {
                kStart++;
            }
            if (kStart >= arr.length) {
                continue;
            }
            if (kStart + i - iStart + 1 != arr.length) {
                continue;
            }
            if (isEqual(arr, iStart, i - iStart + 1, kStart)) {
                return new int[] {i, j};
            }
        }
        return notFound;
    }

    public boolean isEqual(int[] arr, int iStart, int length, int jStar) {
        if (jStar + length > arr.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (arr[iStart + i] != arr[jStar + i]) {
                return false;
            }
        }
        return true;
    }

    @Parameter()
    public int[] arr;
    @Parameter(1)
    public int[] except;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {1, 1, 0, 1, 1}, new int[] {-1, -1}},
                {new int[] {0, 0, 0, 1, 1, 1}, new int[] {3, 5}},
                {new int[] {0, 0, 0, 0, 0}, new int[] {0, 4}},
                {new int[] {0, 1, 0, 1, 1}, new int[] {1, 4}},
                {new int[] {1, 0, 1, 0, 1}, new int[] {0, 3}},
                {new int[] {1, 1, 0, 1, 1}, new int[] {-1, -1}},
                {new int[] {1, 1, 0, 0, 1}, new int[] {0, 2}},
        };
    }

    @Test
    public void test_arr() {
        Assert.assertArrayEquals(except, threeEqualParts(arr));
    }

}