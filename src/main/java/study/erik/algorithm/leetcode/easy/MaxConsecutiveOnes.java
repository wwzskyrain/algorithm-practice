/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaxConsecutiveOnes.java, v 0.1 2021年02月15日 8:05 上午 yueyi Exp $
 */

public class MaxConsecutiveOnes {

    @LetCodeCommit(title = "Max Consecutive Ones", no = 485)
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLengthOnes = 0;
        int lengthOnes = 0;
        for (int num : nums) {
            if (num == 1) {
                lengthOnes++;
                maxLengthOnes = Math.max(maxLengthOnes, lengthOnes);
            } else {
                lengthOnes = 0;
            }
        }
        return maxLengthOnes;
    }

    @Test
    public void test_findMaxConsecutiveOnes() {
        int[] nums = new int[] {1, 1, 0, 1, 1, 1};
        int result = 3;
        Assert.assertEquals(result, findMaxConsecutiveOnes(nums));
    }
}