package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @date 2019/06/01
 **/
@RunWith(Parameterized.class)
public class Permutations {

    /**
     * 全排列，回溯法中的经典基础题目
     * leetCode题目描述 https://leetcode.com/problems/permutations/
     * 战绩：faster than 99.71% less than 97.72%，很快，但是内存使用太多。why
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute_by_my_self(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        doPermuteBySwitch(result, nums, 0);

        return result;
    }

    public List<List<Integer>> permute(int[] nums) {
        backtrace(nums, 0);
        return list;
    }

    public List<List<Integer>> list = new ArrayList();

    public void backtrace(int[] nums, int i) {
        if (i == nums.length - 1) {
            list.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        Consumer<Integer> swap = (Integer j) -> {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        };
        backtrace(nums, i + 1);
        for (int j = i + 1; j < nums.length; j++) {
            swap.accept(j);
            backtrace(nums, i + 1);
            swap.accept(j);
        }
    }

    public void doPermuteBySwitch(List<List<Integer>> result, int[] nums, int point) {

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

            doPermuteBySwitch(result, nums, point + 1);

            temp = nums[point];
            nums[point] = nums[i];
            nums[i] = temp;
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray("[1,2,3]")},
                {ArrayUtils.buildArray("[1,2,4,5,10]")},
                {ArrayUtils.buildArray("[2,12,4,6,3,8]")},
                });
    }

    @Parameterized.Parameter
    public int[] nums;

    @Test
    public void test() {
        System.out.println(permute(nums));
    }
}
