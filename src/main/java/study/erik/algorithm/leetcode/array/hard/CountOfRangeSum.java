/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CountOfRangeSum.java, v 0.1 2022年01月11日 11:31 下午 yueyi Exp $
 */
public class CountOfRangeSum {

    int count = 0;
    int lower;
    int upper;

    @LetCodeCommit(title = "327. Count of Range Sum")
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        long[] temp = new long[nums.length + 1];
        sum[0] = 0;
        this.lower = lower;
        this.upper = upper;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + (long) nums[i - 1];
        }

        mergesort(sum, 0, sum.length - 1, temp);
        return count;
    }

    private void mergesort(long[] sum, int start, int end, long[] temp) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergesort(sum, start, mid, temp);
        mergesort(sum, mid + 1, end, temp);
        merge(sum, start, mid, end, temp);
    }

    private void merge(long[] sum, int start, int mid, int end, long[] temp) {
        int right = mid + 1;
        int index = start;
        int low = mid + 1, high = mid + 1;
        for (int left = start; left <= mid; left++) {
            // 本来应该是这样，只需要一个right指针就行。但是单指针的效率太低了，其结束条件需要是right<=end
            // 改成双指针之后，能很快找到两个边界，然后边界组成的区间就是本次count。
            // 不用双指针就超时，亲测。
            // 双指针之间是没关系的，这样每个指针，比如low，在整个left的遍历中，其实就走了一遍mid+1到ends
            while (low <= end && sum[low] - sum[left] < lower) {
                low++;
            }
            while (high <= end && sum[high] - sum[left] <= upper) {
                high++;
            }
            while (right <= end && sum[right] < sum[left]) {
                temp[index++] = sum[right++];
            }
            temp[index++] = sum[left];
            count += high - low;
        }
        while (right <= end) {
            temp[index++] = sum[right++];
        }

        for (int i = start; i <= end; i++) {
            sum[i] = temp[i];
        }
    }
}