package study.erik.algorithm.leetcode.hard.array;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/8/4 ，时间：11:06
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class KthSmallestProductOfTwoSortedArrays {

    @LetCodeCommit(title = "2040. Kth Smallest Product of Two Sorted Arrays")
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long left = (long) 0;
        long right = (long) 1e10 + 1;
        long negativeProductSize = 0;
        List<Integer> A1 = new ArrayList<>(); // minus
        List<Integer> A2 = new ArrayList<>(); // plus
        List<Integer> B1 = new ArrayList<>(); // minus
        List<Integer> B2 = new ArrayList<>(); // plus
        long s = 1;

        for (int i = 0; i < nums1.length; i++) {
            if (nums1[i] < 0) {
                A1.add(nums1[i] * -1);
            }

            if (nums1[i] >= 0) {
                A2.add(nums1[i]);
            }
        }

        for (int i = 0; i < nums2.length; i++) {
            if (nums2[i] < 0) {
                B1.add(nums2[i] * -1);
            }

            if (nums2[i] >= 0) {
                B2.add(nums2[i]);
            }
        }

        Collections.sort(A1);
        Collections.sort(B1);


        negativeProductSize = A1.size() * B2.size() + A2.size() * B1.size();

        if (k > negativeProductSize) {
            k -= negativeProductSize;
            s = 1;
        } else {
            //倒过来找
            k = negativeProductSize - k + 1;
            //swap(B1,B2)
            List<Integer> temp = B1;
            B1 = B2;
            B2 = temp;
            s = -1;
        }


        while (left < right) {
            long mid = left + (right - left) / 2;

            if (k <= check(mid, A1, B1) + check(mid, A2, B2)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left * s;
    }

    private long check(long value, List<Integer> nums1, List<Integer> nums2) {
        // nums1和nums2都是正整数
        long total = 0;
        int j = nums2.size() - 1;

        for (Integer n1 : nums1) {
            while (j >= 0 && (long) n1 * (long) nums2.get(j) > value) {
                j--;
            }

            total += j + 1;
        }

        return total;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {8, ArrayUtils.buildArray("[2,5]"), ArrayUtils.buildArray("[3,4]"), 2},
                {0, ArrayUtils.buildArray("[-4,-2,0,3]"), ArrayUtils.buildArray("[2,4]"), 6},
                {-6, ArrayUtils.buildArray("[-2,-1,0,1,2]"), ArrayUtils.buildArray("[-3,-1,2,4,5]"), 3},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums1;
    @Parameterized.Parameter(2)
    public int[] nums2;
    @Parameterized.Parameter(3)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, kthSmallestProduct(nums1, nums2, k));
    }

}
