/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumArray.java, v 0.1 2021年08月17日 8:04 上午 yueyi Exp $
 */
public class NumArray {

    private long[] preSum;

    @LetCodeCommit(title = "Range Sum Query - Immutable",
            selfRemark = "一把梭了")
    public NumArray(int[] nums) {
        preSum = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            preSum[i] = (i > 0 ? preSum[i - 1] : 0) + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return ((int) (preSum[right] - (left > 0 ? preSum[left - 1] : 0)));
    }

}