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

    /**
     * 构造二维数组
     *
     * @param intervals demo： intervals = [[1,4],[3,6],[2,8]]
     */
    public static int[][] buildArray2Dimension(String intervals) {
        if (intervals.equalsIgnoreCase("[]")) {
            return new int[0][0];
        }
        intervals = intervals.replace(" ", "");
        intervals = intervals.replace("[[", "");
        intervals = intervals.replace("]]", "");
        String[] split1 = intervals.split("],\\[");
        int[][] twoDimensionArray = new int[split1.length][];
        for (int i = 0; i < split1.length; i++) {
            String arr2Str = split1[i];
            String[] split2 = arr2Str.split(",");
            twoDimensionArray[i] = new int[split2.length];
            for (int j = 0; j < split2.length; j++) {
                twoDimensionArray[i][j] = Integer.valueOf(split2[j]);
            }
        }
        return twoDimensionArray;
    }

    public static char[][] buildArray2DimensionToChar(String intervals) {
        intervals = intervals.replace("[[", "");
        intervals = intervals.replace("]]", "");
        String[] split1 = intervals.split("],\\[");
        char[][] twoDimensionArray = new char[split1.length][];
        for (int i = 0; i < split1.length; i++) {
            String arr2Str = split1[i];
            String[] split2 = arr2Str.split(",");
            twoDimensionArray[i] = new char[split2.length];
            for (int j = 0; j < split2.length; j++) {
                twoDimensionArray[i][j] = split2[j].charAt(1);
            }
        }
        return twoDimensionArray;
    }

    /**
     * 构造一维数组
     *
     * @param intervals demo： intervals = [3,6,5,1,8]
     */
    public static int[] buildArray(String intervals) {
        intervals = intervals.replace("[", "");
        intervals = intervals.replace("]", "");
        if (intervals.length() == 0) {
            return new int[0];
        }
        String[] split = intervals.split(",");
        int[] array = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            array[i] = Integer.valueOf(split[i]);
        }
        return array;
    }
}