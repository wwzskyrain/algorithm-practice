package study.erik.algorithm.leetcode.series.calcultor;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/9/14 ，时间：09:24
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Basic_Calculator_2 {

    @LetCodeCommit(title = "224. Basic Calculator",
            selfRemark = "基本思路：同样用两个栈来做计算，分别是数据栈和操作符栈。" +
                    "注意1：本次支持+-*/^%，还都是两元操作符号，但是支持优先级" +
                    "注意2：这里支持负数，解决技巧有两处分别解决。一是表达式的开头，二是左元括弧的开头。" +
                    "注意3：这里不支持正数，假设支持的话，也是同支持负数的技巧解决。")
    public int calculate(String s) {

        Map<Character, Integer> map = new HashMap<>();
        map.put('-', 1);
        map.put('+', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('%', 2);
        map.put('^', 3);

        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);

        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        // 存放所有的操作，包括 +/-
        Deque<Character> ops = new ArrayDeque<>();

        int n = s.length();
        char[] cs = s.toCharArray();

        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    char op = ops.peekLast();
                    if (op != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNum(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < n && isNum(cs[j])) u = u * 10 + (int) (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                } else { //处理符号
                    //处理正数和负数
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        //放心，如果是"(-2)"这种负数，那么处理c='-'时，操作符号栈的last就是'('，就不会进入循环了。
                        char prev = ops.peekLast();
                        //如果当前操作符优先级不够大，就不计算哈。
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        //最后还是要计算一次，表达式结束，表示最小优先级
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peekLast();

    }

    /**
     * 这里只实现了'加减乘除'、%/^共6种计算。
     *
     * @param nums
     * @param ops
     */
    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') {
            ans = a + b;
        } else if (op == '-') {
            ans = a - b;
        } else if (op == '*') {
            ans = a * b;
        } else if (op == '/') {
            ans = a / b;
        } else if (op == '^') {
            ans = (int) Math.pow(a, b);
        } else if (op == '%') {
            ans = a % b;
        }
        nums.addLast(ans);
    }

    boolean isNum(char c) {
        return Character.isDigit(c);
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, "1 + 1"},
                {3, " 2-1 + 2 "},
                {23, "(1+(4+5+2)-3)+(6+8)"},
                {3, "1-(     -2)"}, // "1-(-2)","1-(0-2)"
                {-1, "-2+ 1"} //负数
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String s;

    @Test
    public void test() {
        Assert.assertEquals(expect, calculate(s));
    }

}
