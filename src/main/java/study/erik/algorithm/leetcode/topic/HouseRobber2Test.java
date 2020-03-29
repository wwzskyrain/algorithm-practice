package study.erik.algorithm.leetcode.topic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-03-29 08:36
 */
public class HouseRobber2Test {

    /**
     * title = House Robber 2
     * url = https://leetcode.com/problems/house-robber-ii/
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        return solution(nums);
    }

    /**
     * 成绩：100% 和 6%
     * 思路：
     * 这里 f(0,n) = max{ f(0,n-1), f(1,n-2) + nums[n] }
     * 所以，直接用两遍第一题的解法，再求max即可。
     * 小结：如果没有第一题的铺垫，还真的很难办呢。差一点就用二位矩阵来解题了
     * @param nums
     * @return
     */
    public int solution(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        if (nums.length == 3) {
            return Math.max(nums[0], Math.max(nums[1], nums[2]));
        }

        int[] copyFrom0ToN_1 = Arrays.copyOf(nums, nums.length - 1);
        int resultFrom0ToN_1 = solutionForHouseRobber1(copyFrom0ToN_1);

        int[] copyFrom1ToN_2 = Arrays.copyOfRange(nums, 1, nums.length - 2);
        int resultFrom1ToN_2 = solutionForHouseRobber1(copyFrom1ToN_2);

        return Math.max(resultFrom0ToN_1, resultFrom1ToN_2 + nums[nums.length - 1]);
    }

    /**
     * 成绩：100% 和 5%
     * 总结：
     *
     * @param nums
     * @return
     */
    public int solutionForHouseRobber1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        if (nums.length == 3) {
            return Math.max(nums[1], nums[0] + nums[2]);
        }

        int result1 = Math.max(nums[1], nums[0] + nums[2]);
        int result2 = Math.max(nums[0], nums[1]);
        int result = 0;

        for (int i = 3; i < nums.length; i++) {
            result = Math.max(result2 + nums[i], result1);
            result2 = result1;
            result1 = result;
        }

        return result;
    }


    @Test
    public void test_rob1_solution1() {
        int[] nums2 = {1, 2, 3, 1};
        Assert.assertEquals(4, solution(nums2));

        int[] nums1 = {2, 3, 2};
        Assert.assertEquals(3, solution(nums1));
    }


}
