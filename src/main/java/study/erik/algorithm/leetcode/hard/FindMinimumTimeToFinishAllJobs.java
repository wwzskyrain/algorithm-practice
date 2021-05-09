/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FindMinimumTimeToFinishAllJobs.java, v 0.1 2021年05月08日 9:34 上午 yueyi Exp $
 */
public class FindMinimumTimeToFinishAllJobs {

    @LetCodeCommit(no = 1723, title = "Find Minimum Time to Finish All Jobs",
            diff = "h",
            selfRemark = "这是一道很正宗的'动态规划(状态压缩)'。还有一个回溯法的解法，以后可以试一试的，因为里面有剪枝逻辑。")
    public int minimumTimeRequired(int[] jobs, int k) {


        int[] sum = new int[1 << jobs.length];
        for (int i = 1; i < sum.length; i++) {
            int trailingZeros = Integer.numberOfTrailingZeros(i);
            int j = (i - 1) & i;
            sum[i] = sum[j] + jobs[trailingZeros];
        }
        int[][] dp = new int[k][1 << jobs.length];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = sum[i];
        }

        for (int i = 1; i < k; i++) {
            for (int j = 0; j < sum.length; j++) {
                int minn = Integer.MAX_VALUE;
                for (int x = j; x > 0; x = (x - 1) & j) {
                    //这里x的变化，并不是从j依次到1，而是会跳跃的。
                    minn = Math.min(minn, Math.max(dp[i - 1][j - x], sum[x]));
                }
                dp[i][j] = minn;
            }
        }
        return dp[k - 1][(1 << jobs.length) - 1];
    }

    @Test
    public void test_1() {
        int[] jobs = new int[] {1, 2, 4, 7, 8};
        int k = 2;

        Assert.assertEquals(11, minimumTimeRequired(jobs, k));
    }

}