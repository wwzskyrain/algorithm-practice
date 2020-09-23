package study.erik.algorithm.leetcode.recursive;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;


import java.util.*;

/**
 * @author erik.wang
 * @date 2020-09-09 08:27
 */
public class CombinationSum {


    @LetCodeCommit(title = "39. Combination Sum", diff = "m",
            selfRemark = "这是一个无穷背包问题，当然我这里用了dfs的解法")
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> allResults = new HashSet<>();
        dfs(candidates, target, new LinkedList<>(), allResults);
        return new ArrayList<>(allResults);
    }

    public void dfs(int[] candidates, int target, LinkedList<Integer> curAddends, Set<List<Integer>> allResult) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            ArrayList<Integer> addends = new ArrayList<>(curAddends);
            addends.sort(Comparator.comparing(Integer::intValue));
            allResult.add(addends);
            return;
        }
        for (int candidate : candidates) {
            curAddends.addLast(candidate);
            dfs(candidates, target - candidate, curAddends, allResult);
            curAddends.removeLast();
        }
    }

    @Test
    public void test_solution() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> except = Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(7));
        Assert.assertEquals(except, combinationSum(candidates, target));
    }

    @Test
    public void test_solution_1() {
        int[] candidates = {2, 3, 5};
        int target = 8;
        List<List<Integer>> except = Arrays.asList(Arrays.asList(2, 3, 3), Arrays.asList(3, 5), Arrays.asList(2, 2, 2, 2));
        Assert.assertEquals(except, combinationSum(candidates, target));
    }

}
