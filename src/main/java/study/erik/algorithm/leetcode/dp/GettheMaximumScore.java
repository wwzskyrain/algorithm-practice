package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-04 08:31
 * title = Get the Maximum Score
 */
public class GettheMaximumScore {

    /**
     * 这更像是一个双指针的题目，而不是一个dp.
     * dp1表示当前i1和i2指针时(不包括当前指针)，以nums1元素结尾的最大值
     * dp2表示当前i1和i2指针时(不包括当前指针)，以nums2元素结尾的最大值
     * 双指针i1和i2按照小的nus[i]先走,即大值等小值
     *
     * @param nums1
     * @param nums2
     * @return
     */
    @LetCodeCommit(title = "Get the Maximum Score", time = 100, space = 100, diff = "h", types = LetCodeCommit.Type.Double_Point)
    public int maxSum(int[] nums1, int[] nums2) {
        int mod = 1000000007;
        long dp1 = 0, dp2 = 0;
        int i1 = 0, i2 = 0;
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] == nums2[i2]) {
                dp1 = dp2 = Math.max(dp1, dp2) + nums1[i1];
                i1++;
                i2++;
            } else if (nums1[i1] < nums2[i2]) {
                dp1 += nums1[i1++];
            } else {
                dp2 += nums2[i2++];
            }
        }
        while (i1 < nums1.length) {
            dp1 += nums1[i1++];
        }
        while (i2 < nums2.length) {
            dp2 += nums2[i2++];
        }

        return ((int) (Math.max(dp1, dp2) % mod));
    }

    @Test
    public void test_case_1() {

        int[] nums1 = {2, 4, 5, 8, 10}, nums2 = {4, 6, 8, 9};
        Assert.assertEquals(30, maxSum(nums1, nums2));
    }

    @Test
    public void test_case_2() {

        int[] nums1 = {1, 3, 5, 7, 9}, nums2 = {3, 5, 100};
        Assert.assertEquals(109, maxSum(nums1, nums2));
    }

    @Test
    public void test_case_3() {

        int[] nums1 = {1, 2, 3, 4, 5}, nums2 = {6, 7, 8, 9, 10};
        Assert.assertEquals(40, maxSum(nums1, nums2));
    }

    @Test
    public void test_case_4() {

        int[] nums1 = {1, 4, 5, 8, 9, 11, 19}, nums2 = {2, 3, 4, 11, 12};
        Assert.assertEquals(61, maxSum(nums1, nums2));
    }

}
