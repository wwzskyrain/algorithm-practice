/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : ReorderedPowerOf2.java, v 0.1 2022年05月28日 11:17 yueyi Exp $
 */
public class ReorderedPowerOf2 {

    @LetCodeCommit(title = "869. Reordered Power of 2",
            diff = "m",
            selfRemark = "数字字符串的permutation")
    public boolean reorderedPowerOf2(int n) {
        int[] array = countArray(n);
        for (int i = 0; i < 31; i++) {
            if (Arrays.equals(array, countArray(1 << i))) {
                return true;
            }
        }
        return false;
    }

    public int[] countArray(int n) {
        int[] arr = new int[10];
        while (n > 0) {
            arr[n % 10]++;
            n /= 10;
        }
        return arr;
    }
}