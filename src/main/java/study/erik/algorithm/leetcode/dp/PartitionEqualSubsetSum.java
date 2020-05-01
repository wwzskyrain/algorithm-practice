package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

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
        return solution(nums);
    }

    /**
     * 成绩：34 和 6 ，太低了，肯定用错了方法。
     * 优化后：成绩 68 和 50，也不是很高，不过也不错了。
     * 优化点在于把二维dp变成了一维dp，变化也很简单粗暴，如果懂得01背包问题的简化，就很容易理解这一步。
     * <p>
     * 解法思路：当然还是01背包问题了。
     *
     * @param nums
     * @return
     */
    public boolean solution(int[] nums) {
        if (nums.length == 1) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = dp.length - 1; j >= 0; j--) {
                if (!dp[j] && j - nums[i] >= 0) {
                    int k;
                    dp[j] = ((k = j - nums[i]) >= 0 ? dp[k] : false) || dp[j];
                }
            }
        }
        return dp[sum];
    }


}
