/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : MaximumGap.java, v 0.1 2021年12月20日 8:31 上午 yueyi Exp $
 */
public class MaximumGap {

    @LetCodeCommit(title = "164. Maximum Gap",
            diff = "m",
            selfRemark = "这里用到了桶排序——基数排序。")
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // m is the maximal number in nums
        int m = nums[0];
        for (int i = 1; i < nums.length; i++) {
            m = Math.max(m, nums[i]);
        }

        int exp = 1; // 1, 10, 100, 1000 ...
        int R = 10; // 10 digits

        int[] aux = new int[nums.length];

        while (m / exp > 0) { // Go through all digits from LSB to MSB
            int[] count = new int[R];

            for (int i = 0; i < nums.length; i++) {
                count[(nums[i] / exp) % 10]++;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            for (int i = nums.length - 1; i >= 0; i--) {
                aux[--count[(nums[i] / exp) % 10]] = nums[i];
            }

            for (int i = 0; i < nums.length; i++) {
                nums[i] = aux[i];
            }
            exp *= 10;
        }

        int max = 0;
        for (int i = 1; i < aux.length; i++) {
            max = Math.max(max, aux[i] - aux[i - 1]);
        }

        return max;
    }

    public int maximumGapWithBucket(int[] nums) {
        int n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        int d = Math.max(1, (max - min) / (n - 1));
        int buketSize = (max - min) / d + 1;

        int[][] buckets = new int[buketSize][2];
        for (int i = 0; i < buketSize; i++) {
            Arrays.fill(buckets[i], -1);
        }
        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - min) / d;
            if (buckets[idx][0] == -1) {
                buckets[idx][0] = buckets[idx][1] = nums[i];
            } else {
                buckets[idx][0] = Math.min(buckets[idx][0], nums[i]);
                buckets[idx][1] = Math.max(buckets[idx][1], nums[i]);
            }
        }

        int ret = 0;
        int pre = -1;
        for (int i = 0; i < buketSize; i++) {
            if (buckets[i][0] == -1) {
                continue;
            }
            if (pre != -1) {
                //本桶的最小值 - 上一个桶(不是i-1，是pre)的最大值。
                ret = Math.max(ret, buckets[i][0] - buckets[pre][1]);
            }
            pre = i;
        }
        return ret;
    }

}