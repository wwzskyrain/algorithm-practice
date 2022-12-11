/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
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
 * @version : DuplicateZeros.java, v 0.1 2022年12月10日 23:16 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class DuplicateZeros {

    @LetCodeCommit(title = "1089. Duplicate Zeros")
    public void duplicateZeros(int[] arr) {
        int[] stack = new int[arr.length];
        int iStack = 0;
        int l = arr.length;
        int i = 0;
        while (i < l) {
            if (arr[i] == 0) {
                if (i + iStack + 1 < l) {
                    stack[iStack++] = i;
                } else {
                    break;
                }
            }
            if (i + 1 + iStack < l) {
                i++;
            } else {
                break;
            }
        }
        int j = l - 1;
        while (j > i) {
            arr[j--] = arr[i];
            if (stack[iStack - 1] == i) {
                arr[j--] = 0;
                iStack--;
            }
            i--;
        }
    }

    @Parameter
    public int[] arr;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray(
                        "[9,9,9,4,8,0,0,3,7,2,0,0,0,0,9,1,0,0,1,1,0,5,6,3,1,6,0,0,2,3,4,7,0,3,9,3,6,5,8,9,1,1,3,2,0,0,7,3,3,0,5,7,0,8,1,"
                                + "9,6,3,0,8,8,8,8,0,0,5,0,0,0,3,7,7,7,7,5,1,0,0,8,0,0]")},
                {ArrayUtils.buildArray("[0,0,0,0,0,0,0]")},
                {ArrayUtils.buildArray("[1,0,2,3,0,4,5,0]")},
        };
    }

    @Test
    public void test_() {
        duplicateZeros(arr);
        System.out.println(Arrays.toString(arr));
    }

}