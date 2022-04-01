/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : ValidTriangleNumber.java, v 0.1 2022年04月01日 9:29 下午 yueyi Exp $
 */
public class ValidTriangleNumber {

    @LetCodeCommit(title = "611. Valid Triangle Number",
            diff = "d",
            selfRemark = "三角形的三个边：最大边小于另外两个边的和。"
                    + "k是最大边，i、j分别是最小边和次小边")
    public int triangleNumber(int[] nums) {

        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int k = nums.length - 1; k > 1; k--) {
            int i = 0, j = k - 1;
            while (i < j) {
                if (nums[i] + nums[j] <= nums[k]) {
                    i++;
                } else {
                    // i, i+1, i+2, ... , j-1 ，这（j-i）个都能和j、k组成三角形。
                    count += (j - i);
                    j--;
                }
            }
        }
        return count;
    }

}