package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/06/02
 **/
public class PermutationsII {

    @Test
    public void test_permuteUnique() {

        int[] nums = new int[]{1, 1, 2};
        System.out.println(permuteUnique(nums));
    }

    /**
     *  link = https://leetcode.com/problems/permutations-ii/
     *  title=Permutations II
     *  description：Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     * 有了全排列的基础，这一题就很简单了。关键点就是去重。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> permute = new ArrayList<>();
        doPermute(permute, nums, 0);
        return permute;
    }

    public void doPermute(List<List<Integer>> result, int[] nums, int level) {

        if (nums == null || nums.length == 0) {
            return;
        }
        if (level == nums.length - 1) {
            List<Integer> paiLie = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                paiLie.add(nums[i]);
            }
            result.add(paiLie);
        }

        for (int i = level; i < nums.length; i++) {

            //注意，关键点，判断当前nums[i]是否可以打头时，一定要从本level开始开启，而且比一个不漏的看。
            //刚刚想通过事先排序然后比较nums[i]和nums[i-1]来加速，可惜，逻辑错误的。因为有交换操作会打乱nums的排序
            boolean hasSeen = false;
            for (int j = level; j < i; j++) {
                if (nums[j] == nums[i]) {
                    hasSeen = true;
                    break;
                }
            }
            if(hasSeen){
                continue;
            }
            int temp = nums[level];
            nums[level] = nums[i];
            nums[i] = temp;

            doPermute(result, nums, level + 1);

            temp = nums[level];
            nums[level] = nums[i];
            nums[i] = temp;
        }
    }


}
