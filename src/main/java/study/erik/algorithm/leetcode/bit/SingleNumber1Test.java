package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-22 19:04
 * @description
 */
public class SingleNumber1Test {

    public int singleNumber(int[] nums) {
        return solution(nums);
    }

    /**
     * @param nums
     * @return
     */
    public int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("");
        }
        int theOne = nums[0];
        for (int i = 1; i < nums.length; i++) {
            theOne = theOne ^ nums[i];
        }
        return theOne;
    }

    @Test
    public void test_solution() {
        int[] nums = {2, 2, 1};
        Assert.assertEquals(1, singleNumber(nums));

        int[] nums1 = {4, 1, 2, 1, 2};
        Assert.assertEquals(4, singleNumber(nums1));
    }

}
