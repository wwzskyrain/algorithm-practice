package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-11 15:01
 * @description
 */
public class JumpGameII {


    /**
     * title = jump game II
     * url = https://leetcode.com/problems/jump-game-ii/
     * 问题分析：找到最小跳跃次数
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        return solution1(nums);
    }


    public int solution1(int[] nums) {
        int length = nums.length;
        int[] mixStep = new int[length];

        for (int i = 0; i < length - 1; i++) {
            mixStep[i] = Integer.MAX_VALUE;
        }

        mixStep[length - 1] = 0;

        for (int i = length - 2; i >= 0; i--) {

            int min = length;
            for (int j = 1; j <= nums[i] && j + i < length; j++) {
                min = Math.min(min, mixStep[j + i]);
            }
            mixStep[i] = min + 1;
        }

        return mixStep[0];
    }

    /**
     * 成绩：15% 和30%
     * 成绩不好。解法分析:和JumpGameI的solution2一样，
     * 采用span覆盖法
     * @param nums
     * @return
     */
    public int solution2(int[] nums) {

        int[] minSteps = new int[nums.length];
        minSteps[0] = 0;
        for (int i = 1; i < minSteps.length; i++) {
            minSteps[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            int span = nums[i];
            for (int j = 1; j <= span; j++) {
                int index = i + j;
                if (index < nums.length) {
                    minSteps[index] = Math.min(minSteps[index], minSteps[i] + 1);
                }
            }
        }
        return minSteps[nums.length - 1];
    }


    @Test
    public void test_jump() {
        int[] array1 = new int[]{2, 3, 1, 1, 4};
        int[] array2 = new int[]{2, 3, 0, 1, 4};
        Assert.assertEquals(2, solution2(array1));
        Assert.assertEquals(2, solution2(array2));
    }


}
