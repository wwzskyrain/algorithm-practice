/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.stack.medium;

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
 * @version : ValidateStackSequences.java, v 0.1 2022年11月18日 15:01 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ValidateStackSequences {

    @LetCodeCommit(title = "946. Validate Stack Sequences",
            diff = "m",
            selfRemark = "很简单啦，按照题意写出来就行了")
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int iP = 0;
        for (int i : pushed) {
            stack.push(i);
            while (!stack.isEmpty() && iP < popped.length && stack.peek() == popped[iP]) {
                stack.pop();
                iP++;
            }
        }
        return iP == popped.length;
    }

    @Parameter
    public int[]   pushed;
    @Parameter(1)
    public int[]   popped;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3,4,5]"), ArrayUtils.buildArray("[4,5,3,2,1]"), true},
                {ArrayUtils.buildArray("[1,2,3,4,5]"), ArrayUtils.buildArray("[4,3,5,1,2]"), false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, validateStackSequences(pushed, popped));
    }

}