/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumProductSubarray.java, v 0.1 2021年11月19日 12:58 下午 yueyi Exp $
 */
public class MaximumProductSubarray {

    @LetCodeCommit(title = "Maximum Product Subarray",
            selfRemark = "整数连乘的特性之一：越多数来乘，结果越大。"
                    + "针对这个题目的一个结论：在不考虑含0元素的情况下，最大值只能是[start,...,s1]，或者[s2...,end]，其中s1是右边第一个负数，s2是左边第一个负数。"
                    + "换一个说法：假设最大子数组是[s,e]，那么s=0 或者 e=nums.length。"
                    + "证明：假设最初是[s,e], s=e，那么"
                    + " 1. 如果两边任何一边是整数，就吃进来变成新的[s`,e`]。"
                    + " 2. 两边都是负数，则把两遍都吃进来，变成新的[s`,e`]。"
                    + " 3. 由12吃下来，就至少有一边到头了。"
                    + " 4. 结论：分别从两头连乘下来，记录其中的最值即可"
                    + "由这些就够可以写代码了。当然我们还可以再精确分析下去，得到这些结论"
                    + " A. 如果偶数个负数，就全部连乘起来。"
                    + " B. 如果奇数个负数，则max(从左往右乘到最后一个负数，从右往左连乘到第一个负数)."
                    + "按照AB的结论来写代码也可以的。"
                    + "不，还要考虑0的问题呢，而且写起来啰嗦。为什么啰嗦，因为分析的太细了，就要好多代码来搞。"
                    + "所以，分析题目要简单为好，不一定要细致入微。")
    public int maxProduct(int[] nums) {

        int maxPrefix = 0;
        int maxSuffix = 0;
        int max = nums[0];
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            maxPrefix = (maxPrefix == 0 ? 1 : maxPrefix) * nums[i];
            maxSuffix = (maxSuffix == 0 ? 1 : maxSuffix) * nums[l - i - 1];
            max = Math.max(max, Math.max(maxPrefix, maxSuffix));
        }
        return max;
    }

    public int maxProduct1(int[] nums) {
        int l = nums.length;
        if (l == 1) {
            return nums[0];
        }
        int first = -1;
        int last = l;
        int numNegative = 0;
        for (int i = 0; i < l; i++) {
            int n = nums[i];
            if (n < 0) {
                last = i;
                numNegative++;
            }
            n = nums[l - i - 1];
            if (n < 0) {
                first = l - i - 1;
            }
        }
        if (numNegative % 2 == 0) {
            int r = 1;
            for (int n : nums) {
                r *= n;
            }
            return r;
        } else {
            int r1 = 1;
            int r2 = 1;
            first++;
            last--;
            while (first < l) {
                r1 *= nums[first++];
            }
            while (last >= 0) {
                r2 *= nums[last--];
            }
            return Math.max(r1, r2);
        }
    }

    @Test
    public void test_() {
        Assert.assertEquals(6, maxProduct1(new int[] {2, 3, -2, 4}));
    }

    @Test
    public void test_1() {
        Assert.assertEquals(-2, maxProduct1(new int[] {-2}));
    }

}