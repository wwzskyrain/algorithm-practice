/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author yueyi
 * @version : RemoveInvalidParentheses.java, v 0.1 2022年01月06日 8:31 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RemoveInvalidParentheses {

    @LetCodeCommit(title = "301. Remove Invalid Parentheses",
            time = 8,
            selfRemark = "虽然成绩不好，但是这个回溯法用的很标准。"
                    + "也看了评论区的好算法，但是太巧妙，其真确性不容易证明。")
    public List<String> removeInvalidParentheses(String s) {

        Set<String> results = new HashSet<>();
        help(s, 0, results, "", new LinkedList<>());
        if (results.size() == 0) {
            results.add("");
        }
        return new ArrayList<>(results);
    }

    public void help(String s, int curIndex, Set<String> results, String curResult, Deque<Character> stack) {
        if (curIndex == s.length()) {
            if (!stack.isEmpty()) {
                return;
            }
            if (results.size() == 0) {
                results.add(curResult);
            } else {
                String result1 = results.iterator().next();
                if (result1.length() < curResult.length()) {
                    results.clear();
                    results.add(curResult);
                } else if (result1.length() == curResult.length()) {
                    results.add(curResult);
                }
            }
            return;
        }
        char charAt = s.charAt(curIndex);

        if (charAt == '(') {
            stack.push(charAt);
            help(s, curIndex + 1, results, curResult + charAt, stack);
            stack.pop();
            help(s, curIndex + 1, results, curResult, stack);
        } else if (charAt == ')') {
            if (!stack.isEmpty() && stack.peek().equals('(')) {
                // 不用
                help(s, curIndex + 1, results, curResult, stack);
                // 使用该')':消除一个匹配的'('; 递归调用时加上当前字符
                Character pop = stack.pop();
                help(s, curIndex + 1, results, curResult + charAt, stack);
                stack.push(pop);
            } else {
                help(s, curIndex + 1, results, curResult, stack);
            }
        } else {
            help(s, curIndex + 1, results, curResult + charAt, stack);
        }
    }

    @Parameter
    public String       s;
    @Parameter(1)
    public List<String> expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {")(f", Arrays.asList("f")},
                {"(()", Arrays.asList("()")},
                {"()())", Arrays.asList("(())", "()()")},
                {"(a)())()", Arrays.asList("(a())()", "(a)()()")},
                {")(", Arrays.asList("")},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, removeInvalidParentheses(s));
    }

}