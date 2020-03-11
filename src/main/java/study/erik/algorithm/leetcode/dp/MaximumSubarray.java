package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-11 18:54
 * @description
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        return solution(nums);
    }

    /**
     * 这是一个非常经典的dp问题；而最终的解法，更是优美的很。
     * 由于这个问题，我已经很熟悉了，就不在分析了。这里想记录一下细节。
     * 1.   边界条件
     * 2.   maxSum的初始化要是 nums[0]，而不应该是Integer.MIN_VALUE.
     * @param nums
     * @return
     */
    public int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxSum = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(currentSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    @Test
    public void test_solution() {
        int[] nums1 = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assert.assertEquals(6, solution(nums1));

        int[] nums2 = new int[]{1};
        Assert.assertEquals(1, solution(nums2));
    }

}
