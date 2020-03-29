package study.erik.algorithm.leetcode.topic;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-29 08:36
 */
public class HouseRobber1Test {

    /**
     * title = House Robber 1
     * url = https://leetcode.com/problems/house-robber/
     *
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        return rob1_solution1(nums);
    }

    /**
     * 成绩：100% 和 5%
     * 总结：
     * @param nums
     * @return
     */
    public int rob1_solution1(int[] nums) {
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
        int[] nums2 = {2, 7, 9, 3, 1};
        Assert.assertEquals(12, rob1_solution1(nums2));

        int[] nums1 = {1, 2, 3, 1};
        Assert.assertEquals(4, rob1_solution1(nums1));
    }



}
