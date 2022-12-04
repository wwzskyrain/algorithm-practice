/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : MaximizeSumOfArrayAfterKNegations.java, v 0.1 2022年12月04日 12:20 yueyi Exp $
 */
public class MaximizeSumOfArrayAfterKNegations {

    @LetCodeCommit(title = "1005. Maximize Sum Of Array After K Negations",
            diff = "e",
            selfRemark = "easy题目，题目貌似复杂，但是顺着题意构造解就行")
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        for (; i < nums.length; i++) {
            if (k <= 0) {
                break;
            }
            int num = nums[i];
            if (num >= 0) {
                break;
            }
            nums[i] = -num;
            k--;
        }
        if (k % 2 == 1) {
            Arrays.sort(nums);
            nums[0] = -nums[0];
        }
        return Arrays.stream(nums).sum();
    }

}