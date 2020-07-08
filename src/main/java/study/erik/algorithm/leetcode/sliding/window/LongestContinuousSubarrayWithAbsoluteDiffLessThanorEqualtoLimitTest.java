package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;

/**
 * @author erik.wang
 * @date 2020-07-05 12:49
 * title = Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimitTest {

    @Test
    public void test_() {

        Assert.assertEquals(longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5), 4);

        int[] nums = {8, 2, 4, 7};
        int limit = 4;
        Assert.assertEquals(longestSubarray(nums, limit), 2);
    }

    public int longestSubarray(int[] nums, int limit) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int i, j;
        i = j = 0;
        for (; j < nums.length; j++) {

            map.put(nums[j], 1 + map.getOrDefault(nums[j], 0));

            if (map.lastEntry().getKey() - map.firstEntry().getKey() > limit) {
                map.put(nums[i], map.get(nums[i]) - 1);
                if (map.get(nums[i]) == 0) {
                    map.remove(nums[i]);
                }
                i++;
            }
        }
        return j - i;
    }

}
