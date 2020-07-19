package study.erik.algorithm.leetcode.series.sum;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-07-18 23:50
 * title= Subarray Sum Equals K
 */
public class SubarraySumEqualsK {

    @Test
    public void test_() {
        int[] nums = {1, 1, 1};
        Assert.assertEquals(2, subarraySum(nums, 2));
    }

    /**
     * 成绩：46 和 5
     * 空间利用率何其多呀
     * 解法：prefix_sum + map
     * 拓展：如果nums中都是非负的，那么就可以继续优化，用滑动窗口，或者叫做'快慢指针'
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {

        int sum = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
