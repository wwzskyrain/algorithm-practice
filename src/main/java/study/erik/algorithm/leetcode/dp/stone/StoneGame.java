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

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : StoneGame.java, v 0.1 2021年06月16日 8:06 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StoneGame {

    @LetCodeCommit(title = "Stone Game")
    public boolean stoneGame(int[] piles) {
        return resolveWithDfs(piles);
//        return resolveWithDp(piles);
    }

    /**
     * dp思想我们也行到了，就是有个优化点
     * 1.本来我们用dp[i][j]表示先手的石子和，这样我们就必须处理后手的石子和。计算和是就要累加，而且是任意(i，j），所以划不来。
     * 2.优化石子和为石子差。
     * 3.dp[i][j] = 先手和后手的最大差值
     * 4.在for循环时，这种是从对角线，向斜上方平移，所以用一个l表示位移，而i就正常的从0到dp.length
     *
     * @param piles
     * @return
     */
    public boolean resolveWithDp(int[] piles) {
        int[][] dp = new int[piles.length][piles.length];
        // dp[i][j] = 先手和后手的最大差值

        for (int i = 0; i < dp.length; i++) {
            //l = 0 的情况
            dp[i][i] = piles[i];
        }
//        for (int l = 1; l < dp.length; l++) {
//            for (int i = 0; i < dp.length; i++) {
//                for (int j = i + l; j < dp[i].length; j++) {
//                    //神奇呀，这里的+-都是可以的。
////                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
//                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] + dp[i][j - 1]);
//                }
//            }
//        }
        // 这是一个新解法，对于这种斜三角的dp，竟然可以用两层循环就搞定。
        // 算了，这个优化不通用的。
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < dp.length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][piles.length - 1] > 0;
    }


    public boolean resolveWithDfs(int[] piles) {
        int delta = dfs(piles, 0, piles.length - 1, new HashMap<>());
        return delta > 0;
    }

    public int dfs(int[] piles, int l, int r, Map<Integer, Integer> memo) {
        Integer key = l * 500 + r;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (l == r) {
            memo.put(key, piles[l]);
            return piles[l];
        }
        int max = Integer.MIN_VALUE;
        max = Math.max(max, piles[l] - dfs(piles, l + 1, r, memo));
        max = Math.max(max, piles[r] - dfs(piles, l, r - 1, memo));
        memo.put(key, max);
        return max;
    }

    @Test
    public void test_() {
        Assert.assertEquals(except, stoneGame(piles));
    }

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {new int[]{5, 3, 4, 5}, true}
        };
    }

    @Parameter
    public int[] piles;
    @Parameterized.Parameter(1)
    public boolean except;

}