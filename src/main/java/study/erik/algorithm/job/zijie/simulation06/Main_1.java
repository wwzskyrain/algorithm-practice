package study.erik.algorithm.job.zijie.simulation06;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/8 16:58
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_1 {

    @LetCodeCommit(title = "")
    public String reverseParentheses(String s) {
        char[] arr = s.toCharArray();
        int l = s.length();
        int[] stack = new int[l / 2 + 1];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == '(') {
                stack[idx++] = i;
            } else if (c == ')') {
                int peek = stack[--idx];
                reverse(arr, peek + 1, i - 1);
            }
        }
        int i = 0;
        int j = 0;
        //早用栈就没事儿了。
        char[] ret = new char[arr.length];
        while(j < arr.length){
            char c = arr[j++];
            if(c != '(' && c != ')'){
                ret[i++] = c;
            }
        }
        return String.copyValueOf(ret, 0, i);
    }

    public void reverse(char[] arr, int ii, int jj) {
        while (ii < jj) {
            char c = arr[ii];
            arr[ii] = arr[jj];
            arr[jj] = c;
            ii++;
            jj--;
        }
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {"iloveu", "(u(love)i)"},

        });
    }

    @Parameterized.Parameter
    public String expect;
    @Parameterized.Parameter(1)
    public String s;

    @Test
    public void test() {
        Assert.assertEquals(expect, reverseParentheses(s));
    }

}
