/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : EvaluateReversePolishNotation.java, v 0.1 2021年12月10日 12:02 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class EvaluateReversePolishNotation {

    @LetCodeCommit(title = "150. Evaluate Reverse Polish Notation",
            selfRemark = "测试格式化")
    public int evalRPN(String[] tokens) {

        Deque<Integer> stack = new LinkedList<>();
        int curResult;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    curResult = stack.pop() + stack.pop();
                    stack.push(curResult);
                    break;
                case "-":
                    curResult = -stack.pop() + stack.pop();
                    stack.push(curResult);
                    break;
                case "*":
                    curResult = stack.pop() * stack.pop();
                    stack.push(curResult);
                    break;
                case "/":
                    Integer d = stack.pop();
                    Integer dd = stack.pop();
                    curResult = dd / d;
                    stack.push(curResult);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    @Parameter
    public String[] tokens;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {new String[]{"2", "1", "+", "3", "*"}, 9},
                {new String[]{"4", "13", "5", "/", "+"}, 6},
                {new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, 22},
                };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, evalRPN(tokens));
    }
}