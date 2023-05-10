/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author yueyi
 * @version : ConstrainedSubsequenceSum.java, v 0.1 2023年05月10日 22:23 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ConstrainedSubsequenceSum {

    @LetCodeCommit(title = "1425. Constrained Subsequence Sum",
            diff = "h",
            selfRemark = "这个题目似乎不难，用一维dp就能搞定。而且也有效，而且我们也写出来top-bottom的解法。在constrainedSubsetSum2"
                    + "但是会超时。看看lee的解法吧。"
                    + "简直对题意'曲解'到了令人发指的地步，然后再接合k滑动窗口最大值的解法，竟然用O(n)个解了。"
                    + "存在这样优秀的解法，怪不得我们会超时。")
    public int constrainedSubsetSum(int[] A, int k) {
        int res = A[0];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < A.length; ++i) {
            A[i] += !q.isEmpty() ? q.peek() : 0;
            res = Math.max(res, A[i]);
            while (!q.isEmpty() && A[i] > q.peekLast()) {q.pollLast();}
            if (A[i] > 0) {q.offer(A[i]);}
            if (i >= k && !q.isEmpty() && q.peek() == A[i - k]) {q.poll();}
        }
        return res;
    }

    public int constrainedSubsetSum2(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        //dp[i]表示已子数组[i..n]的最优解
        Arrays.fill(dp, -1);
        dfs(nums, 0, k, dp);
        int max = Integer.MIN_VALUE;
        for (int i : dp) {
            max = Math.max(max, i);
        }
        return max;
    }

    public int dfs(int[] nums, int index, int k, int[] dp) {
        int length = nums.length;
        if (index == length) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int max = nums[index];
        int sum = nums[index];
        for (int i = index; i < index + k && i < length; i++) {
            int subMax = dfs(nums, i + 1, k, dp);
            max = Math.max(max, sum + subMax);
        }
        dp[index] = max;
        return max;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   k;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{ArrayUtils.buildArray("[-5266,4019,7336,-3681,-5767]"), 2, 11355},
                {ArrayUtils.buildArray("[-6,5,-3]"), 1, -1},
                //{ArrayUtils.buildArray("[-1,-2,-3]"), 1, -1},
                //{ArrayUtils.buildArray("[10,-2,-10,-5,20]"), 2, 23},
                //{ArrayUtils.buildArray("[10,2,-10,5,20]"), 2, 37},
                //{ArrayUtils.buildArray("[10,2,-10,5,20]"), 2, 37},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, constrainedSubsetSum(nums, k));
    }
}