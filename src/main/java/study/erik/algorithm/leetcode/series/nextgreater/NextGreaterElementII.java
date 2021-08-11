/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.nextgreater;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NextGreaterElementII.java, v 0.1 2021年08月10日 3:29 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NextGreaterElementII {

    @LetCodeCommit(title = "Next Greater Element II")
    public int[] nextGreaterElements(int[] nums) {
        //return resolveDirectly(nums);
        return resolveWithStack(nums);
    }

    public int[] resolveWithStack(int[] nums) {
        if (nums.length == 1) {
            return new int[] {-1};
        }
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        //从第一个最大值的第一个右边值开始，这样保证最大值可以最后入栈，
        //从而导致踢出那些非最大值的元素
        int i = (maxIndex + 1) % nums.length;
        // 严格单调递减栈
        int[] stack = new int[nums.length];
        int top = 0;
        int[] result = new int[nums.length];
        while (i <= maxIndex + nums.length) {
            //循环访问
            int availableIndex = i % nums.length;
            while (top > 0 && nums[availableIndex] > nums[stack[top - 1]]) {
                result[stack[top - 1]] = nums[availableIndex];
                top--;
            }
            stack[top++] = availableIndex;
            i++;
        }
        //站内存留的都是最大值的index，直接赋值为-1
        while (top > 0) {
            result[stack[--top]] = -1;
        }
        return result;
    }

    public int[] resolveDirectly(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        int[] greater = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                greater[i] = -1;
                continue;
            }
            int index = i + 1;
            while (nums[(index) % nums.length] <= nums[i]) {
                index++;
            }
            greater[i] = nums[(index) % nums.length];
        }
        return greater;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,1,1,1,1]"), ArrayUtils.buildArray("[-1,-1,-1,-1,-1]")},
                {ArrayUtils.buildArray("[1]"), ArrayUtils.buildArray("[-1]")},
                {ArrayUtils.buildArray("[3,5,4,2]"), ArrayUtils.buildArray("[5,-1,5,3]")},
                {ArrayUtils.buildArray("[2,5,4,3]"), ArrayUtils.buildArray("[5,-1,5,5]")},
                {ArrayUtils.buildArray("[1,2,1]"), ArrayUtils.buildArray("[2,-1,2]")},
                {ArrayUtils.buildArray("[1,2,3,4,3]"), ArrayUtils.buildArray("[2,3,4,-1,4]")}
        };
    }

    @Test
    public void test_nums1() {
        Assert.assertArrayEquals(expect, nextGreaterElements(nums));
    }

}