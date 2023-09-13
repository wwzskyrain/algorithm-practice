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
 * 日期：2023/9/13 ，时间：09:47
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Best_Time_to_Buy_and_Sell_Stock {

    @LetCodeCommit(title = "121.Best Time to Buy and Sell Stock",
            selfRemark = "一次买卖，太简单了。" +
                    "这里还有一个解法：")
    public int maxProfit(int[] prices) {
        int minLeft = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (minLeft > prices[i]) {
                minLeft = prices[i];
            } else {
                max = Math.max(max, prices[i] - minLeft);
            }
        }
        return max;
    }

    /**
     * 这个解法，很清晰，很基础，就是有点杀鸡用牛刀了。
     * 不过，这种分析方法，对该系列题目的解答很有好处。
     * @param prices
     * @return
     */
    public int maxProfitSolution2(int[] prices) {
        int n = prices.length;
        //max[i]表示在第i天卖出的最大收益。max[i] = prices[i] - (从0到i-1天的最小值)
        //那么 max中的最大值就是问题的解
        int[] max = new int[n];
        int ans = 0;
        int minLeft = prices[0];
        for (int i = 1; i < prices.length; i++) {
            //1.计算max[i]
            max[i] = prices[i] - minLeft;
            //2.更新ans
            ans = Math.max(ans, max[i]);
            //3.更新当前最小值
            minLeft = Math.min(minLeft, prices[i]);

        }
        return ans;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {5, ArrayUtils.buildArray("[7,1,5,3,6,4]")},
                {0, ArrayUtils.buildArray("[7,6,4,3,1]")}
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
