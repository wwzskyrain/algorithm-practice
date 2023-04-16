/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : SumOfMutatedArrayClosestToTarget.java, v 0.1 2023年04月13日 07:42 yueyi Exp $
 */
public class SumOfMutatedArrayClosestToTarget {

    @LetCodeCommit(title = "1300. Sum of Mutated Array Closest to Target")
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int i = 0;
        while (i < n && arr[i] * (n - i) < target) {
            target -= arr[i++];
        }
        if (i == n) {
            return arr[i - 1];
        }
        int res = target / (n - i);
        if (target - res * (n - i) > (res + 1) * (n - i) - target) {
            res++;
        }
        return res;
    }

}