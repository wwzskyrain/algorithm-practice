package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-03-21 14:41
 * @description
 */
public class ThreeSumClosest {

    /**
     * title = 3 sum closet.
     * url = https://leetcode.com/problems/3sum-closest/submissions/
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        return solution2(nums, target);
    }

    /**
     * 效果提升的不错，成绩 51% 和 6%了。
     *  剩下的优化，估计就是点点滴滴的优化了，算了。
     * @param nums
     * @param target
     * @return
     */
    public int solution2(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("nums is less than three element");
        }
        Arrays.sort(nums);
        int closet = Integer.MAX_VALUE;
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            int currentTarget = target - nums[i];
            while (low < high) {
                int tempSum = nums[low] + nums[high];
                int tempCloset = Math.abs(currentTarget - tempSum);
                if (closet > tempCloset) {
                    sum = tempSum + nums[i];
                    closet = tempCloset;
                }
                if (currentTarget > tempSum) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return sum;
    }

    /**
     * 成绩:5% and 6% 好低。
     * 时间复杂度是 N三次方。
     * 这个题目的最好的时间复杂都市N平方：先排序，然后在遍历={夹逼寻找closet的值}，见{@link #solution2(int[], int)}
     *
     * @param nums
     * @param target
     * @return
     */
    public int solution1(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("nums is less than three element");
        }

        Integer closet = Integer.MAX_VALUE;
        int sum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int tempSum = nums[i] + nums[j] + nums[k];
                    int tempCloset = Math.abs(tempSum - target);
                    if (tempCloset < closet) {
                        closet = tempCloset;
                        sum = tempSum;
                    }
                }
            }
        }
        return sum;
    }


    @Test
    public void test_solution() {

//        int[] nums1 = {1, 1, 1, 0};
//        int target1 = -100;
//        Assert.assertEquals(2, solution2(nums1, target1));

        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        Assert.assertEquals(2, solution2(nums, target));
    }

}
