package study.erik.algorithm.leetcode.zijie.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/12 ，时间：09:44
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class MedianOfTwoSortedArrays {

    @LetCodeCommit(title = "4. Median of Two Sorted Arrays",
    selfRemark = "参考 https://leetcode.cn/problems/median-of-two-sorted-arrays/solutions/8999/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/?company_slug=bytedance 题解4")
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            //保证 m < n， 时间复杂度O(log(min(m,n)))
            return findMedianSortedArrays(nums2, nums1);
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            //寻找目标分叉——i的合适位置
            int i = (iMax + iMin) / 2;
            int j = (m + n + 1) / 2 - i; //j对于m和n的和为奇偶都可以。

            if (j != 0 && i != m && nums2[j - 1] > nums1[i]) {
                //检查1. nums2[j - 1] <= nums1[i]
                iMin = i + 1;
            } else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) {
                //检查2. nums1[i - 1] <= nums2[j]
                iMax = i - 1;
            } else {
                //过了检查1和2，找到了目标拆分点。
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums2[j - 1], nums1[i - 1]);
                }

                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int maxRight = 0;
                if (i == m) {
                    maxRight = nums2[j];
                } else if (j == n) {
                    maxRight = nums1[i];
                } else {
                    maxRight = Math.min(nums2[j], nums1[i]);
                }
                return (maxLeft + maxRight) / 2.0;
            }
        }
        return 0.0;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2.00000, ArrayUtils.buildArray("[1,3]"), ArrayUtils.buildArray("[2]")},
                {2.50000, ArrayUtils.buildArray("[1,2]"), ArrayUtils.buildArray("[3,4]")},
                {2.50000, ArrayUtils.buildArray("[1,3]"), ArrayUtils.buildArray("[2,7]")},
        });
    }

    @Parameterized.Parameter
    public double expect;
    @Parameterized.Parameter(1)
    public int[] nums1;
    @Parameterized.Parameter(2)
    public int[] nums2;


    @Test
    public void test() {
        System.out.println(findMedianSortedArrays(nums1, nums2));
        Assert.assertTrue(Math.abs(expect - findMedianSortedArrays(nums1, nums2)) < 1e-6);
    }

}
