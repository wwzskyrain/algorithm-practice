/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
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
 * @version : PreviousPermutationWithOneSwap.java, v 0.1 2021年08月11日 10:28 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PreviousPermutationWithOneSwap {

    @LetCodeCommit(title = "Previous Permutation With One Swap",
            selfRemark = "和'Next Greater Element III'的解法一样，"
                    + "效果也是100%",
            related = "Next Greater Element III")
    public int[] prevPermOpt1(int[] arr) {
        return resolve(arr);
    }

    public int[] resolve(int[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] <= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return arr;
        }
        int foundIndex = Arrays.binarySearch(arr, i + 1, arr.length, arr[i]);
        if (foundIndex < 0) {
            foundIndex = -(foundIndex + 1);
        }
        if (foundIndex >= arr.length) {
            foundIndex--;
        }
        while (foundIndex >= 0 && arr[foundIndex] >= arr[i]) {
            foundIndex--;
        }
        while (foundIndex > 0 && arr[foundIndex] == arr[foundIndex - 1]) {
            foundIndex--;
        }
        int temp = arr[foundIndex];
        arr[foundIndex] = arr[i];
        arr[i] = temp;
        return arr;
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[3,2,1]"), ArrayUtils.buildArray("[3,1,2]")},
                {ArrayUtils.buildArray("[1,1,5]"), ArrayUtils.buildArray("[1,1,5]")},
                {ArrayUtils.buildArray("[1,9,4,6,7]"), ArrayUtils.buildArray("[1,7,4,6,9]")},
                {ArrayUtils.buildArray("[3,1,1,3]"), ArrayUtils.buildArray("[1,3,1,3]")},
        };
    }

    @Test
    public void test_arr() {
        Assert.assertArrayEquals(expect, prevPermOpt1(arr));
    }
}