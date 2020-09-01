package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-09-01 09:00
 */
public class PredictTheWinner {

    @LetCodeCommit(title = "486. Predict the Winner", diff = "m",
            selfRemark = "游戏问题，也是博弈问题，就是求最值，用dp，用递归，用记忆法都可以")
    public boolean PredictTheWinner(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int[] sums = new int[nums.length];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = nums[i] + (i == 0 ? 0 : sums[i - 1]);
        }
//      dp[i][j] = max{ nums[i] + sum[i+1,j] - dp[i+1][j] , num[j] + sum[i,j-1] - dp[i,j-1] }
//      左边右边两种情况的较大值
        int[][] dp = new int[nums.length][nums.length];
        for (int l = 0; l < sums.length; l++) {
            for (int i = 0; i < sums.length - l; i++) {
                int j = i + l;
                if (i == j) {
                    dp[i][j] = nums[i];
                } else {
                    dp[i][j] = Math.max(nums[i] + sums[j] - sums[i] - dp[i + 1][j], nums[j] + sums[j - 1] - sums[i] + nums[i] - dp[i][j - 1]);
                }
            }
        }
        int first = dp[0][nums.length - 1];
        int second = sums[nums.length - 1] - first;
        return first >= second;
    }

    @Test
    public void test_solution_1() {
        Assert.assertFalse(PredictTheWinner(new int[]{1, 5, 2}));
    }

    @Test
    public void test_solution_2() {
        Assert.assertTrue(PredictTheWinner(new int[]{1, 5, 233, 7}));
    }

    @Test
    public void test_solution_3() {
        Assert.assertTrue(PredictTheWinner(new int[]{1, 1}));
    }

    @Test
    public void test_solution_4() {
        Assert.assertFalse(PredictTheWinner(new int[]{1, 3, 1}));
    }

}
