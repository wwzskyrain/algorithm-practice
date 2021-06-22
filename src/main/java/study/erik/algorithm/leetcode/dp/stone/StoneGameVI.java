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

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yueyi
 * @version : StoneGameVI.java, v 0.1 2021年06月19日 7:31 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StoneGameVI {

    @LetCodeCommit(title = "Stone Game VI")
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        //return resolveWithDfs(aliceValues, bobValues);
        return resolveWithSort(aliceValues, bobValues);
    }

    public int resolveWithSort(int[] aliceValues, int[] bobValues) {
        int[][] sum = new int[aliceValues.length][3];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = new int[] {aliceValues[i] + bobValues[i], aliceValues[i], bobValues[i]};
        }
        Arrays.sort(sum, Comparator.comparing(a -> a[0]));
        boolean aliceTurn = true;
        int sumAlice = 0;
        int sumBob = 0;
        for (int i = sum.length - 1; i >= 0; i--) {
            if (aliceTurn) {
                sumAlice += sum[i][1];
            } else {
                sumBob += sum[i][2];
            }
            aliceTurn = !aliceTurn;
        }
        return Integer.compare(sumAlice, sumBob);
    }

    /**
     * 方法应该是对的，就是超时的严重，当石头数量14时，几乎返回不了。
     * 能备忘录吗？不太好备忘，因为key不好拼凑
     *
     * @param aliceValues
     * @param bobValues
     * @return
     */
    public int resolveWithDfs(int[] aliceValues, int[] bobValues) {
        int difference = dfs(aliceValues, bobValues, new boolean[bobValues.length], true);
        if (difference > 0) {
            return 1;
        } else if (difference == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    public int dfs(int[] aliceValues, int[] bobValues, boolean[] visited, boolean aliceTurn) {

        Integer maxDifference = null;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int maxDiff = dfs(aliceValues, bobValues, visited, !aliceTurn);
                int curDiff = (aliceTurn ? aliceValues[i] : bobValues[i]) - maxDiff;
                if (maxDifference == null) {
                    maxDifference = curDiff;
                } else {
                    maxDifference = Math.max(maxDifference, curDiff);
                }
                visited[i] = false;
            }
        }
        return maxDifference == null ? 0 : maxDifference;
    }

    @Parameter
    public int[] aliceValues;
    @Parameter(1)
    public int[] bobValues;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {1, 3}, new int[] {2, 1}, 1},
                {new int[] {1, 2}, new int[] {3, 1}, 0},
                {new int[] {2, 4, 3}, new int[] {1, 6, 7}, -1},
                {new int[] {9, 9, 5, 5, 2, 8, 2, 4, 10, 2, 3, 3, 4}, new int[] {9, 5, 3, 4, 4, 6, 6, 6, 4, 3, 7, 5, 10}, 1}
        };
    }

    @Test
    public void test() {
        Assert.assertEquals(expect, stoneGameVI(aliceValues, bobValues));
    }
}