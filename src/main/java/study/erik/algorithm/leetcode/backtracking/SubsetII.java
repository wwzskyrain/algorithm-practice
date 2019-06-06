package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/06/01
 **/
public class SubsetII {


    /**
     * 相对于I，题目输入的是一个有重复元素的nums
     * 这里是对'标准回溯法'的一个变形，形成了最终解法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_StandardBacktrace(int[] nums) {

        List<List<Integer>> allSubset = new ArrayList<>();
        Arrays.sort(nums);
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

            if (i == position || nums[i] != nums[i - 1]) {

                int currentIndex = currentSubset.size();
                currentSubset.add(currentIndex, nums[i]);

                findSubsetByStandardBacktrace(allSubset, nums, i + 1, currentSubset);//注意，这是i+1，而不是position+1.
                currentSubset.remove(currentIndex);
            }

        }
    }


    /**
     * 迭代法，这个解法真的很美。
     * 这是针对具体问题II的'变形迭代法'做出的解答。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets_Iteration(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        result.add(new ArrayList<>());

        int nextStart = 0;
        for (int i = 0; i < nums.length; i++) {

            int currentSize = result.size();

            int j = 0;
            if (i > 0 && nums[i] == nums[i - 1]) {
                j = nextStart;
            }
            for (; j < currentSize; j++) {
                List<Integer> subSetView = new ArrayList<>(result.get(j));
                subSetView.add(nums[i]);
                result.add(subSetView);
            }
            nextStart = currentSize;
        }

        return result;
    }

    @Test
    public void test_subsetsII_Iteration() {

        int[] nums = new int[]{1, 1};
        List<List<Integer>> allSubset = subsets_Iteration(nums);
        System.out.println(allSubset);

    }


}
