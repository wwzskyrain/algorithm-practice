/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : PartitionToKEqualSumSubsets.java, v 0.1 2022年02月28日 10:38 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PartitionToKEqualSumSubsets {

    @LetCodeCommit(title = "698. Partition to K Equal Sum Subsets",
            selfRemark = "这个题目我们做过，用了正宗的回溯法，其代码写的也是很经典，今天看了也佩服。"
                    + "可惜两年前的98%的成绩，如今已经超时了。"
                    + "然后加一个倒排序，23%就可以过了。"
                    + "为什么倒排后就能加速呢？"
                    + "首先相较于回溯的复杂度，排序本身不会增大整体算法的复杂度。"
                    + "其次，排序之后，先处理大的，能很好的---物语了",
            related = "2025. Maximum Number of Ways to Partition an Array")
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int temp = nums[l];
            nums[l++] = nums[h];
            nums[h--] = temp;
        }
        return backtrack(nums, new boolean[nums.length], 0, total / k, k);
    }

    public boolean backtrack(int[] nums, boolean[] visited, int curSum, int target, int k) {
        if (k == 1) {
            return true;
        }
        if (curSum > target) {
            return false;
        }
        if (curSum == target) {
            // 这里又一次递归调用，向k维度递归
            return backtrack(nums, visited, 0, target, k - 1);
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            // 这是正常的一次递归调用，visited维度
            if (backtrack(nums, visited, curSum + nums[i], target, k)) {
                return true;
            }
            visited[i] = false;
        }

        return false;
    }

    @Parameter
    public int[]   nums;
    @Parameter(1)
    public int     k;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[4,3,2,3,5,2,1]"), 4, true},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, canPartitionKSubsets(nums, k));
    }
}