package study.erik.algorithm.job.duoduo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/10 09:02
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public int longestValidParentheses(String s) {
        int l = s.length();
        int[] stack = new int[l];
        int idx = 0;
        int max = 0;
        for (int i = 0; i < l; i++) {
            if (idx == 0) {
                stack[idx++] = i;
                continue;
            }
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    stack[idx++] = i;
                    break;
                case ')':
                    if (s.charAt(stack[idx - 1]) == ')') {
                        stack[idx++] = i;
                    } else {
                        idx--;
                        int last = (idx == 0 ? -1 : stack[idx - 1]);
                        int newLength = i - last;
                        max = Math.max(max, newLength);
                    }
                default:
            }
        }
        return max;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
//                {2, "(()"},
                {4, ")()())"},
                {0, ""},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String s;

    @Test
    public void test() {
        Assert.assertEquals(expect, longestValidParentheses(s));
    }

}
