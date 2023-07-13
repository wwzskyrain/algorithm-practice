/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.stone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : StoneGameVIII.java, v 0.1 2021年06月19日 10:50 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1872. Stone Game VIII",
        diff = "h",
        selfRemark = "这个就有意思了。"
                + "这个好像是博弈，有点像下象棋，我还想到要阿尔法-bate剪枝呢。"
                + "然而是多想了。"
                + "按照题意思路，好像是现有解[0]，再有后面的解[1]/解[2]，"
                + "其实不是的。这个想法本身就搞清楚。"
                + "先有左边的'score difference'，后有左边的'score difference'是正确的。"
                + "但是不是先有左边的'maximize score difference'，后有右边'maximize score difference'。"
                + "因为有博弈的角度存在，所以最优解是从右往左的。"
                + "设dp[i]表示先手从i开始的最优解——注意，不一定就抓[0,...,i]，而是可以抓[0,...,i],[0,...,i+1],[0,...,i+2]....[0,...,n-1]"
                + "则dp[i] = max(dp[i+1], sum(i) - dp[i+1]."
                + "即，要么就是抓[0,...,i]，此时的解=sum(i) - dp[i+1]。"
                + "要么往后抓抓，也就是dp[i+1]了。"
                + "还可以参考： https://leetcode.com/problems/stone-game-viii/solutions/1224872/top-down-and-bottom-up/")
public class StoneGameVIII {

    public int stoneGameVIII(int[] stones) {
        Integer[] dp = new Integer[100001];
        for (int i = 1; i < stones.length; i++) {
            stones[i] += stones[i - 1];
        }
        return dfs(stones, 1, dp);
    }

    public int dfs(int[] preSum, int i, Integer[] dp) {
        if (dp[i] == null) {
            if (i == preSum.length - 1) {
                dp[i] = preSum[i];
            } else {
                dp[i] = Math.max(dfs(preSum, i + 1, dp), preSum[i] - dfs(preSum, i + 1, dp));
            }
        }
        return dp[i];
    }

    public int stoneGameVIIIWithBottomToUp(int[] stones) {
        int n = stones.length;
        Integer[] dp = new Integer[n];
        for (int i = 1; i < stones.length; i++) {
            stones[i] += stones[i - 1];
        }
        int lastIndex = n - 1;
        dp[lastIndex] = stones[lastIndex];
        for (int i = dp.length - 2; i > 0; i--) {
            dp[i] = Math.max(stones[i] - dp[i + 1], dp[i + 1]);
        }
        return dp[1];
    }

    public int stoneGameVIIIWithBottomToUp2(int[] stones) {
        int n = stones.length;
        Integer[] dp = new Integer[n];
        for (int i = 1; i < stones.length; i++) {
            stones[i] += stones[i - 1];
        }
        int max = stones[n - 1];
        for (int i = dp.length - 2; i > 0; i--) {
            max = Math.max(max, stones[i] - max);
        }
        return max;
    }

    @Parameter
    public int[] stones;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {-1, 2, -3, 4, -5}, 5},
                {new int[] {7, -6, 5, 10, 5, -2, -6}, 13},
                {new int[] {-10, -12}, -22}
        };
    }

    @Test
    public void test() {
        //Assert.assertEquals(expect, stoneGameVIII(stones));
        //Assert.assertEquals(expect, stoneGameVIIIWithBottomToUp(stones));
        Assert.assertEquals(expect, stoneGameVIIIWithBottomToUp2(stones));
    }
}