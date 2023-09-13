package study.erik.algorithm.leetcode.dp;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/13 ，时间：11:51
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {

    @LetCodeCommit(title = "309. Best Time to Buy and Sell Stock with Cooldown",
            selfRemark = "突然疑问：状态机的本质是什么？" +
                    "解法二我们继续用数组来尝试一下")
    public int maxProfit(int[] prices) {
        int rest = 0;
        int buyed = -prices[0];
        int selled = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            buyed = Math.max(buyed, rest - prices[i]);
            rest = Math.max(selled, rest);
            selled = buyed + prices[i];
            max = Math.max(max, selled);
        }
        return max;
    }

    /**
     * 这个解法没有用状态机，说实话，状态机虽然思路清晰，但是状态机的有效性在道理上我没有想通。
     * 我这里继续用数组来解题。
     * 很开心，至此数组法又攻下一城
     * @param prices
     * @return
     */
    public int maxProfitSolution2(int[] prices) {
        /*
        1.buy[i]表示，如果当前必须买入的话，其最大收益。
        2.buy[i] = Math.max(sell[i-2] - prices[i]  // i-1天是冷静日
            , sell[i-3] - prices[i]              // i-1、i-2都是冷静日
            , ...
            ,sell[0] - prices[i])                // 1到i-1都是冷静日

        3.sell表示，如果当天必须卖出的话，其最大收益
        4.sell[i] = Math.max(buy[i-1] + prices[i], buy[i-2] + prices[i], ... ,buy[0] + prices[i])
        * */
        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = -prices[0];
        int maxSellI_2 = 0;
        int maxBuyI_1 = buy[0];
        for (int i = 1; i < prices.length; i++) {
            buy[i] = maxSellI_2 - prices[i];
            sell[i] = maxBuyI_1 + prices[i];
            maxBuyI_1 = Math.max(maxBuyI_1, buy[i]);
            if (i >= 2) {
                //这一步可以泛化
                maxSellI_2 = Math.max(maxSellI_2, sell[i - 1]);
            }
        }
        return Math.max(maxSellI_2, sell[n - 1]);
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {3, ArrayUtils.buildArray("[1,2,3,0,2]")},
                {0, ArrayUtils.buildArray("[1]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxProfitSolution2(nums));
    }

}
