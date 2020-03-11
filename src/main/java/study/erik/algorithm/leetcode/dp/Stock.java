package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

public class Stock {

    /**
     * title = Best Time to Buy and Sell Stock
     * link = https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * 注意：只能进行一次交易，买卖一次，先买后买
     * 解法：1/2
     * 这个写法是抄的官网给的solution；解释如下：
     * 实际上是求后面一个数减去前面一个数的最大差值。
     * 因此，在遍历一遍的过程中，只需要维护一个最小值，和当前利润的最大值即可。时间复杂度 O(n)，空间复杂度 O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfitI1(int[] prices) {

        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;

    }


    /**
     * title = Best Time to Buy and Sell Stock
     * link = https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * 注意：只能进行一次交易，买卖一次，先买后买
     * 解法：2/2
     * 解法：定义p[i] 为 price[0...i] 的最大收益；那么p[length-1] 就是源问题的解。
     * 再定义 m[i]为price[0...i]的最小价格；
     * 递推公式：
     * p[i] = max( p[i-1], price[i] - m[i-1] )
     * 写成算法时，没必要额外使用一个p数组和m数组；
     * 因为p和m都是一维的，而且他们都是依次变化的，所以各自只需要一个变量就可以了
     * 如下写法，maxProfit就是p数组的代表，minPrice就是m数组的代表
     * <p>
     * leetcode结果：Runtime: 1 ms, faster than 80.79% 还是不错的
     *
     * @param prices
     * @return
     */
    public int maxProfitI2(int[] prices) {

        if (prices.length == 0) {
            return 0;
        }

        int maxProfit = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {

            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);

        }

        return maxProfit;
    }


    @Test
    public void test_max_profit_I_2() {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        Assert.assertEquals(5, maxProfitI2(prices));
    }

    /**
     * title = Best Time to Buy and Sell Stock II
     * link = https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
     * 注意：全程可以进行多次交易；即在每一个上升区间的起点买入、最高点卖出。
     * 所以，这个就简单了，只需要记录每一次'递增'区间的涨幅。
     *
     * @param prices
     * @return
     */
    public int maxProfitII(int[] prices) {

        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;

    }

    /**
     * title = Best Time to Buy and Sell Stock III
     * link = https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
     * 注意：全程最多两次交易，一次交易就是一次买入和卖出。仓位为1，即两次交易是有顺序的，第一次买卖完成后才能进行第二次买卖。
     * 解法一：（基本解法）遍历的过程，以i为分界点，来计算两边各进行一次交易的收益和，遍历时保存收益和最大。
     *
     * @param prices
     * @return
     */
    public int maxProfitIII1(int[] prices) {
        return 0;
    }

    /**
     * title = Best Time to Buy and Sell Stock IV
     * link = https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
     * 描述：最多进行k次交易的最大收益
     * 解法：这是网上流行的的一种解法；link = https://blog.csdn.net/yc1203968305/article/details/78435616
     * 在leetCode上验证过。
     * 解法解读：用了两个递推公式：
     * function:
     * 公式1：gain = prices[i]-prices[i-1]
     * 公式2：local[i][j] = max(global[i-1][j-1]+gain,local[i-1][j]+gain)
     * 公式3：global[i][j] = max(global[i-1][j],local[i][j])
     *
     * local[i][j] 表示在第[i]天卖出的情况下，最多进行j次交易，这时的最大交易额。
     * global[i][j] 表示i天最多进行j次交易的最大交易额，也是问题的解
     * 公式3很简单，全局最优就是local的最大值
     * 公式2有点扯淡，因为对global[i-1][j-1]+gain解释不清楚。网上还有另一个版本呢。
     * 我自己也有一个local[i][j]的递推公式，但是有点长，所以就不谢了。
     * 不过很希望能解释清楚这个公式2，因为这是一个思考方式，叫做'全局最优和局部最优'理论嗯。
     * 但是这个题目不能这样结束的。
     * 幸好在网上找到了一篇可以贯穿这个系列题目解法。
     * link = https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39038/kadanes-algorithm-since-no-one-has-mentioned-about-this-so-far-in-case-if-interviewer-twists-the-input
     * 他让我明白了一点，他详细分析了这个题目中的变量，并且建立了一个很细节的交易求最值的模型。
     * 有时候大道至简；那是应为化繁为简了。所以细节分析还是很重要的。
     * @param prices
     * @return
     */
    public int maxProfitIV1(int k, int[] prices) {
        if (prices.length == 0)
            return 0;
        int n = prices.length;
        //分为两种情况，当k>=n/2时，可以进行最大次数的交易。就是随便买，随便卖
        if (k >= n / 2) {
            int maxPro = 0;
            for (int i = 1; i < n; i++)
                maxPro += (prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0);
            return maxPro;
        }
        //第二种情况
        int[][] global = new int[n][k + 1];
        int[][] local = new int[n][k + 1];

        for (int i = 0; i <= k; i++) {
            local[0][i] = 0;
            global[0][i] = 0;
        }

        for (int i = 1; i < n; i++) {
            int gain = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                local[i][j] = Math.max(global[i - 1][j - 1], local[i - 1][j]) + gain;
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }

        return global[n - 1][k];
    }

    /**
     * 解法：我自己也有一套解法，不过还没以试验过，连代码都没来及写呢
     * 我已经记录在一张纸上了
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitIV2(int k, int[] prices) {

        if (prices.length == 0)
            return 0;
        int n = prices.length;
        //分为两种情况，当k>=n/2时，可以进行最大次数的交易。就是随便买，随便卖
        if (k >= n / 2) {
            int maxPro = 0;
            for (int i = 1; i < n; i++)
                maxPro += (prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0);
            return maxPro;
        }
        //第二种情况
        //待补充

        return 0;
    }

    @Test
    public void test_maxProfitIII1() {
        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int maxProfitIII2 = maxProfitIII1(prices);
        Assert.assertEquals(6, maxProfitIII2);
    }

    public int maxProfitIII2(int[] prices) {

        if (prices == null || prices.length < 2) {
            return 0;
        }
        int aBuy1 = prices[0];
        int aSell1 = 0;
        int aBuy2 = prices[0];
        int aSell2 = 0;

        for (int i = 1; i < prices.length; i++) {
            aBuy1 = Math.min(aBuy1, prices[i]);
            aSell1 = Math.max(aSell1, prices[i] - aBuy1);
            aBuy2 = Math.min(aBuy2, prices[i] - aSell1);
            aSell2 = Math.max(aSell2, prices[i] - aBuy2);
        }

        return aSell2;


    }

    @Test
    public void test_maxProfitIII2() {
        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int maxProfitIII2 = maxProfitIII2(prices);
        Assert.assertEquals(6, maxProfitIII2);
    }


    /**
     * title = Best Time to Buy and Sell Stock IV
     * 注意：III问题的泛化，最对进行k次交易
     * 使用二维dp吧
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {

        if (prices == null || prices.length < 2) {
            return 0;
        }
////        List<Integer> upZones = upZones(prices);
//        Collections.sort(upZones);
//        Collections.reverse(upZones);
//
//        int maxProfit = 0;
//        for (int i = 0; i < upZones.size() && i < k; i++) {
//            maxProfit += upZones.getKeys(i);
//        }

//        return maxProfit;
        return 0;
    }


}
