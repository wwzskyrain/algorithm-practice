/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.parentheses;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : MinimumRemoveToMakeValidParentheses.java, v 0.1 2023年03月16日 09:06 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumRemoveToMakeValidParentheses {

    @LetCodeCommit(title = "1249. Minimum Remove to Make Valid Parentheses")
    public String minRemoveToMakeValid(String s) {
        List<Integer> stack = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.add(i);
            } else if (c == ')') {
                int stackSize = stack.size();
                if (stackSize > 0 && s.charAt(stack.get(stackSize - 1)) == '(') {
                    stack.remove(stackSize - 1);
                } else {
                    stack.add(i);
                }
            }
        }
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < n) {
            if (j < stack.size() && i == stack.get(j)) {
                j++;
                i++;
                continue;
            }
            sb.append(s.charAt(i++));
        }
        return sb.toString();
    }

    @Parameter
    public String s;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"lee(t(c)o)de)", "lee(t(c)o)de"},
                {"a)b(c)d", "ab(c)d"},
                {"))((", ""},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minRemoveToMakeValid(s));
    }

}