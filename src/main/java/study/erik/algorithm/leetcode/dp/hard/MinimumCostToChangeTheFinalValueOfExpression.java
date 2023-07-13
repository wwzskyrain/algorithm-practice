/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yueyi
 * @version : MinimumCostToChangeTheFinalValueOfExpression.java, v 0.1 2023年07月09日 12:58 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumCostToChangeTheFinalValueOfExpression {

    @LetCodeCommit(title = "1896. Minimum Cost to Change the Final Value of Expression",
            diff = "h",
            selfRemark = "")
    public int minOperationsToFlip(String expression) {
        int len = expression.length();
        Deque<int[]> st_num = new ArrayDeque<>();
        Deque<Character> st_opt = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char c = expression.charAt(i);
            if (c == '(' || c == '&' || c == '|') {
                st_opt.push(c);
                continue;
            } else if (c == '0') {
                st_num.push(new int[] {0, 1});
            } else if (c == '1') {
                st_num.push(new int[] {1, 0});
            } else {
                st_opt.pop();
            }
            calc(st_num, st_opt);
        }
        return Math.max(st_num.peek()[0], st_num.peek()[1]);
    }

    private void calc(Deque<int[]> st1, Deque<Character> st2) {
        if (st1.size() >= 2 && (st2.peek() == '|' || st2.peek() == '&')) {
            int[] top1 = st1.pop();
            int[] top2 = st1.pop();
            int[] result_add = opt_and(top1, top2);
            int[] result_or = opt_or(top1, top2);
            if (st2.peek() == '&') {
                st1.push(new int[] {Math.min(result_add[0], result_or[1] + 1), Math.min(result_add[1], result_or[1] + 1)});
            } else {
                st1.push(new int[] {Math.min(result_add[0] + 1, result_or[0]), Math.min(result_add[1] + 1, result_or[1])});
            }
            st2.pop();
        }
    }

    private int[] opt_or(int[] a, int[] b) {
        int[] res = new int[2];
        res[0] = a[0] + b[0];
        res[1] = Math.min(a[0] + b[1], Math.min(a[1] + b[0], a[1] + b[1]));
        return res;
    }

    private int[] opt_and(int[] a, int[] b) {
        int[] res = new int[2];
        res[0] = Math.min(a[0] + b[0], Math.min(a[0] + b[1], a[1] + b[0]));
        res[1] = a[1] + b[1];
        return res;
    }

    @Parameter
    public String expression;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"1&(0|1)", 1},
                {"(0&0)&(0&0&0)", 3},
                {"(0|(1|0&1))", 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minOperationsToFlip(expression));
    }

}