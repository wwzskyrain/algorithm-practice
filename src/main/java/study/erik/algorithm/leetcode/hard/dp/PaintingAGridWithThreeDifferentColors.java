/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : PaintingAGridWithThreeDifferentColors.java, v 0.1 2023年07月16日 16:25 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PaintingAGridWithThreeDifferentColors {

    @LetCodeCommit(title = "1931. Painting a Grid With Three Different Colors",
            diff = "h",
            selfRemark = "这个就仓促看答案了，然后整理一下，代码很清晰")
    public int colorTheGrid(int m, int n) {
        return dpWithDfs(0, 0, m, n);
    }

    Integer[][]     memo    = new Integer[1000][1024];
    List<Integer>[] neiMemo = new List[1024];
    int             MOD     = 1_000_000_007;

    int dpWithDfs(int column, int prevColMask, int mRow, int nColumn) {
        if (column == nColumn) {
            return 1; // Found a valid way
        }
        if (memo[column][prevColMask] != null) {
            return memo[column][prevColMask];
        }
        int ans = 0;
        for (int nei : neighbors(prevColMask, mRow)) {
            ans = (ans + dpWithDfs(column + 1, nei, mRow, nColumn)) % MOD;
        }
        return memo[column][prevColMask] = ans;
    }

    List<Integer> neighbors(int prevColMask, int m) { // Generate all possible columns we can draw, if the previous col is `prevColMask`
        if (neiMemo[prevColMask] != null) {
            return neiMemo[prevColMask];
        }
        LinkedList<Integer> nextColors = new LinkedList<>();
        dfsForNeighbors(0, m, prevColMask, 0, nextColors);
        return neiMemo[prevColMask] = nextColors;
    }

    void dfsForNeighbors(int r, int m, int prevColMask, int curColMask, List<Integer> out) {
        if (r == m) { // Filled full color for this column
            out.add(curColMask);
            return;
        }
        for (int i = 1; i <= 3; ++i) // Try colors i in [1=RED, 2=GREEN, 3=BLUE]
        {
            if (getColor(prevColMask, r) != i && (r == 0 || getColor(curColMask, r - 1) != i)) {
                //与左边列颜色不同  && (r==0 || 与上边颜色不同)
                dfsForNeighbors(r + 1, m, prevColMask, setColor(curColMask, r, i), out);
            }
        }
    }

    int getColor(int mask, int pos) { // Get color of the `mask` at `pos`, use 2 bits to store a color
        return (mask >> (2 * pos)) & 3;
    }

    int setColor(int mask, int pos, int color) { // Set `color` to the `mask` at `pos`, use 2 bits to store a color
        return mask | (color << (2 * pos));
    }

    @Parameter
    public int mm;
    @Parameter(1)
    public int nn;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, 1, 3},
                {1, 2, 6},
                {5, 5, 580986},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, colorTheGrid(mm, nn));
    }

}