package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-05-01 11:11
 */
public class PartitionEqualSubsetSum {

    @Test
    public void test_solution() {

        int[] nums3 = {100};
        Assert.assertFalse(canPartition(nums3));

        int[] nums2 = {1, 1};
        Assert.assertTrue(canPartition(nums2));

        int[] nums1 = {1, 2, 3, 5};
        Assert.assertFalse(canPartition(nums1));

        int[] nums = {1, 5, 11, 5};
        Assert.assertTrue(canPartition(nums));
    }

    /**
     * title = Partition Equal Subset Sum
     * url = https://leetcode.com/problems/partition-equal-subset-sum/
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums.length == 1) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : nums) {
            //倒这来，等号右边的dp[j]是dp[i-1][j]， 等号左边的dp[j]是dp[i][j]。
            //dp[i][j]只有两个可选值，dp[i-1][j]、dp[i-1][j-num];
            for (int j = dp.length - 1; j >= 0 && j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[sum];
    }


}
