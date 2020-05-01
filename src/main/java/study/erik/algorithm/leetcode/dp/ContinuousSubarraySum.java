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
        return solution(nums, k);
    }


    public boolean solution(int[] nums, int k) {

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
