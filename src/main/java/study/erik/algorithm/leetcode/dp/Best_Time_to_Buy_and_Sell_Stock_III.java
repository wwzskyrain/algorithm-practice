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
 * 日期：2023/9/13 ，时间：10:11
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Best_Time_to_Buy_and_Sell_Stock_III {

    /**
     * You may complete at most two transactions. 最多可以买卖两次
     * 动态规划？数组法
     *
     * @param prices 参见 {@link Best_Time_to_Buy_and_Sell_Stock#maxProfitSolution2(int[])}
     * @return
     */
    @LetCodeCommit(title = "123. Best Time to Buy and Sell Stock III",
            selfRemark = "这个就有点意思了。这个就不能简单的分析就能写的了。所以要系统的分析了。上dp")
    public int maxProfit(int[] prices) {
        //可以定义四个数组，分别是buy1，sell1，buy2，sell2
        //buy1[i]表示，在i天购买prices[i]的前提下的最大收益
        //sell1[i]表示，在i天卖出prices[i]的前提下的最大收益，注意，sell1之前必须完成buy1.
        //所以sell1[i] = prices[i] + max(buy1[0], buy1[1], ..., buy1[i-1])；
        //buy2[i]表示在i天购买prices[i](第二次购买)的前提下的最大收益，注意，buy2之前必须完成sell1。
        //所以buy2[i] = -prices[i] + max(sell1[0], sell1[0], ..., sell1[i-1])
        //sell2[i]表示在i天卖出prices[i](第二次卖出)的前提下的最大收益，注意，sell2之前必须完成buy2.
        //所以sell2[i] = prices[i] + max(buy2[0], buy2[0], ..., buy2[i-1])
        //总结，sell1、buy2、sell2的定义和取值表达式都很类似。
        //我们的目标是求sell1和sell2的最大值。
        //而且，在写代码的时候，可以不用真的定义这四个数组，就直接用四个变量，变量的定义直接定义为对应数组的当前最值。
        //变量的话，其表达式稍微变了一下而已，不再赘述。
        //我的这个解释要比官网的解释'https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/solutions/552695/mai-mai-gu-piao-de-zui-jia-shi-ji-iii-by-wrnt/'
        //更容易懂，更接近算法的本质。
        //这个解法，参见该系列题目1的第二个解法
        int buy1 = -prices[0];
        int sell1 = 0; //buy1和sell1两个动作都在同一天完成
        int buy2 = -prices[0]; //buy1/sell1/buy2都在同一天完成
        int sell2 = 0; //buy1/sell1/buy2/sell2都在同一天完成
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, prices[i] + buy1);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, prices[i] + buy2);
        }
        return sell2;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, 2, ArrayUtils.buildArray2Dimension("[[1,1],[2,1],[1,2],[2,2]]"), 2},
        });
    }


}
