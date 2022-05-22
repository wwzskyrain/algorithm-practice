/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : PeakIndexInAMountainArray.java, v 0.1 2022年05月22日 12:11 yueyi Exp $
 */
public class PeakIndexInAMountainArray {

    @LetCodeCommit(title = "852. Peak Index in a Mountain Array",
            diff = "m",
            selfRemark = "easy题，O(lgn)解法：二叉搜索，很熟练了.")
    public int peakIndexInMountainArray(int[] arr) {

        int l = 0, h = arr.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (arr[m - 1] < arr[m] && arr[m] > arr[m + 1]) {
                return m;
            }
            if (arr[m - 1] < arr[m]) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return -1;
    }

}