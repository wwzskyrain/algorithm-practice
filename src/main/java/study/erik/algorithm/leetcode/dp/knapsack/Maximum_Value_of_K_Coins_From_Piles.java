package study.erik.algorithm.leetcode.dp.knapsack;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/17 22:09
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Maximum_Value_of_K_Coins_From_Piles {

    @LetCodeCommit(title = "2218. Maximum Value of K Coins From Piles",
            postLink = "https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles/description/",
            selfRemark = "哈哈，第一版提交ac了，然后我要优化一下代码。")
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[][] dp = new int[piles.size()][k + 1];
        List<List<int[]>> allValueAndWeight = new ArrayList<>();
        for (int i = 0; i < piles.size(); i++) {
            List<Integer> pile = piles.get(i);
            List<int[]> vAndW = new ArrayList<>();
            int sum = 0;
            for (int j = 0; j < pile.size(); j++) {
                sum += pile.get(j);
                vAndW.add(new int[]{sum, j + 1});
            }
            allValueAndWeight.add(vAndW);
        }

        for (int i = 0; i < allValueAndWeight.size(); i++) {
            List<int[]> valueAndWeight = allValueAndWeight.get(i);
            for (int j = 0; j < k + 1; j++) {
                int maxValue = i == 0 ? 0 : dp[i - 1][j];
                for (int l = 0; l < valueAndWeight.size(); l++) {
                    int[] v_w = valueAndWeight.get(l);
                    int v = v_w[0];
                    int w = v_w[1];
                    if (j - w >= 0) {
                        int curValue = (i == 0 ? 0 : dp[i - 1][j - w]) + v;
                        maxValue = Math.max(maxValue, curValue);
                    }
                }
                dp[i][j] = maxValue;
            }
        }
        return dp[piles.size() - 1][k];
    }


    // 代码清爽了。
    public int maxValueOfCoinsSolution2(List<List<Integer>> piles, int k) {
        int[] dp = new int[k + 1];
        int totalCoin = 0;
        for (List<Integer> pile : piles) {
            //优化点1：直接在piles的迭代中操作，不用开数组来先整理pile了。
            //优化点2：dp改为一位数组，是真正的一维数组。不用附加临时数组。
            for (int i = 1; i < pile.size(); i++) {
                pile.set(i, pile.get(i) + pile.get(i - 1));
            }
            totalCoin += pile.size();
            int maxJ = Math.min(totalCoin, k);
            for (int j = maxJ; j >= 0; j--) {
                //注意点：j是从大到小。为什么？因为这跟“多重背包问题还是不一样的”，这更像是01背包问题，每次只能从第i组中选，不能受第i组中的影响
                //当然，如果用循环数组或者临时数组，也是可以正序来的。
                for (int i = 0; i < pile.size() && i < j; i++) {
                    dp[j] = Math.max(dp[j], dp[j - i - 1] + pile.get(i));
                }
            }
        }
        return dp[k];
    }

    public int maxValueOfCoinsSolution3(List<List<Integer>> piles, int k) {
        int[][] dp = new int[2][k + 1];
        int dpIdx = 0;
        int totalCoin = 0;
        for (List<Integer> pile : piles) {
            //优化点1：直接在piles的迭代中操作，不用开数组来先整理pile了。
            //优化点2：dp改为一位数组，是真正的一维数组。不用附加临时数组。
            for (int i = 1; i < pile.size(); i++) {
                pile.set(i, pile.get(i) + pile.get(i - 1));
            }
            totalCoin += pile.size();
            int maxJ = Math.min(totalCoin, k);

            int lastDpIdx = dpIdx % 2;
            dpIdx++;
            int curDpIdx = dpIdx % 2;

            for (int j = 0; j <= maxJ; j++) {
                //这里看，就是j从小到大。配合循环数组，没问题的。但是形式上没有真正的一维数组那么精巧。
                int max = dp[lastDpIdx][j];
                for (int i = 0; i < pile.size() && i < j; i++) {
                    max = Math.max(max, dp[lastDpIdx][j - i - 1] + pile.get(i));
                }
                dp[curDpIdx][j] = max;
            }
        }
        return dp[dpIdx % 2][k];
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {494, ArrayUtils.buildList2Dimension("[[37,88],[51,64,65,20,95,30,26],[9,62,20],[44]]"), 9},
                {101, ArrayUtils.buildList2Dimension("[[1,100,3],[7,8,9]]"), 2},
                {706, ArrayUtils.buildList2Dimension("[[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]]"), 7},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public List<List<Integer>> piles;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
//        Assert.assertEquals(expect, maxValueOfCoins(piles, k));
//        Assert.assertEquals(expect, maxValueOfCoinsSolution2(piles, k));
        Assert.assertEquals(expect, maxValueOfCoinsSolution3(piles, k));
    }

}
