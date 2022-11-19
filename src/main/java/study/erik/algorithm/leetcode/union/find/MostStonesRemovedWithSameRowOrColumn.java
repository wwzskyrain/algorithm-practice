/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.union.find;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : MostStonesRemovedWithSameRowOrColumn.java, v 0.1 2022年11月18日 15:40 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MostStonesRemovedWithSameRowOrColumn {

    @LetCodeCommit(title = "947. Most Stones Removed with Same Row or Column")
    public int removeStones(int[][] stones) {

        Set<int[]> visited = new HashSet<>();
        int numOfIsland = 0;
        for (int[] stone : stones) {
            if (!visited.contains(stone)) {
                dfs(stone, visited, stones);
                numOfIsland++;
            }
        }
        return stones.length - numOfIsland;
    }

    public void dfs(int[] stone, Set<int[]> visited, int[][] stones) {
        visited.add(stone);
        for (int[] s : stones) {
            if (!visited.contains(s)) {
                if (s[0] == stone[0] || s[1] == stone[1]) {
                    dfs(s, visited, stones);
                }
            }
        }
    }

    @Parameter
    public int[][] stones;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[3,2],[3,1],[4,4],[1,1],[0,2],[4,0]]"), 4},
                {ArrayUtils.buildArray2Dimension("[[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]"), 5},
                {ArrayUtils.buildArray2Dimension("[[0,0],[0,2],[1,1],[2,0],[2,2]]"), 3},
                {ArrayUtils.buildArray2Dimension("[[0,0]]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, removeStones(stones));
    }
}