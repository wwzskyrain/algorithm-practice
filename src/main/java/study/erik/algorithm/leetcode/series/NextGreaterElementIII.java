/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : NextGreaterElementIII.java, v 0.1 2021年08月10日 11:53 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NextGreaterElementIII {

    @LetCodeCommit(title = "Next Greater Element III")
    public int nextGreaterElement(int n) {
        return resolve(n);
    }

    /**
     * 扣扣弄弄，竟然成绩100.00%
     *
     * @param n
     * @return
     */
    public int resolve(int n) {
        /**
         * 1.找倒数第一个顺序，
         * 2.然后把后面第一个大于它的数字替换
         * 3.把后面再顺序排列
         */
        char[] chars = String.valueOf(n).toCharArray();
        int i = chars.length - 1;
        //寻找第一个顺序
        while (i > 0 && chars[i - 1] >= chars[i]) {
            i--;
        }
        if (i == 0) {
            return -1;
        }
        //排序为了折半查找
        Arrays.sort(chars, i, chars.length);
        //折半查找-第一个大于chars[i-1]的元素
        int foundIndex = Arrays.binarySearch(chars, i, chars.length, chars[i - 1]);
        int insertIndex = foundIndex >= 0 ? foundIndex : -(foundIndex + 1);
        while (insertIndex < chars.length && chars[insertIndex] <= chars[i - 1]) {
            insertIndex++;
        }
        //交换
        char tempChar = chars[insertIndex];
        chars[insertIndex] = chars[i - 1];
        chars[i - 1] = tempChar;
        //再排序
        Arrays.sort(chars, i, chars.length);
        Long l = Long.valueOf(String.valueOf(chars));
        Long maxInt = 0x7fffffffL;
        return l > maxInt ? -1 : l.intValue();
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {12443322, 13222344},
                {476, 647},
                {2147483476, 2147483647},
                {2147483486, -1},
                {11, -1},
                {1234, 1243},
                {1243, 1324},
                {1423, 1432},
                {1432, 2134},
                {4132, 4213},
                {4132, 4213},
                {4312, 4321},
                {4321, -1},
                {120, 201},
                {123, 132},
                {213, 231},
                {231, 312},
                {312, 321},
                {321, -1},
                {1203, 1230},
                {1302, 1320},
                {12, 21},
                {21, -1},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, nextGreaterElement(n));
    }
}