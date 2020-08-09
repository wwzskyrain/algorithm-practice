package study.erik.algorithm.nowcoder.zijie;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-08-05 16:04
 */
public class 交替拿牌 {


    public int maxValue(int[] cards, int m) {
        return doMaxValue(cards, m);
    }

    public int maxValue = 0;

    /**
     * @param values
     * @param m
     * @param i      当前待被取的card下标
     * @param v1     当前a拿到的值
     * @param v2     当期b拿到的值
     * @param aTurn
     * @return 额，回溯法是不行的，因为不是最优策略。
     */
    public void backtrack(int[][] values, int m, int i, int v1, int v2, boolean aTurn) {
        if (i >= values.length) {
            maxValue = Math.max(maxValue, v1);
            return;
        }

        for (int j = 0; j < m; j++) {
            if (aTurn) {
                backtrack(values, m, i + j + 1, v1 + values[i][j], v2, false);
            } else {
                backtrack(values, m, i + j + 1, v1, v2 + values[i][j], true);
            }
        }
    }

    /**
     * 神奇的dp啊，竟然能够用在这种博弈场景。
     * 其实，这是一个一维dp的场景，其实这不难的。
     * 原题链接(PS:其实是一个面经，而且我猜肯定是牛客网上的一个题目，因为输入输出的风格)：https://ac.nowcoder.com/discuss/205655
     *
     * @param cards
     * @param m
     * @return
     */
    public int doMaxValue(int[] cards, int m) {

        /**
         * dp[i]表示从i开始取，先手最大解，即原问题的定义
         * dp[i] = max( sum[i,j] + subSum[i+j+1] - dp[i+j+1] ), 其中j=(0,1,2,...,m-1)
         */
        int[] dp = new int[cards.length];
        int[] subSum = new int[cards.length];
        for (int i = cards.length - 1; i >= 0; i--) {
            if (i == cards.length - 1) {
                subSum[i] = cards[i];
            } else {
                subSum[i] = cards[i] + subSum[i + 1];
            }
        }


        for (int i = cards.length - 1; i >= 0; i--) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < m; j++) {
                if (i + j < subSum.length) {
                    int sumIJ;
                    int leftValue;
                    if (i + j + 1 >= subSum.length) {
                        sumIJ = subSum[i];
                        leftValue = 0;
                    } else {
                        sumIJ = subSum[i] - subSum[i + j + 1];
                        leftValue = subSum[i + j + 1] - dp[i + j + 1];
                    }
                    max = Math.max(sumIJ + leftValue, max);
                } else {
                    break;
                }
            }
            dp[i] = max;
        }
        return dp[0];
    }

    @Test
    public void test_case_1() {
        Assert.assertEquals(2, maxValue(new int[]{1, 1, 1, 1}, 2));
    }

    @Test
    public void test_case_2() {
        Assert.assertEquals(6, maxValue(new int[]{3, -4, 1, 1, 7}, 2));
    }

}
