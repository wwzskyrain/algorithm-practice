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
 * @version : StoneGame.java, v 0.1 2021年06月16日 8:06 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StoneGame {

    @LetCodeCommit(title = "Stone Game")
    public boolean stoneGame(int[] piles) {
        return resolveWithDp(piles);
    }

    /**
     * dp思想我们也行到了，就是有个优化点
     * 1.本来我们用dp[i][j]表示先手的石子和，这样我们就必须处理后手的石子和。计算和是就要累加，而且是任意(i，j），所以划不来。
     * 2.优化石子和为石子差。
     * 3.dp[i][j] = 先手和后手的最大差值
     * 4.在for循环时，这种是从对角线，向斜上方平移，所以用一个l表示位移，而i就正常的从0到dp.length
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
        for (int l = 1; l < dp.length; l++) {
            for (int i = 0; i < dp.length; i++) {
                for (int j = i + l; j < dp[i].length; j++) {
                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] + dp[i][j - 1]);
                }
            }
        }
        return dp[0][piles.length - 1] > 0;
    }

    @Test
    public void test_() {
        Assert.assertEquals(except, stoneGame(piles));
    }

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {5, 3, 4, 5}, true}
        };
    }

    @Parameter
    public int[]   piles;
    @Parameterized.Parameter(1)
    public boolean except;

}