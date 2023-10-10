package study.erik.algorithm.leetcode.series.calcultor;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;

/**
 * 日期：2023/9/14 ，时间：09:24
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Basic_Calculator {

    @LetCodeCommit(title = "224. Basic Calculator",
    selfRemark = "基本思路：用两个栈来做计算，分别是数据栈和操作符栈。" +
            "注意1：这里都是两元操作符号。" +
            "注意2：这里支持负数，解决技巧有两处分别解决。一是表达式的开头，二是左元括弧的开头。" +
            "注意3：这里不支持正数，假设支持的话，也是同支持负数的技巧解决。")
    public int calculate(String s) {
        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);

        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        s = s.replaceAll("\\(-", "\\(0-");
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
                } else {
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peekLast();
    }

    /**
     * 这里只实现了+-计算。
     * @param nums
     * @param ops
     */
    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        nums.addLast(op == '+' ? a + b : a - b);
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
