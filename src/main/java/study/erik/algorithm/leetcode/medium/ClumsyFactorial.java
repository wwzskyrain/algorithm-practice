/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

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
 * @author yueyi
 * @version : ClumsyFactorial.java, v 0.1 2022年12月04日 12:49 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ClumsyFactorial {

    @LetCodeCommit(title = "1006. Clumsy Factorial",
            diff = "m",
            selfRemark = "真有意思"
                    + "1.首先这是一个递归题，直接递归解法"
                    + "2.其次这还可以是一个找规律的题目")
    public int clumsy(int N) {
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        }
        if (N == 3) {
            return 6;
        }
        if (N == 4) {
            return 7;
        }
        if (N % 4 == 1) {
            return N + 2;
        }
        if (N % 4 == 2) {
            return N + 2;
        }
        if (N % 4 == 3) {
            return N - 1;
        }
        return N + 1;
    }

    public int clumsyWithStack(int N) {
        Stack<Character> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        char[] op = new char[]{'*', '/', '+', '-'};
        int opIdx = 0;
        while (N > 0) {
            s2.push(N);
            N--;
            char c = op[opIdx];
            opIdx = (opIdx + 1) % 4;
            switch (c) {
                case '*':
                    break;
                case '/':
                case '-':
                    cal(s1, s2);
                    break;
                case '+':
                    cal(s1, s2);
                    cal(s1, s2);
                default:
            }
            s1.push(c);
        }
        s1.pop();
        cal(s1, s2);
        return s2.pop();
    }

    public void cal(Stack<Character> s1, Stack<Integer> s2) {
        if (s2.size() < 2) {
            return;
        }
        int n2 = s2.pop();
        int n1 = s2.pop();
        char c = s1.pop();
        switch (c) {
            case '*':
                s2.push(n1 * n2);
                break;
            case '/':
                s2.push(n1 / n2);
                break;
            case '+':
                s2.push(n1 + n2);
                break;
            case '-':
                s2.push(n1 - n2);
                break;
            default:
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
//                {7, 4},
{12, 10},
{7, 4},
});
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int N;

    @Test
    public void test() {
        Assert.assertEquals(expect, clumsyWithStack(N));
    }


}