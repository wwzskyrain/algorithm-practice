package study.erik.algorithm.leetcode.series.subsequence;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-01 14:19
 */
public class LongestContinuousIncreasingSubSequenceTest {

    /**
     * title = Longest Continuous Increasing Subsequence
     * url = https://leetcode.com/problems/longest-continuous-increasing-subsequence/
     * 点评：这是这个系列最简单的题目了
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        return solution(nums);
    }


    /**
     * 成绩：99% 和 91，可能太简单了，大家都不屑提交吧。
     *
     * @param nums
     * @return
     */
    public int solution(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums.length;
        }

        int curValue = nums[0];
        int maxCount = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (curValue < nums[i]) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 1;
            }
            curValue = nums[i];
        }
        return maxCount;
    }


    @Test
    public void test_solution() {

        int[] nums1 = {2, 2, 2, 2, 2};
        Assert.assertEquals(1, findLengthOfLCIS(nums1));

        int[] nums = {1, 3, 5, 4, 7};
        Assert.assertEquals(3, findLengthOfLCIS(nums));
    }

}
