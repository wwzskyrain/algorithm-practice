/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Stack;

/**
 * @author yueyi
 * @version : Pattern132.java, v 0.1 2022年02月27日 9:44 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class Pattern132 {

    @LetCodeCommit(title = "456. 132 Pattern",
            selfRemark = "单点栈，是个好题目",
            tag = "单调栈")
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int second = 0x8fffffff;
        for (int i = nums.length - 1; i >= 0; i--) {
            int n = nums[i];
            if (n < second) {
                // 3.比较1是否小于2
                return true;
            }
            while (!stack.isEmpty() && n > stack.peek()) {
                // 1.找到一个2--小于n中最大的那个元素
                second = stack.pop();
            }
            //2.把3压进栈
            stack.push(n);
        }
        return false;
    }

    @Parameter
    public int[]   nums;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("3,6,1,2,3,4"), true},
                {ArrayUtils.buildArray("[1,0,1,-4,-3]"), false},
                {ArrayUtils.buildArray("[1,2,3,4]"), false},
                {ArrayUtils.buildArray("[3,1,4,2]"), true},
                {ArrayUtils.buildArray("[-1,3,2,0]"), true},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, find132pattern(nums));
    }

}