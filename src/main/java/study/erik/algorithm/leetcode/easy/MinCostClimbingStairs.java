package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import static study.erik.algorithm.util.QuestionType.DP;

/**
 * @author erik.wang
 * @date 2020-08-04 09:41
 */
public class MinCostClimbingStairs {

    @LetCodeCommit(title = "Min Cost Climbing Stairs", time = 100, space = 28, diff = "h", types = DP)
    public int minCostClimbingStairs(int[] cost) {

        //dp[i]表示到达i处，所需要的最小花费；dp[i] = min{dp[i-1], dp[i-2]} + cost[i];
        int dp0 = cost[0];
        int dp1 = cost[1];
        int dp = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp = Math.min(dp0, dp1) + (i == cost.length ? 0 : cost[i]);
            dp0 = dp1;
            dp1 = dp;
        }
        return dp;
    }

    @Test
    public void test_case_1() {
        int[] cost = {10, 15, 20};
        Assert.assertEquals(15, minCostClimbingStairs(cost));
    }

    @Test
    public void test_case_2() {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        Assert.assertEquals(6, minCostClimbingStairs(cost));
    }

}
