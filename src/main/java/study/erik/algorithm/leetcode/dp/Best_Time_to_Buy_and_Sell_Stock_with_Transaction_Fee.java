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
 * 日期：2023/9/13 ，时间：14:08
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee {

    @LetCodeCommit(title = "714. Best Time to Buy and Sell Stock with Transaction Fee",
            selfRemark = "又是不限交易次数，不过这时候每次交易都需要费用。" +
                    "开心，至此，我们把买股票系列都用同一种方法解决了。")
    public int maxProfit(int[] prices, int fee) {
        /*
        1.  buy[i]表示，如果第i天必须买入的话，其最大收益
        2.  buy[i] = Math.max(sell[i-1] - price, sell[i-2] - price, ... , sell[0] - price)
            优化一下：buy[i] = maxSellI_1 - price
        3.  sell[i]表示，如果第i天必须卖出的话，其最大收益
        4.  sell[i] = Math.max(buy[i-1] + price - fee, buy[i-2] + price - fee, ... , buy[0] + price - fee)
            优化一下：sell[i] = maxBuyI_1 + price - fee
        * */

        int n = prices.length;
        int[] buy = new int[n];
        int[] sell = new int[n];
        buy[0] = -prices[0];
        int maxSellI_1 = 0;
        int maxBuyI_1 = buy[0];
        for (int i = 1; i < prices.length; i++) {
            sell[i] = maxBuyI_1 + prices[i] - fee;
            buy[i] = maxSellI_1 - prices[i];
            // 更新最值
            maxSellI_1 = Math.max(maxSellI_1, sell[i]);
            maxBuyI_1 = Math.max(maxBuyI_1, buy[i]);
        }
        return maxSellI_1;
    }


    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {8, ArrayUtils.buildArray("[1,3,2,8,4,9]"), 2},
                {6, ArrayUtils.buildArray("[1,3,7,5,10,3]"), 3}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] prices;
    @Parameterized.Parameter(2)
    public int fee;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxProfit(prices, fee));
    }

}
