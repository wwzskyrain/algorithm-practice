/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : PartitionArrayIntoThreePartsWithEqualSum.java, v 0.1 2022年12月04日 20:17 yueyi Exp $
 */
public class PartitionArrayIntoThreePartsWithEqualSum {

    @LetCodeCommit(title = "1013. Partition Array Into Three Parts With Equal Sum")
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int i = 0;
        int curSum = 0;
        int c = 0;
        while (i < arr.length) {
            curSum += arr[i++];
            if (curSum == sum / 3) {
                c++;
                curSum = 0;
            }
        }
        return c >= 3;
    }
}