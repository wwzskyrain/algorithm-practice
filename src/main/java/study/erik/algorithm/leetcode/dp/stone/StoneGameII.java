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
 * @version : StoneGameII.java, v 0.1 2021年06月16日 1:41 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StoneGameII {

    @LetCodeCommit(title = "Stone Game II")
    public int stoneGameII(int[] piles) {
        return resolveWithHuiSu(piles);
    }

    // 错了，这个是博弈，不是求最值。
    // 不错，虽然是博弈，但是我们有全局值，可以减一下，从而把对手的最大值变成我们的最小值。
    // 先写一个dfs之后，在用'备忘录'来加速，不然会超时的。
    // 分析：这个题目的动态规划不太好写，所以直接dfs+备忘录。
    public int resolveWithHuiSu(int[] piles) {
        int[] sum = new int[piles.length + 1];
        int s = 0;
        for (int i = piles.length - 1; i >= 0; i--) {
            s += piles[i];
            sum[i] = s;
        }
        int[][] mem = new int[piles.length][piles.length];
        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[i].length; j++) {
                mem[i][j] = -1;
            }
        }
        return resolve(piles, sum, mem, 0, 1);
    }

    /**
     * 剩下piles[index, index+1, ... ,]，并且当前M值，返回先手的最大值
     *
     * @param piles 石子
     * @param sum   后缀和
     * @param mem   备忘录
     * @param index 当前石子
     * @param M     当前M
     * @return 先手的最大值
     */
    public int resolve(int[] piles, int[] sum, int[][] mem, int index, int M) {
        if (index + 2 * M >= piles.length) {
            return sum[index];
        }
        int Max = 0;
        int subSum = 0;
        for (int i = 0; i < M * 2 && i + index < piles.length; i++) {
            subSum += piles[index + i];
            int resolve = mem[index + i + 1][Math.max(M, i + 1)];
            if (resolve == -1) {
                resolve = resolve(piles, sum, mem, index + i + 1, Math.max(M, i + 1));
                mem[index + i + 1][Math.max(M, i + 1)] = resolve;
            }
            int max = sum[index + i + 1] - resolve + subSum;
            Max = Math.max(Max, max);
        }
        return Max;
    }

    @Parameter
    public int[] piles;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {2, 7, 9, 4, 4}, 10},
                {new int[] {1, 2, 3, 4, 5, 100}, 104},
                {new int[] {7468, 6245, 9261, 3958, 1986, 1074, 5677, 9386, 1408, 1384, 8811, 3885, 9678, 8470, 8893, 7514, 4941, 2148,
                        5217, 5425, 5307, 747, 1253, 3518, 5238, 5834, 9133, 8391, 6100, 3362, 7807, 2581, 6121, 7684, 8744, 9584, 4068,
                        7204, 4285, 8635}, 115357}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, stoneGameII(piles));
    }
}