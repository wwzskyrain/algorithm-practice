/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ArithmeticSlices.java, v 0.1 2021年11月23日 8:24 上午 yueyi Exp $
 */
public class ArithmeticSlices {

    @LetCodeCommit(title = "Arithmetic Slices")
    public int numberOfArithmeticSlices(int[] nums) {
        int c = 0;
        int l = nums.length;
        if (l < 3) {
            return c;
        }
        //f表示当前元素结尾的arithmetic数组的数量
        int f = 0;
        for (int i = 2; i < l; i++) {
            if (2 * nums[i - 1] == (nums[i] + nums[i - 2])) {
                f = f + 1;
            } else {
                f = 0;
            }
            c += f;
        }
        return c;
    }

}