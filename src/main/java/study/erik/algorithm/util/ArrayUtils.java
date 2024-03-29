/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
     * demo = ["a","a","a","a","b","b","b","b","c","c","c","c","c","d","d","d"]
     *
     * @param str
     * @return
     */
    public static char[] buildCharArr(String str) {
        str = str.replace("[", "");
        str = str.replace("]", "");
        String[] split = str.split(",");
        char[] ret = new char[split.length];
        for (int i = 0; i < split.length; i++) {
            ret[i] = split[i].charAt(0);
        }
        return ret;
    }

    /**
     * 构建二维char数组。
     * [["5","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     *
     * @param str
     * @return
     */
    public static char[][] buildCharArr2Dimension(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return null;
        }
        if (str.startsWith("[[")) {
            str = str.substring(2, str.length());
        }
        if (str.endsWith("]]")) {
            str = str.substring(0, str.length() - 2);
        }

        String[] split1s = str.split(",\\[");
        char[][] ret = new char[split1s.length][];
        for (int i = 0; i < split1s.length; i++) {
            String split = split1s[i];
            if (split.endsWith("\n")) {
                split = split.substring(0, split.length() - 1);
            }
            if (split.endsWith("]")) {
                split = split.substring(0, split.length() - 1);
            }
            String[] splits2 = split.split(",");
            ret[i] = new char[splits2.length];
            for (int j = 0; j < splits2.length; j++) {
                ret[i][j] = splits2[j].charAt(1);
            }
        }
        return ret;
    }

    @Test
    public void testBuildCharArr2Dimension() {
        char[][] chars = buildCharArr2Dimension("[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"]\n" +
                ",[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"]\n" +
                ",[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"]\n" +
                ",[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"]\n" +
                ",[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"]\n" +
                ",[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"]\n" +
                ",[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"]\n" +
                ",[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"]\n" +
                ",[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]");

        for (char[] aChar : chars) {
            System.out.println(Arrays.toString(aChar));
        }
    }

    public static char[] buildCharArr(String[] split) {
        char[] ret = new char[split.length];
        for (int i = 0; i < split.length; i++) {
            ret[i] = split[i].charAt(0);
        }
        return ret;
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
        intervals = intervals.substring(1, intervals.length() - 1);
        List<String> arrStr = new ArrayList<>();
        int startIndex = 0, endIndex = 0;
        char[] chars = intervals.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            switch (c) {
                case '[':
                    startIndex = i;
                    break;
                case ']':
                    endIndex = i;
                    arrStr.add(intervals.substring(startIndex + 1, endIndex));
                    break;
                default:
            }
        }

        int[][] twoDimensionArray = new int[arrStr.size()][];
        for (int i = 0; i < arrStr.size(); i++) {
            String s = arrStr.get(i);
            if (s.trim().length() == 0) {
                twoDimensionArray[i] = new int[0];
                continue;
            }
            String[] split = s.split(",");
            twoDimensionArray[i] = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                String s1 = split[j];
                if (s1.trim().length() == 0) {
                    continue;
                }
                twoDimensionArray[i][j] = Integer.parseInt(s1);
            }
        }
        return twoDimensionArray;
    }

    public static List<List<Integer>> buildList2Dimension(String intervals) {
        int[][] array2Dimension = buildArray2Dimension(intervals);
        return Arrays.stream(array2Dimension)
                .map(ints -> Arrays.stream(ints).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    /**
     * [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"]]
     *
     * @param input
     * @return
     */
    public static List<List<String>> buildStrList2Dimension(String input) {
        if (input.equalsIgnoreCase("[]")) {
            return Collections.emptyList();
        }
        input = input.replace(" ", "");
        input = input.replace("[[", "");
        input = input.replace("]]", "");
        String[] split1 = input.split("],\\[");
        List<List<String>> ret = new ArrayList<>();
        for (String accounts : split1) {
            String[] split = accounts.split(",");
            ret.add(Arrays.stream(split).collect(Collectors.toList()));
        }
        return ret;
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


    public static List<Integer> buildList(String str) {
        int[] ints = buildArray(str);
        return Arrays.stream(ints).boxed().collect(Collectors.toList());
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
            array[i] = Integer.valueOf(split[i].trim());
        }
        return array;
    }

    public static double[] buildDoubleArray(String doubleString) {
        doubleString = doubleString.replace("[", "");
        doubleString = doubleString.replace("]", "");
        if (doubleString.length() == 0) {
            return new double[0];
        }
        String[] split = doubleString.split(",");
        double[] doubleArray = new double[split.length];
        for (int i = 0; i < split.length; i++) {
            doubleArray[i] = Double.parseDouble(split[i]);
        }
        return doubleArray;

    }

    public static int[] convertListTo(List<Integer> list) {
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }
}