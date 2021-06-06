package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-04-26 09:59
 */

public class ContinuousSubarraySum {

    /**
     * title = Continuous Subarray Sum
     * url=https://leetcode.com/problems/continuous-subarray-sum/
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        return solution1(nums, k);
    }

    @Test
    public void test_1() {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        Assert.assertTrue(checkSubarraySum(nums, k));
    }

    @Test
    public void test_2() {
        int[] nums = {23, 2, 6, 4, 7};
        int k = 6;
        Assert.assertTrue(checkSubarraySum(nums, k));
    }

    @Test
    public void test_3() {
        int[] nums = {23, 2, 6, 4, 7};
        int k = 13;
        Assert.assertFalse(checkSubarraySum(nums, k));
    }

    @Test
    public void test_4() {
        int[] nums = {23, 2, 4, 6, 6};
        int k = 7;
        Assert.assertFalse(checkSubarraySum(nums, k));
    }

    public boolean solution1(int[] nums, int k) {
// 该解法(滑动窗口)是错误的，
        if (nums.length < 2) {
            return false;
        }

        int sum = nums[0] % k;
        int i = 1;
        int j = 0;
        while (i < nums.length && j <= i) {
            if (sum == k) {
                return true;
            } else if (sum < k) {
                sum += nums[i++] % k;
            } else {
                sum = sum - nums[j++] % k;
            }
        }
        return false;

    }

    public boolean solution(int[] nums, int k) {

        /**
         * key=前缀和mod k， value=第一次出现的index
         */
        Map<Integer, Integer> mem = new HashMap<Integer, Integer>(16) {{
            put(0, -1);
        }};

        int remain = 0;
        for (int i = 0; i < nums.length; i++) {
            remain = (remain + nums[i]);
//            if (remain == 0) {
//                return true;
//            }
            if (k != 0) {
                remain = remain % k;
            }

            Integer preIndex = mem.get(remain);
            if (preIndex != null) {
                if (i - preIndex > 1) {
                    return true;
                }
            } else {
                mem.put(remain, i);
            }
        }
        return false;
    }

    @Test
    public void test_() {


//        int[] nums2 = new int[]{5, 0, 0, 0, 2};  //可以不考虑0
//        int k2 = 6;
//        Assert.assertFalse(checkSubarraySum(nums2, k2));

        int[] nums1 = new int[]{23, 2, 6, 4, 7};
        int k1 = 6;
        Assert.assertTrue(checkSubarraySum(nums1, k1));

        int[] nums = new int[]{23, 2, 4, 6, 7};
        int k = 6;
        Assert.assertTrue(checkSubarraySum(nums, k));
    }

}
