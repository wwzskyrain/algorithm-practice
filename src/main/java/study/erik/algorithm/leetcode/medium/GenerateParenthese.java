package study.erik.algorithm.leetcode.medium;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-03-09 09:08
 * @description
 */
public class GenerateParenthese<ddd> {
    private static final Logger logger = LoggerFactory.getLogger(GenerateParenthese.class);

    public List<String> generateParenthesis(int n) {
        return solution1(n);
    }

    private List<String> solution1(int n) {
        return new ArrayList<>(generate(n));
    }

    /**
     * 这个'递归的解法'
     *
     * @param n
     * @return
     */
    private Set<String> generate(int n) {

        if (n == 1) {
            Set<String> results = new HashSet<>();
            results.add("()");
            return results;
        }

        Set<String> results = generate(n - 1);
        Set<String> newResults = new HashSet<>();
        for (String result : results) {
            // 这里的情况不是完全的
            newResults.add("(" + result + ")");
            newResults.add("()" + result);
            newResults.add(result + "()");
        }
        return newResults;
    }


    /**
     * 分析：二叉树遍历
     * 成绩：100% 21%，震惊，时间成绩竟然是100%。
     * 而这个成绩，只是修改了一点东西。修改之前是24%，5%
     * 改了什么，改了stack，之前用的jdk的，现在自己用数组实现了一个栈。
     * @param n
     * @return
     */
    public List<String> solution2(int n) {
        List<String> results = new ArrayList<>();
        binaryTravel(results, new char[n * 2], 0, 0, 0, n);
        return results;
    }

    /**
     * 用数组实现栈的好处
     * 1. 在输出时，很方便
     * 2. 只用显示入站，不用显示出栈；
     * @param results
     * @param stack
     * @param stackSize
     * @param left
     * @param right
     * @param n
     */
    private void binaryTravel(List<String> results, char[] stack, int stackSize, int left, int right, int n) {

        if (stackSize == n * 2) {
            results.add(String.valueOf(stack));
            return;
        }

        if (left < n) {
            stack[stackSize] = '(';
            binaryTravel(results, stack, stackSize + 1, left + 1, right, n);
        }
        if (right < left) {
            stack[stackSize] = ')';
            binaryTravel(results, stack, stackSize + 1, left, right + 1, n);
        }
    }

    @Test
    public void test_solution() {
        logger.info("result={}", solution1(4));
        logger.info("result={}", solution2(4));

//        String result1 = "()()()(),(()())(),(()(())),()()(()),(((()))),(())()(),()((())),()(())(),()(()()),(()()()),((()())),((()))(),((())())";
//        String result2 = "(((()))),((()())),((())()),((()))(),(()(())),(()()()),(()())(),(())(()),(())()(),()((())),()(()()),()(())(),()()(()),()()()()";
//        Set<String> resultSet1 = new HashSet<>(Arrays.asList(result1.split(",")));
//        Set<String> resultSet2 = new HashSet<>(Arrays.asList(result2.split(",")));
//
//        for (String s : resultSet1) {
//            System.out.println(s + " is " + resultSet2.contains(s));
//        }
//
//        for (String s : resultSet2) {
//            System.out.println(s + " is " + resultSet1.contains(s));
//        }

    }

}
