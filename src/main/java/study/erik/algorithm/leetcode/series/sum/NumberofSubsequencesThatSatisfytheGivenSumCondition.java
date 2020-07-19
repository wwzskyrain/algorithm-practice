package study.erik.algorithm.leetcode.series.sum;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-07-19 16:07
 * title = Number of Subsequences That Satisfy the Given Sum Condition
 */
public class NumberofSubsequencesThatSatisfytheGivenSumCondition {


    @Test
    public void test_() {

        int[] nums3 = {5, 2, 4, 1, 7, 6, 8};
        int target3 = 16;
        Assert.assertEquals(127, numSubseq(nums3, target3));

        int[] nums2 = {2, 3, 3, 4, 6, 7};
        int target2 = 12;
        Assert.assertEquals(61, numSubseq(nums2, target2));


        int[] nums1 = {3, 3, 6, 8};
        int target1 = 10;
        Assert.assertEquals(6, numSubseq(nums1, target1));


        int[] nums = {3, 5, 6, 7};
        int target = 9;
        Assert.assertEquals(4, numSubseq(nums, target));
    }

    /**
     * 大神 lee215的解法：很赞
     * 1.   排序
     * 2.   高低指针从数组的首尾出相向而行
     * 3.
     *
     * @param nums
     * @param target
     * @return
     */
    public int numSubseq(int[] nums, int target) {

        int l = 0, r = nums.length - 1, count = 0, mod = 1000000007;

        Arrays.sort(nums);

        int[] pow = new int[nums.length];
        pow[0] = 1;
        for (int i = 1; i < pow.length; i++) {
            pow[i] = (pow[i - 1] * 2) % mod;
        }
        while (l <= r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else {
                //这个delta是很有意义的：只需要pow(r - l)，这里定住了左边l，即A[l+1],A[l+2],...,A[r]是可以选的，即2^(size(A[l+1],A[l+2],...,A[r])) = 2^(l - r).
                //随即l++
                count = (count + pow[r - (l++)]) % mod;
            }
        }
        return count;
    }


}
