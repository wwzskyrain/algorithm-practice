package study.erik.algorithm.leetcode.series.calcultor;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

/**
 * 日期：2023/9/14 ，时间：10:21
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class BasicCalculatorII {

    @LetCodeCommit(title = "227. Basic Calculator II",
    selfRemark = "这个题目是m，因为只有加减乘除没有元括弧。" +
            "这里就简单了，直接用一个栈就行了，因为数字之前的符号如果是乘除，就计算啦。")
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
            //c是操作符
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
