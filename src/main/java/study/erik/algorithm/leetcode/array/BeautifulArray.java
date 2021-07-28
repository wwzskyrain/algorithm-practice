/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BeautifulArray.java, v 0.1 2021年07月29日 12:18 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BeautifulArray {

    @LetCodeCommit(title = "Beautiful Array")
    public int[] beautifulArray(int n) {
        return resolveWithRecursive(n);
    }

    private int[] r1 = {1};
    private int[] r2 = {2, 1};
    private int[] r3 = {2, 1, 3};
    private int[] r4 = {2, 1, 4, 3};
    private int[] r5 = {3, 1, 2, 5, 4};

    public int[] resolveWithRecursive(int n) {
        switch (n) {
            case 1:
                return r1;
            case 2:
                return r2;
            case 3:
                return r3;
            case 4:
                return r4;
            case 5:
                return r5;
        }
        int halfN = n / 2 + (n % 2 == 0 ? 0 : 1);
        int[] right = resolveWithRecursive(halfN);
        int[] left = new int[right.length];
        for (int i = 0; i < right.length; i++) {
            right[i] += right[i];
            left[i] = right[i] - 1;
        }
        int[] result = new int[n];
        for (int i = 0; i < result.length; i++) {
            if (i < left.length) {
                result[i] = left[i];
                continue;
            }
            result[i] = right[i - left.length];
        }
        return result;
    }

    @Parameter
    public int   n;
    @Parameter(1)
    public int[] except;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {4, new int[] {2, 1, 4, 3}},
                {5, new int[] {3, 1, 2, 5, 4}}
        };
    }

    @Test
    public void test_except() {
        Assert.assertArrayEquals(except, beautifulArray(n));
    }

}