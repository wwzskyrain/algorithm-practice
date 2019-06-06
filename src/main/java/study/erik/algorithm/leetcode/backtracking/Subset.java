package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/06/01
 * 共四个解法：位图法、遍历子集二叉树、标准回溯法、迭代法。其中位图法和遍历二叉树、迭代法比较容易理解。
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


    /**
     * 标准回溯法，faster than 67.81%，  less than 99.23%
     * 战绩不佳，其效率远不如我自己的遍历二叉树；
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_StandardBacktrace(int[] nums) {

        List<List<Integer>> allSubset = new ArrayList<>();
        findSubsetByStandardBacktrace(allSubset, nums, 0, new ArrayList<>());
        return allSubset;
    }

    /**
     * @param allSubset
     * @param nums
     * @param position
     * @param currentSubset 当前一个解，也是用于试探的解
     */
    public void findSubsetByStandardBacktrace(List<List<Integer>> allSubset, int[] nums, int position, List<Integer> currentSubset) {

        allSubset.add(new ArrayList<>(currentSubset));

        for (int i = position; i < nums.length; i++) {
            int currentIndex = currentSubset.size();
            currentSubset.add(currentIndex, nums[i]);
            findSubsetByStandardBacktrace(allSubset, nums, i + 1, currentSubset);//注意，这是i+1，而不是position+1.
            currentSubset.remove(currentIndex);
        }
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
