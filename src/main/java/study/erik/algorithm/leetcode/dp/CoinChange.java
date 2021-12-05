package study.erik.algorithm.leetcode.dp;

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
 * @author erik.wang
 * @date 2020-04-25 20:45
 */
@RunWith(Parameterized.class)
public class CoinChange {

    @LetCodeCommit(title = "322. Coin Change",
            selfRemark = "这里才是硬币找零的正解。需要注意很多的case。"
                    + "注意这里解法1和解法2最大的区别不在于是以当前i去更新i+coin还是用i-coin来更新当前i，"
                    + "即不在于i的更新时之前还是之后，而在于两重循环的次序——coins是内循环还是外循环。"
                    + "这一点很重要，虽然在这个题中没有体现出差别，在CoinChange2中就很重要了，"
                    + "选错内外循环的话是会找不到正确解答的，因为那里是一个组合。")
    public int coinChange(int[] coins, int amount) {
        return solution2(coins, amount);
    }

    public int solution1(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, -1);
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == -1) {
                //不能找零，所以也不能用作跳板继续为后续的找零任务迭代
                continue;
            }
            for (int coin : coins) {
                // 注意这个i+coin<0的case，因为index会超姐
                if (i + coin >= dp.length || i + coin < 0) {
                    continue;
                }
                if (dp[i + coin] == -1) {
                    // 第一次
                    dp[i + coin] = dp[i] + 1;
                } else {
                    dp[i + coin] = Math.min(dp[i + coin], dp[i] + 1);
                }
            }
        }
        return dp[amount];
    }

    public int solution2(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, -1);
        for (int coin : coins) {
            for (int i = coin; i < dp.length; i++) {
                int preDp = dp[i - coin];
                if (preDp == -1) {
                    continue;
                }
                if (dp[i] == -1) {
                    dp[i] = dp[i - coin] + 1;
                } else {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount];
    }

    @Parameter
    public int[] coins;
    @Parameter(1)
    public int   amount;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,5]"), 11, 3},
                {ArrayUtils.buildArray("[2]"), 3, -1},
                {ArrayUtils.buildArray("[1]"), 0, 0},
                {ArrayUtils.buildArray("[1]"), 1, 1},
                {ArrayUtils.buildArray("[1,2147483647]"), 2, 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, coinChange(coins, amount));
    }

}
