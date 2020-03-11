package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @Date 2019-11-03
 */
public class PartitionKSubsets {

    @Test
    public void test_can_partition_k_subset() {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        Assert.assertTrue(canPartitionKSubsets2(nums, k));

        int[] nums1 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int k1 = 5;
        Assert.assertTrue(canPartitionKSubsets2(nums1, k1));
    }

    /**
     * title: Partition to K Equal Sum Subsets
     *  题目分析：这是一个求解题，是回溯法的拿手菜。
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets1(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        return canDivide1(nums, new int[k], 0, sum / k);
    }

    /**
     * 是否能够将nums[0...index]安放到sums中。
     * 遗憾，超时了。
     *
     * @param nums
     * @param sums
     * @param index
     * @param target
     * @return
     */
    public boolean canDivide1(int[] nums, int[] sums, int index, int target) {
        if (index == nums.length) {
            for (int sum : sums) {
                if (sum != target) {
                    return false;
                }
            }
            return true;
        }

        for (int i = 0; i < sums.length; i++) {
            if (sums[i] + nums[index] > target) {
                continue;
            }

            sums[i] += nums[index];
            if (canDivide1(nums, sums, index + 1, target)) {
                return true;
            }
            //回溯法，一定要有回溯。什么时候回溯，自调用找不到解就要回溯。
            sums[i] -= nums[index];
        }
        // 到这里就说明，nums[index]无处安放了。
        return false;
    }


    public boolean canPartitionKSubsets2(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % k != 0) {
            return false;
        }
        return canDivide2(nums, k, sum / k, 0, new boolean[nums.length], 0);
    }

    /**
     * 是否能将nums全部分配到k个组中。
     *
     * @param nums    元数据
     * @param k       分组大小
     * @param target  每个组的sum
     * @param curSun  当前和
     * @param visited 访问记录，回溯法一般都有这个的。
     * @param start 加上一个start，拯救了超时，而且吧时间提到了100%，amazing！
     * @return
     */
    public boolean canDivide2(final int[] nums, final int k, final int target, int curSun, boolean[] visited, int start) {
        if (k == 1) {
            return true;
        }

        if (curSun > target) {
            return false;
        }

        if (curSun == target) {
            return canDivide2(nums, k - 1, target, 0, visited, 0);
        }

        for (int i = start; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            if (canDivide2(nums, k, target, curSun + nums[i], visited, i + 1)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }


}
