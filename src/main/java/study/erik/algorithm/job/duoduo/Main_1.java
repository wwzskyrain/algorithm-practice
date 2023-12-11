package study.erik.algorithm.job.duoduo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.nowcoder.zijie.Mahjon;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/10 08:32
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_1 {

    @LetCodeCommit(title = "")
    public int maxProfit(int[] prices) {
        // buy[i]必须买入的最优质
        // buy[i] = max{sale[i-2],sale[i-3],...,sale[1],sale[0]} - prices[i]
        // sale[i]必须卖出
        // sale[i] = max{buy[i-1], buy[i-2],...,buy[1],buy[0]} + prices[i]
        int n = prices.length;
        int[] buy = new int[n];
        int[] sale = new int[n];
        int max_sale_2 = 0;
        int max_buy_1 = -prices[0];
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                buy[i] = -prices[i];
            } else {
                buy[i] = max_sale_2 - prices[i];
            }
            if (i < 1) {
                sale[i] = 0;
            } else {
                sale[i] = max_buy_1 + prices[i];
            }
            max_buy_1 = Math.max(max_buy_1, buy[i]);
            if (i < 1) {
                max_sale_2 = 0;
            } else {
                max_sale_2 = Math.max(max_sale_2, sale[i - 1]);
            }
        }
        return Math.max(max_sale_2, sale[n - 1]);
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, ArrayUtils.buildArray("[1,2,3,0,2]")},
                {0, ArrayUtils.buildArray("[1]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] prices;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxProfit(prices));
    }

}
