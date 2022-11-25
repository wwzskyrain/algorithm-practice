/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : SquaresOfASortedArray.java, v 0.1 2022年11月25日 22:20 yueyi Exp $
 */
public class SquaresOfASortedArray {

    @LetCodeCommit(title = "977. Squares of a Sorted Array")
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int l = 0, r = res.length - 1;
        int i = r;
        while (l <= r) {
            if (nums[l] * nums[l] < nums[r] * nums[r]) {
                res[i] = nums[r] * nums[r];
                r--;
            } else {
                res[i] = nums[l] * nums[l];
                l++;
            }
            i--;
        }
        return res;
    }
}