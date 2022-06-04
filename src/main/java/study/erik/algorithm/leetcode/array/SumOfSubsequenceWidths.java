/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : SumOfSubsequenceWidths.java, v 0.1 2022年06月04日 10:31 yueyi Exp $
 */
public class SumOfSubsequenceWidths {

    @LetCodeCommit(title = "891. Sum of Subsequence Widths",
            diff = "h",
            selfRemark = "这是hard题目，却都有巧姐."
                    + "改答案copy自leet大神的： https://leetcode.com/problems/sum-of-subsequence-widths/discuss/161267/JavaC%2B%2BPython-Sort-and-One-Pass"
                    + "这种思路就是全局求解，而不能局限于逐个")
    public int sumSubseqWidths(int[] nums) {
        long res = 0;
        int n = nums.length;
        Arrays.sort(nums);
        long M = 1000000007;
        long c = 1;
        for (int i = 0; i < n; i++, c = (c * 2) % M) {
            res = (res + nums[i] * c - nums[n - i - 1] * c) % M;
        }
        return (int) (res % M);
    }
}