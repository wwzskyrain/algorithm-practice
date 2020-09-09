package study.erik.algorithm.leetcode.recursive;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-09-08 08:53
 */
public class Combinations {

    @LetCodeCommit(title = "77. Combinations")
    public List<List<Integer>> combine(int n, int k) {
        Set<List<Integer>> allCombine = new HashSet<>();
        dfs(n, k, allCombine, new LinkedList<>());
        return new ArrayList<>(allCombine);
    }

    public void dfs(int n, int k, Set<List<Integer>> allCombine, LinkedList<Integer> curCombine) {

        if (n < k) {
            return;
        }

        if (k == 0) {
            allCombine.add(new ArrayList<>(curCombine));
            return;
        }
        for (int i = n; i > 0; i--) {
            curCombine.addLast(i);
            dfs(i - 1, k - 1, allCombine, curCombine);
            curCombine.removeLast();
            dfs(i - 1, k, allCombine, curCombine);
        }
        for (int i = n; i > 0; i--) {

        }
    }

    @Test
    public void test_solution_1() {
        int n = 4, k = 2;
        List<List<Integer>> allCombine = combine(n, k);
        allCombine.stream().forEach(System.out::println);
    }

    @Test
    public void test_solution_2() {
        int n = 1, k = 1;
        List<List<Integer>> allCombine = combine(n, k);
        allCombine.stream().forEach(System.out::println);
    }


}
