package study.erik.algorithm.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/06/01
 **/
public class Permutations {

    /**
     * 全排列，回溯法中的经典基础题目
     * leetCode题目描述 https://leetcode.com/problems/permutations/
     *  战绩：faster than 99.71% less than 97.72%，很快，但是内存使用太多。why
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        doPermute(result, nums, 0);

        return result;
    }

    public void doPermute(List<List<Integer>> result, int[] nums, int point) {

        if (nums == null || nums.length == 0) {
            return;
        }

        if (point == nums.length - 1) {
            List<Integer> paiLie = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                paiLie.add(nums[i]);
            }
            result.add(paiLie);

        }

        for (int i = point; i < nums.length; i++) {

            int temp = nums[point];
            nums[point] = nums[i];
            nums[i] = temp;

            doPermute(result, nums, point + 1);

            temp = nums[point];
            nums[point] = nums[i];
            nums[i] = temp;
        }
    }


}
