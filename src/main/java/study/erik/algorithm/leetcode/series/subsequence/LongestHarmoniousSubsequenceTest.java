package study.erik.algorithm.leetcode.series.subsequence;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-04-01 14:52
 */
public class LongestHarmoniousSubsequenceTest {

    /**
     * url = https://leetcode.com/problems/longest-harmonious-subsequence/
     * title = longest harmonious subsenquence
     * difficulty = easy
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        return solution(nums);
    }

    /**
     * 成绩：97% 和 94 成绩不错
     * 解法：双指针法。注意可以先排序。因为和谐子序列是没有次序之分的。
     * 注意：注意'和谐子序列'的定义，必须要"最大值和最小值相差1"，一个数字的数组可不是和谐序列呀。
     * 评价题目：这个题目很有意思的，虽然只是easy难度
     *
     * @param nums
     * @return
     */
    public int solution(int[] nums) {
        if (nums.length < 1) {
            return nums.length;
        }
        Arrays.sort(nums);
        int maxCount = 0;
        int index1 = 0, index2 = 0;
        while (index2 < nums.length) {
            if (nums[index2] == nums[index1]) {
                index2++;
            } else if (nums[index2] == nums[index1] + 1) {
                maxCount = Math.max(maxCount, index2 - index1 + 1);
                index2++;
            } else {
                while (nums[index2] > nums[index1] + 1) {
                    index1++;
                }
            }

        }
        return maxCount;
    }

    @Test
    public void test_solution() {

        int[] nums1 = {1, 1, 1, 1};
        Assert.assertEquals(0, findLHS(nums1));

        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        Assert.assertEquals(5, findLHS(nums));
    }

}
