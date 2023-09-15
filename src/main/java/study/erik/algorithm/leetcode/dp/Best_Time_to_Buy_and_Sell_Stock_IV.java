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
 * 日期：2023/9/13 ，时间：11:05
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Best_Time_to_Buy_and_Sell_Stock_IV {

    @LetCodeCommit(title = "188. Best Time to Buy and Sell Stock IV",
            selfRemark = "已经第四题了，是第3题的泛化版本。" +
                    "所以这里就直接泛化第3题的解法。" +
                    "总结一下解法：" +
                    "主流的解法就是我们的这种，然而有人用了一个三维数组来表示状态，其中一个维度只有两个元素，既买卖，对应我们的buy数组和sell数组。" +
                    "还有一种更高深的解法：wqs法(Alien Trick)法，不做研究，不做了解。" +
                    "至此，股票题目的难度已经是hard的，" +
                    "但是后面还有两个变形题目，虽然都是medium，但是能更好地帮我们加深理解这个算法，所以请继续。")
    public int maxProfitSolution1(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[] buy = new int[k];
        int[] sell = new int[k];
        // buy[i] 表示buy0，buy1，buy2，...，分表表示在第j天时，购买了1次，购买了2次，购买了3次的最大收益，注意这里j不是i
        // 表达式为：buy[i] = max(buy[i], sell[i-1] - prices[i])，如果不理解可以回去再看看第3题的解法
        // 同理，sell[i]，表示在第j天时，第i+1次卖出动作后的收益最大值，同样注意，j不是i
        // 表达式为：sell[i] = max(sell[i], buy[i] + prices[i])

        // 初始化
        Arrays.fill(buy, -prices[0]);
        for (int j = 1; j < prices.length; j++) { //为了与注释保持一致，这里第一层循环变量用j
            buy[0] = Math.max(buy[0], -prices[j]);
            sell[0] = Math.max(sell[0], buy[0] + prices[j]);
            for (int i = 1; i < k; i++) { //为了与注释保持一致，这里第二层循环变量用i
                buy[i] = Math.max(buy[i], sell[i - 1] - prices[j]);
                sell[i] = Math.max(sell[i], buy[i] + prices[j]);
            }
        }
        return sell[k - 1];
    }

    /**
     * 由于题设的取值原因，1 <= k <= 100，1 <= prices.length <= 1000，所以一般情况，k不会大于prices.length的一半，
     * 而这个quickSolve就是针对这种非正常情况。
     * 当然非正常情况时也能用上面的通用解法。
     *
     * @param prices
     * @return
     */
    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, 2, ArrayUtils.buildArray("[2,4,1]")},
                {7, 2, ArrayUtils.buildArray("[3,2,6,5,0,3]")},

        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int k;
    @Parameterized.Parameter(2)
    public int[] prices;


    @Test
    public void test() {
        Assert.assertEquals(expect, maxProfitSolution1(k, prices));
    }

}
