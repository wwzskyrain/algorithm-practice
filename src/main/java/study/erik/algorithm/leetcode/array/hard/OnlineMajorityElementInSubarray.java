/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : OnlineMajorityElementInSubarray.java, v 0.1 2022年12月31日 13:10 yueyi Exp $
 */
public class OnlineMajorityElementInSubarray {

    @LetCodeCommit(title = "1157. Online Majority Element In Subarray")
    public static class MajorityChecker {
        int[] arr;

        public MajorityChecker(int[] arr) {
            this.arr = arr;
        }

        public int query(int left, int right, int threshold) {
            int count = 0;
            int vote = -1;
            for (int i = left; i <= right; i++) {
                if (count == 0) {
                    vote = this.arr[i];
                    count++;
                } else {
                    if (this.arr[i] != vote) {
                        count--;
                    } else {
                        count++;
                    }
                }
            }
            count = 0;
            for (int i = left; i <= right; i++) {
                if (this.arr[i] == vote) {
                    count++;
                }
            }
            return count >= threshold ? vote : -1;
        }
    }

}