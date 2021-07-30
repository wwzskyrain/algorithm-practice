/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.util;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : ArraysUtil.java, v 0.1 2021年07月30日 9:13 上午 yueyi Exp $
 */
public class ArrayUtils {

    public static void printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        for (int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
    }
}