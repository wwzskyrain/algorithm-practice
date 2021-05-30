/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumOfAbsoluteValueExpression.java, v 0.1 2021年05月15日 11:58 上午 yueyi Exp $
 */
public class MaximumOfAbsoluteValueExpression {

    @LetCodeCommit(title = "Maximum of Absolute Value Expression", no = 1131)
    public int maxAbsValExpr(int[] arr1, int[] arr2) {

        int max = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                int r = Math.abs(arr1[i] - arr1[j]) + Math.abs(arr2[i] - arr2[j]) + Math.abs(i - j);
                max = Math.max(max, r);
            }
        }
        return max;
    }

    @Test
    public void test_1() {
        int[] arr1 = {1, 2, 3, 4}, arr2 = {-1, 4, 5, 6};
        Assert.assertEquals(13, maxAbsValExpr(arr1, arr2));
    }

    @Test
    public void test_2() {
        int[] arr1 = {1, -2, -5, 0, 10}, arr2 = {0, -2, -1, -7, -4};
        Assert.assertEquals(20, maxAbsValExpr(arr1, arr2));
    }
}