package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/06/01
 **/
public class Subset {

    /**
     * LeeCode描述:https://leetcode.com/problems/subsets/
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_BitGraph(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        findSubset(result, nums);
        return result;
    }

    /**
     * 位图法的解答
     *
     * @param result
     * @param nums
     */
    public void findSubset(List<List<Integer>> result, int[] nums) {

        int total = 1 << nums.length;

        for (int i = 0; i < total; i++) {
            List<Integer> subSet = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                int point = 1 << j;
                if ((i & point) != 0) {
                    subSet.add(nums[j]);
                }
            }
            result.add(subSet);
        }
    }

    /**
     * faster than 100.00%, less than 99.23%，内存占用太高了。
     * 回溯法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_Backtrace(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        findSubsetByTraceBinaryTree(result, nums, 0, new ArrayList<>());
        return result;
    }

    /**
     * 回溯法的具体实现之遍历一颗二叉树，这颗二叉树是一颗子集树。
     *
     * @param result
     * @param nums
     * @param level
     * @param currentSet
     */
    public void findSubsetByTraceBinaryTree(List<List<Integer>> result, int[] nums, int level, List<Integer> currentSet) {

        if (nums == null || nums.length == 0) {
            return;
        }
        List<Integer> temp = new ArrayList<>(currentSet);
        if (level == nums.length - 1) {
            result.add(new ArrayList<>(temp));
            temp.add(nums[level]);
            result.add(temp);
            return;
        }

        findSubsetByTraceBinaryTree(result, nums, level + 1, temp);
        temp.add(nums[level]);
        findSubsetByTraceBinaryTree(result, nums, level + 1, temp);
    }

    @Test
    public void test_subsetI() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = subsets_Backtrace(nums);
        System.out.println(result);
    }

    /**
     * 迭代法，这个解法真的很美。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_Iteration(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        result.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            int element = nums[i];
            int currentSize = result.size();
            for (int j = 0; j < currentSize; j++) {
                List<Integer> subSetView = new ArrayList<>(result.get(j));
                subSetView.add(element);
                result.add(subSetView);
            }
        }

        return result;
    }






}
