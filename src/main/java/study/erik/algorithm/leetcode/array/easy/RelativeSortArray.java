/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

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
 * @version : RelativeSortArray.java, v 0.1 2023年01月12日 21:39 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RelativeSortArray {

    @LetCodeCommit(title = "1122. Relative Sort Array")
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[arr2.length];
        int i = 0;
        int last = arr1.length - 1;
        while (i <= last) {
            int a = arr1[i];
            int j = 0;
            while (j < arr2.length) {
                if (a == arr2[j]) {
                    count[j]++;
                    break;
                }
                j++;
            }
            if (j >= arr2.length) {
                int t = arr1[last];
                arr1[last] = arr1[i];
                arr1[i] = t;
                last--;
            } else {
                i++;
            }
        }
        i = 0;
        for (int j = 0; j < count.length; j++) {
            while (count[j] > 0) {
                arr1[i++] = arr2[j];
                count[j]--;
            }
        }
        Arrays.sort(arr1, i, arr1.length);
        return arr1;
    }

    @Parameter
    public int[] arr1;
    @Parameter(1)
    public int[] arr2;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,3,1,3,2,4,6,7,9,2,19]"), ArrayUtils.buildArray("[2,1,4,3,9,6]")},
                {ArrayUtils.buildArray("[28,6,22,8,44,17]"), ArrayUtils.buildArray("[22,28,8,6]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(relativeSortArray(arr1, arr2)));
    }

}