/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.stack;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Stack;

/**
 * @author yueyi
 * @version : BasicCalculatorII.java, v 0.1 2021年12月24日 2:52 上午 yueyi Exp $
 */
public class BasicCalculatorII {

    @LetCodeCommit(title = "227. Basic Calculator II",
            related = "https://leetcode.com/problems/basic-calculator-iii/")
    public int calculate(String s) {

        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
                continue;
            }
            if ((!Character.isDigit(c) || c != ' ') || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                    default:
                        break;
                }
                sign = c;
                num = 0;
            }
        }
        int ret = 0;
        for (int i : stack) {
            ret += i;
        }
        return ret;
    }

}