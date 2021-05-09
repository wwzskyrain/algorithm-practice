/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yueyi
 * @version : NextGreaterElementII.java, v 0.1 2021年03月06日 7:02 下午 yueyi Exp $
 */
public class NextGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {
        if(nums.length==1){
            return new int[]{-1};
        }
        // 单调递增栈
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int nextIndex = (i+1)%nums.length;
            //result[i] = nums[nextIndex] >
        }
        return result;
    }

    @Test
    public void test_() {
        int[] nums = {1, 2, 1};
        int[] except = {2, -1, 2};
        Assert.assertEquals(nextGreaterElements(nums), except);
    }

}