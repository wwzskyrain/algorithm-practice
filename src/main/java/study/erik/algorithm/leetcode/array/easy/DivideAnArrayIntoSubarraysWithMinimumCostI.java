package study.erik.algorithm.leetcode.array.easy;


import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class DivideAnArrayIntoSubarraysWithMinimumCostI {
    public int minimumCost(int[] nums) {
        int min1 = nums[1];
        int min1Idx = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min1) {
                min1 = nums[i];
                min1Idx = i;
            }
        }
        int min2 = Integer.MAX_VALUE;
        for(int i = 1; i < nums.length; i++) {
            if(i != min1Idx && nums[i] < min2) {
                min2 = nums[i];
            }
        }

        return nums[0] + min1 + min2;
    }
}
