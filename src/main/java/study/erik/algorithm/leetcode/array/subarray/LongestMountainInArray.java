/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.subarray;

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
 * @version : LongestMountainInArray.java, v 0.1 2022年05月22日 09:41 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LongestMountainInArray {

    @LetCodeCommit(title = "845. Longest Mountain in Array",
            diff = "m",
            related = "mountain array还是比较简单的."
                    + "1.模式简单"
                    + "2.不会有overlap")
    public int longestMountain(int[] arr) {

        int max = 0;
        int startIdx = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                // count
                if (m == 2) {
                    max = Math.max(max, i - startIdx);
                }
                // reset
                m = 0;
            } else if (arr[i] > arr[i - 1]) {
                if (m == 0) {
                    m = 1;
                    startIdx = i - 1;
                } else if (m == 1) {
                    continue;
                } else {
                    max = Math.max(max, i - startIdx);
                    m = 1;
                    startIdx = i - 1;
                }
            } else {
                if (m == 0) {
                    continue;
                } else if (m == 1) {
                    m = 2;
                } else {
                    continue;
                }
            }
        }
        if (m == 2) {
            max = Math.max(max, arr.length - startIdx);
        }
        return max;
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[0,1,2,3,4,5,4,3,2,1,0]"), 11},
                {ArrayUtils.buildArray("[2,1,4,7,3,2,5]"), 5},
                {ArrayUtils.buildArray("[2,2,2]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestMountain(arr));
    }
}