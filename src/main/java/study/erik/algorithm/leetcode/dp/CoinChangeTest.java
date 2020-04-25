package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-25 20:45
 */
public class CoinChangeTest {

    /**
     * title = Coin Change
     * url = https://leetcode.com/problems/coin-change/
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        return solution(coins, amount);
    }

    /**
     * 成绩：49和5
     * 小结：算法没有问题，就是待优化
     * 算法解析：动态规划，dp[i] 代表 问题，即最小硬币个数
     * 递归公式 dp[i] = min{ dp[i-c1]+1, dp[i-c2]+1,...,} c1,c2...表示硬币的面值;
     *
     * 注意：不要用贪心算法，不靠谱
     * 优化点：递归实现时，用了自底向上的方式，其中计算了很多不必要的dp[i].所以优化点
     *      时间优化点：
     *          1.用递归函数+备忘录，其中：递归可以减少不必要的dp[i]的计算，备忘录可以减少重复计算。
     *          2.还有一种自底向上的实现，以coin的面值为步长，从小到大计算dp[i]，但是这样没有做到减少不必要的dp[i]的计算
     *      空间优化：不会，难道不用这个备忘录，难道还有直接计算性质的。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int solution(int[] coins, int amount) {

        if (amount < 1) {
            return 0;
        }
        int[] mem = new int[amount + 1];
        for (int i = 0; i < mem.length; i++) {
            mem[i] = -1;
        }
        int minCoin = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            minCoin = Math.min(minCoin, coins[i]);
            if (coins[i] < mem.length) {
                mem[coins[i]] = 1;
            }
        }

        for (int i = minCoin; i < mem.length; i++) {
            if (mem[i] != -1) {
                continue;
            }
            int minNum = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                int remainAmount = i - coins[j];
                if (remainAmount < 0 || (remainAmount < mem.length && mem[remainAmount] == -1)) {
                    continue;
                }
                minNum = Math.min(minNum, mem[remainAmount] + 1);
            }
            mem[i] = minNum == Integer.MAX_VALUE ? -1 : minNum;
        }
        return mem[amount];
    }

//    public int find(int[] coins, int amount, int[] mem){
//
//    }


    @Test
    public void test_() {

        Assert.assertEquals(-1, coinChange(new int[]{2147483647}, 2));
        Assert.assertEquals(-1, coinChange(new int[]{2}, 1));
        Assert.assertEquals(0, coinChange(new int[]{1}, 0));
        Assert.assertEquals(3, coinChange(new int[]{1, 2, 5}, 11));
        Assert.assertEquals(-1, coinChange(new int[]{2}, 3));
    }

}
