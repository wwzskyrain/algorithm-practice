package study.erik.algorithm.leetcode.divide;

import org.junit.Test;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-05-06 09:38
 */
public class DifferentWaystoAddParentheses {

    @Test
    public void test_solution() {

        System.out.println(diffWaysToCompute("2-1-1"));
        System.out.println(diffWaysToCompute("2*3-4*5"));

    }

    /**
     * Different Ways to Add Parentheses
     *
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        return calculateWithRecursive(input, new HashMap<>());
    }

    /**
     * 成绩：不说了，这是别人的代码；这么优美的代码我们写得出来吗？
     * 这个问题本身就是一个可以'分治'的问题
     *
     * @param input
     * @param mem
     * @return
     */
    public List<Integer> calculateWithRecursive(String input, Map<String, List<Integer>> mem) {
        List<Integer> results;
        if ((results = mem.get(input)) != null) {
            return results;
        }
        results = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> results1 = calculateWithRecursive(part1, mem);
                List<Integer> results2 = calculateWithRecursive(part2, mem);
                for (Integer i1 : results1) {
                    for (Integer i2 : results2) {
                        switch (c) {
                            case '+':
                                results.add(i1 + i2);
                                break;
                            case '-':
                                results.add(i1 - i2);
                                break;
                            case '*':
                                results.add(i1 * i2);
                                break;
                            default:

                        }
                    }
                }
            }
        }
        // 处理一个数据的情况
        if (results.size() == 0) {
            results.add(Integer.parseInt(input));
        }
        //备忘录嘛
        mem.put(input, results);
        return results;
    }

}
