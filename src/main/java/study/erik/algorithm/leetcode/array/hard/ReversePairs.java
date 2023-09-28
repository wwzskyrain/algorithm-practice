/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : ReversePairs.java, v 0.1 2022年02月07日 2:21 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ReversePairs {

    @LetCodeCommit(title = "Reverse Pairs")
    public int reversePairs(int[] nums) {
        return solutionWithMergeSortII(nums, 0, nums.length - 1);
    }

    // 使用系统提供的排序。前提是排序和计数分离了
    public int solutionWithMergeSortI(int[] nums, int l, int h) {

        if (l >= h) {
            return 0;
        }
        int mid = l + (h - l) / 2;
        int count = 0;
        count += solutionWithMergeSortI(nums, l, mid);
        count += solutionWithMergeSortI(nums, mid + 1, h);
        for (int i = l, j = mid + 1; i < mid + 1; i++) {
            while (j < h + 1 && nums[i] / 2.0 > nums[j]) {
                j++;
            }
            count += j - (mid + 1);
        }
        Arrays.sort(nums, l, h + 1);
        return count;
    }

    // 手写merge的逻辑。前提是排序和计数分离了
    // 比使用系统System.sort要快一倍呢
    public int solutionWithMergeSortII(int[] nums, int l, int h) {

        if (l >= h) {
            return 0;
        }
        int mid = l + (h - l) / 2;
        int count = 0;
        count += solutionWithMergeSortII(nums, l, mid);
        count += solutionWithMergeSortII(nums, mid + 1, h);

        int[] temp = new int[h - l + 1];
        int i = l;
        int j = mid + 1;
        int p = j;
        int k = 0;
        while (i <= mid) {
            //先计数
            while (p <= h && nums[i] / 2.0 > nums[p]) {
                p++;
            }
            count += p - (mid + 1);

            //再合并
            while (j <= h && nums[i] >= nums[j]) {
                temp[k++] = nums[j++];
            }
            temp[k++] = nums[i++];
        }
        while (j <= h) {
            temp[k++] = nums[j++];
        }
        System.arraycopy(temp, 0, nums, l, temp.length);
        return count;
    }

    public int solutionWithTowLoop(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] / 2.0 > nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,3,2,3,1]"), 2},
                {ArrayUtils.buildArray("[2,4,3,5,1]"), 3},
                {ArrayUtils.buildArray("[2147483647,2147483647,2147483647,2147483647,2147483647,2147483647]"), 0}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, reversePairs(nums));
    }

}