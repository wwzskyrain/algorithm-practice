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
 * 日期：2023/9/13 ，时间：09:56
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Best_Time_to_Buy_and_Sell_Stock_II {

    @LetCodeCommit(title = "122. Best Time to Buy and Sell Stock II",
            selfRemark = "这个也很简单，累计每一个增区间")
    public int maxProfit(int[] prices) {
        int total = 0;
        for (int i = 1; i < prices.length; i++) {
            total += Math.max(prices[i] - prices[i - 1], 0);
        }
        return total;
    }


}
