package study.erik.algorithm.leetcode.medium;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author erik.wang
 * @date 2020-03-08 17:52
 * @description
 * 该题有两个思路，一个动态规划思路，见solution2；一个是dfs思路，见solution1.
 * 当然两个思路都是基于递归来实现的。
 */
public class CombinationSum2 {

    private static final Logger logger = LoggerFactory.getLogger(CombinationSum2.class);

    /**
     * title=Combination Sum II
     * url=https://leetcode.com/problems/combination-sum-ii/
     * 分析：也是求组合；与1不同的是，每个元素只能用一次
     * 成绩：16%，7% 哎， 太差了
     * 解法分析：这和第一题比起来，根本没有任何新意。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return solution1(candidates, target);
    }

    public List<List<Integer>> solution1(int[] candidates, int target) {
        Set<List<Integer>> results = new HashSet<>();
        List<Integer> candidateList = IntStream.of(candidates).boxed().collect(Collectors.toList());
        Collections.sort(candidateList, Collections.reverseOrder());
        dfs(results, new LinkedList<>(), candidateList, target, 0);
        return new ArrayList<>(results);
    }

    /**
     * 这是改型了的dfs，不是严格的dfs，因为，哈哈，因为什么呀，这就是dfs。
     * @param results
     * @param stack
     * @param candidates
     * @param currentTarget
     * @param level
     */
    private void dfs(Set<List<Integer>> results, Deque<Integer> stack, List<Integer> candidates, int currentTarget, int level) {

        if (currentTarget == 0) {
            results.add(new ArrayList<>(stack));
            return;
        }
        //这种不想是求组合的写法
        for (int i = level; i < candidates.size(); i++) {
            int candidate = candidates.get(i);
            if (currentTarget >= candidate) {
                stack.push(candidate);
                dfs(results, stack, candidates, currentTarget - candidate, i + 1);
                stack.pop();
            }

        }
    }

    /**
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> solution2(int[] candidates, int target) {
        Set<List<Integer>> results = new HashSet<>();
        combine(results, new LinkedList<>(), candidates, 0, target);
        return new ArrayList<>(results);
    }


    public void combine(Set<List<Integer>> results, Deque<Integer> result, int[] candidates, int index, int target) {

        if (index == candidates.length) {
            if (target == 0) {
                List<Integer> resultCopy = new ArrayList<>(result);
                Collections.sort(resultCopy);
                results.add(resultCopy);
            }
            return;
        }
        int candidate = candidates[index];
        if (candidate <= target) {
            result.push(candidate);
            combine(results, result, candidates, index + 1, target - candidate);
            result.pop();
        }
        combine(results, result, candidates, index + 1, target);
    }

    @Test
    public void test_solution() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        logger.info("result={}", JSON.toJSONString(solution1(candidates, target)));
        logger.info("result={}", JSON.toJSONString(solution2(candidates, target)));
    }


}
