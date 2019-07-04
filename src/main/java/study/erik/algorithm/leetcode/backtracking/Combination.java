package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/06/02
 **/
public class Combination {

    @Test
    public void test_combine() {
        List<List<Integer>> combine = combine_standard_trace(4, 2);
        System.out.println(combine);
    }

    /**
     * title: Combinations
     * link = https://leetcode.com/problems/combinations/
     * description = Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * 解法：标准回溯法
     * 战绩：faster than 43.79%  less than 66.75%并不是很好，不过这个写法真的很标准
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine_standard_trace(int n, int k) {

        List<List<Integer>> combine = new ArrayList<>();
        doCombination(combine, 1, n, k, new ArrayList<>());
        return combine;
    }

    public void doCombination(List<List<Integer>> combine, int position, int n, int k, List<Integer> currentCombine) {

        if (currentCombine.size() == k) {
            combine.add(new ArrayList<>(currentCombine));
            return;
        }

        for (int i = position; i <= n; i++) {
            int currentSize = currentCombine.size();
            currentCombine.add(currentSize, i);
            doCombination(combine, i + 1, n, k, currentCombine);
            currentCombine.remove(currentSize);
        }
    }


    /**
     * 这个解法是公式的演艺 C(n,k) = C(n-1,k-1) + C(n-1,k)
     * 战绩：faster than 85.65%  less than 41.35%
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine_recursive(int n, int k) {

        ArrayList<List<Integer>> result = new ArrayList<>();

        if (n == k) {
            List<Integer> data = new ArrayList<>();
            for (int i = 1; i <= k; i++) {
                data.add(i);
            }
            result.add(data);
            return result;
        }
        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                result.add(new ArrayList<>(Arrays.asList(i)));
            }
            return result;
        }

        List<List<Integer>> sub1 = combine_recursive(n - 1, k - 1);
        for (List<Integer> list : sub1) {
            list.add(n);
        }

        List<List<Integer>> sub2 = combine_recursive(n - 1, k);
        sub1.addAll(sub2);

        return sub1;
    }

    @Test
    public void test_combine_recursive() {
        List<List<Integer>> combine = combine_recursive(4, 2);
        System.out.println(combine);
    }


}
