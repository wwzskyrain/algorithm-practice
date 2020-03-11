package study.erik.algorithm.leetcode.medium;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-03-08 21:50
 * @description
 */
public class CombinationSum3 {
    private static final Logger logger = LoggerFactory.getLogger(CombinationSum3.class);


    /**
     * title = Combination Sum III
     * url = https://leetcode.com/problems/combination-sum-iii/
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        return solution1(k, n);

    }

    /**
     * 这个题目，很简单，我更想用'dp'思想来解答。不想用dfs。
     * 成绩：24% 6% ，成绩不好
     * 后续：
     *  1. easy： https://leetcode.com/problems/rank-transform-of-an-array/
     *  2. medium： https://leetcode.com/problems/count-square-submatrices-with-all-ones/
     *  3. easy： https://leetcode.com/problems/find-numbers-with-even-number-of-digits/
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> solution1(int k, int n) {
        Set<List<Integer>> results = new HashSet<>();
        combine(results, new LinkedList<>(), k, n, 1);
        return new ArrayList<>(results);
    }

    public void combine(Set<List<Integer>> results, Deque<Integer> combination, int space, int target, int i) {

//        base
        if (space == 0 && target == 0) {
            List<Integer> result = new ArrayList<>(combination);
            Collections.sort(result);
            results.add(result);
            return;
        }

        if (target <= 0 || space <= 0 || i > 9) {
            return;
        }

        combine(results, combination, space, target, i + 1);

        combination.push(i);
        combine(results, combination, space - 1, target - i, i + 1);
        combination.pop();

    }

    @Test
    public void test_solution() {
        logger.info("result = {}", solution1(3, 9));
        logger.info("result = {}", solution1(3, 7));
    }

}
