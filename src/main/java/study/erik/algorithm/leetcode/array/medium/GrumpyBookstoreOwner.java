/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : GrumpyBookstoreOwner.java, v 0.1 2022年12月18日 15:42 yueyi Exp $
 */
public class GrumpyBookstoreOwner {

    @LetCodeCommit(title = "1052. Grumpy Bookstore Owner",
            diff = "m",
            selfRemark = "也好像做过——暴躁的书店老板")
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int[] ones = new int[n];
        int sum = 0;
        for (int i = 0; i < customers.length; i++) {
            ones[i] += (i > 0 ? ones[i - 1] : 0);
            if (grumpy[i] == 0) {
                sum += customers[i];
            } else {
                ones[i] += customers[i];
            }
        }
        int max = sum;
        for (int i = minutes - 1; i < n; i++) {
            max = Math.max(max, sum + ones[i] - (i == minutes - 1 ? 0 : ones[i - minutes]));
        }
        return max;
    }

}