/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.geometry;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : LargestPerimeterTriangle.java, v 0.1 2022年11月25日 22:15 yueyi Exp $
 */
public class LargestPerimeterTriangle {

    @LetCodeCommit(title = "976. Largest Perimeter Triangle")
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 3; i >= 0; i--) {
            if (nums[i] + nums[i + 1] > nums[i + 2]) {
                return nums[i] + nums[i + 1] + nums[i + 2];
            }
        }
        return 0;
    }

}