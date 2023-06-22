/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : FindMinimumTimeToFinishAllJobs.java, v 0.1 2021年05月08日 9:34 上午 yueyi Exp $
 */
public class FindMinimumTimeToFinishAllJobs {

    @LetCodeCommit(no = 1723, title = "Find Minimum Time to Finish All Jobs",
            diff = "h",
            selfRemark = "这是一道很正宗的'动态规划(状态压缩)'。还有一个回溯法的解法，以后可以试一试的，因为里面有剪枝逻辑。"
                    + "这里我有一个猜想，那就是用greedy，优先分配大job给当前工作量最小的work.这是错误的")
    public int minimumTimeRequired(int[] jobs, int k) {

        int[] sum = new int[1 << jobs.length];
        for (int i = 1; i < sum.length; i++) {
            int trailingZeros = Integer.numberOfTrailingZeros(i);
            int j = (i - 1) & i;
            sum[i] = sum[j] + jobs[trailingZeros];
        }
        int[][] dp = new int[k][1 << jobs.length];
        //dp[i][s]表示i个worker来完成s工作集的最优解。
        for (int s = 0; s < dp[0].length; s++) {
            //s工作集都个编号为0的工人做了。
            dp[0][s] = sum[s];
        }

        for (int i = 1; i < k; i++) {
            for (int s = 0; s < sum.length; s++) {
                int minn = Integer.MAX_VALUE;
                for (int x = s; x > 0; x = (x - 1) & s) {
                    //这里x的变化，并不是从j依次到1，而是会跳跃的。
                    minn = Math.min(minn, Math.max(dp[i - 1][s - x], sum[x]));
                }
                dp[i][s] = minn;
            }
        }
        //k-1表示第k个工人了。k=0时也不表示没有工人，而是表示编号是0的工人。
        return dp[k - 1][(1 << jobs.length) - 1];
    }

    public int minimumTimeRequiredWithGreedy(int[] jobs, int k) {
        // 这是一个错误的思路
        // [5,5,4,4,4] k = 2 输出13，期待的是12
        // 13的解法 5+4+4 and 5+4
        // 12的解法 5+5 and 4+4+4
        Arrays.sort(jobs);
        int[] workers = new int[k];
        for (int i = jobs.length - 1; i >= 0; i--) {
            workers[0] += jobs[i];
            Arrays.sort(workers);
        }
        return workers[k - 1];
    }

    @Test
    public void test_1() {
        int[] jobs = new int[] {1, 2, 4, 7, 8};
        int k = 2;

        Assert.assertEquals(11, minimumTimeRequired(jobs, k));
    }

}