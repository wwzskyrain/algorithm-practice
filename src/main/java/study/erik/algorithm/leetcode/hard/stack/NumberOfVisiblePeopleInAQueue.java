/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.stack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : NumberOfVisiblePeopleInAQueue.java, v 0.1 2023年07月27日 12:08 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfVisiblePeopleInAQueue {

    @LetCodeCommit(title = "1944. Number of Visible People in a Queue",
            selfRemark = "这个题短小精悍，用来做面试题很合适。难度也不小。"
                    + "难点在于用递减栈去思考。")
    public int[] canSeePersonsCount(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        int n = heights.length;
        int[] ans = new int[n];
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                //递减栈。这里的都能看到i。直到peek比i高（满足递减栈的定义）
                Integer pop = stack.pop();
                ans[pop]++;
            }
            if (!stack.isEmpty()) {
                //当前的peek也能看到i，虽然peek比i高一点。
                ans[stack.peek()]++;
            }
            stack.push(i);
        }
        return ans;
    }

    @Parameter
    public int[] heights;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[10,6,8,5,11,9]")},
                {ArrayUtils.buildArray("[5,1,2,3,10]")}
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(canSeePersonsCount(heights)));
    }
}