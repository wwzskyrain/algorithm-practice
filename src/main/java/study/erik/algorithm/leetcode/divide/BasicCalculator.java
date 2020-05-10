package study.erik.algorithm.leetcode.divide;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-08 14:27
 */
public class BasicCalculator {

    @Test
    public void test_() {
        Assert.assertEquals(2, calculate("1 + 1"));
        Assert.assertEquals(3, calculate(" 2-1 + 2 "));
        Assert.assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    /**
     * title = Basic Calculator
     * 描述：看test，计算二则运算，带圆括号
     * 成绩：29 和 44
     * 扣弄了一个下午，终于通过了
     * 解法很不高明，就是硬写；没啥章法；
     * 不过我们还是有几点原则的
     * 1.对s的遍历，不要回退
     * 2.坚持用两个栈，数据栈和操作符栈
     * 3.用操作符来定界数字，然后见到数字就计算，除非不适合计算，比如操作数不够两个、操作符是(。
     *
     * 改进：这里用字符来定界数字了，其实数字可以自我定界的，用十进制累积法
     */
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        char[] opStack = new char[s.length()];
        int[] dataStack = new int[s.length()];
        int opSize = 0, dataSize = 0;
        //自定义一个栈，使我们非常熟悉的操作了
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i++);
            switch (c) {
                case '+':
                case '-':
                    if (sb.length() > 0) {
                        dataStack[dataSize++] = Integer.parseInt(sb.toString());
                        if (opSize > 0 && opStack[opSize - 1] != '(') {
                            int result = calculate(dataStack[--dataSize], dataStack[--dataSize], opStack[--opSize]);
                            dataStack[dataSize++] = result;
                        }
                        sb = new StringBuilder();
                    }
                    opStack[opSize++] = c;
                    break;
                case ')':
                    if (sb.length() > 0) {
                        dataStack[dataSize++] = Integer.parseInt(sb.toString());
                        if (opSize > 0 && opStack[opSize - 1] != '(') {
                            int result = calculate(dataStack[--dataSize], dataStack[--dataSize], opStack[--opSize]);
                            dataStack[dataSize++] = result;
                        }
                        sb = new StringBuilder();
                    }
                    opSize--;
                    if (opSize > 0 && opStack[opSize - 1] != '(') {
                        int result = calculate(dataStack[--dataSize], dataStack[--dataSize], opStack[--opSize]);
                        dataStack[dataSize++] = result;
                    }
                    break;
                case '(':
                    opStack[opSize++] = c;
                    break;
                default:
                    sb.append(c);
            }
        }
        if (sb.length() > 0) {
            dataStack[dataSize++] = Integer.parseInt(sb.toString());
            if (dataSize > 1) {
                int result = calculate(dataStack[--dataSize], dataStack[--dataSize], opStack[--opSize]);
                dataStack[dataSize++] = result;
            }
        }
        return dataStack[dataSize - 1];
    }


    private int calculate(int n1, int n2, char operation) {
        switch (operation) {
            case '+':
                return n1 + n2;
            case '-':
                return n2 - n1;
            default:
                throw new IllegalArgumentException();
        }
    }
}
