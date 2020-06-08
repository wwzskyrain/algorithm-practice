package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-06-07 23:02
 */
public class NextPermutationTest {

    @Test
    public void test_() {


        int[] nums4 = new int[]{5, 1, 1};
        nextPermutation(nums4);
        System.out.println(Arrays.toString(nums4));

        int[] nums3 = new int[]{1, 3, 2};
        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3));

        int[] nums = new int[]{1, 2, 3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));


        int[] nums1 = new int[]{3, 2, 1};
        nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = new int[]{1, 1, 5};
        nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    /**
     * title = next greater permutation
     * url = https://leetcode.com/problems/next-permutation/submissions/
     * 成绩 32 和 92， 为什么不快呢？我这是lgN的计算复杂度呢
     * m级别的题目几乎难不倒我了，至少可以解出来呢
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }

        int left = nums.length - 2;
        int right = nums.length - 1;

        // 1.从后面开始，找到倒数第一个'顺序对儿'
        while (left >= 0 && nums[left] >= nums[right]) {
            left--;
            right--;
        }

        if (left == -1) {
            //如果全体都是逆序，则直接排序
            Arrays.sort(nums);
            return;
        } else {
            //把后面大于nums[left]的最小值与left交换，然后把left之后的进行排序
            int minIndex = right;
            for (int i = right + 1; i < nums.length; i++) {
                if (nums[i] > nums[left] && nums[i] < nums[minIndex]) {
                    minIndex = i;
                }
            }

            int temp = nums[left];
            nums[left] = nums[minIndex];
            nums[minIndex] = temp;

            Arrays.sort(nums, right, nums.length);
            return;
        }
    }

}
