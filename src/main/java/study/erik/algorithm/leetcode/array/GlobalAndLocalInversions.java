/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : GlobalAndLocalInversions.java, v 0.1 2022年05月04日 17:59 yueyi Exp $
 */
public class GlobalAndLocalInversions {

    @LetCodeCommit(title = "775. Global and Local Inversions",
            selfRemark = "没必要求global inversion，当然，也没必要求local inversion."
                    + "只需要观察他们两个相等时的特征. 特征很明显：局部逆序，而且局部就是2个相邻元素"
                    + "0,2,1,4,3,5,6"
                    + "1,0,2,3,5,4,6")
    public boolean isIdealPermutation(int[] nums) {
        int curMax = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            curMax = Math.max(curMax, nums[i]);
            if (curMax > nums[i + 2]) {
                return false;
            }
        }
        return true;
    }
}