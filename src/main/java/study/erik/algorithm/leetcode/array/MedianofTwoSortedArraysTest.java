package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-14 09:00
 */
public class MedianofTwoSortedArraysTest {


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return solution(nums1, nums2);
    }


    /**
     * 思路解析：首先声明此解法来自：https://github.com/Blankj/awesome-java-leetcode/blob/master/note/004/README.md
     * 问题是求两个排序数组的中位数，可以扩展成求两个排序数组的第k大的数字。
     * <p>
     * 首先这样想：假设两个数组是a和b，求a和b中第k小的数字。设第k大的数字是K
     * a中的前m个，和b中的前k-m个，这k个元素中a[m-1]和b[k-m-1]这两个值，一定是'一个大于K一个小于K'——自己想下哈。
     * 这就看出了夹逼的样子了。
     * 假设a[m-1]小，那么这m个元素都是小于K的，把他们都舍弃。问题变成：从a[m....]和b中，找到第k-m小的数字。好了，写递归函数吧。
     * 选择步长当m = k/2
     * <p>
     * 发散思维：如果m=1来进行呢？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double solution(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        if (length % 2 == 0) {
            return (helper(nums1, 0, nums2, 0, length / 2) + helper(nums1, 0, nums2, 0, length / 2 + 1)) / 2.0;
        }
        return helper(nums1, 0, nums2, 0, length / 2 + 1);
    }

    /**
     * 返回自m和n开始的第k小的元素
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * @param k
     * @return
     */
    public int helper(int[] nums1, int m, int[] nums2, int n, int k) {

        if (m >= nums1.length) {
            return nums2[n + k - 1];
        }
        if (n >= nums2.length) {
            return nums1[m + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[m], nums2[n]);
        }
        int p1 = m + k / 2 - 1;
        int p2 = n + k / 2 - 1;

        int mid1 = p1 < nums1.length ? nums1[p1] : Integer.MAX_VALUE;
        int mid2 = p2 < nums2.length ? nums2[p2] : Integer.MAX_VALUE;

        if (mid1 < mid2) {
            return helper(nums1, m + k / 2, nums2, n, k - k / 2);
        } else {
            return helper(nums1, m, nums2, n + k / 2, k - k / 2);
        }

    }

    @Test
    public void test_() {

        Assert.assertTrue(Math.abs(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}) - 2.5) < 1e-6);
        Assert.assertTrue(Math.abs(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) - 2) < 1e-6);
    }
}
