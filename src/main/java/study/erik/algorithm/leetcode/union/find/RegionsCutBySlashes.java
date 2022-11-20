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
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RegionsCutBySlashes.java, v 0.1 2022年11月20日 09:27 yueyi Exp $
 */
@LetCodeCommit(title = "959. Regions Cut By Slashes",
diff = "m",
selfRemark = "这个题目还是蛮有意思的。"
        + "但是却难住我了"
        + "求区域个数首先就应该想到并查集。")
@RunWith(Parameterized.class)
public class RegionsCutBySlashes {

    int count = 0;
    int n     = 0;

    public int regionsBySlashes(String[] grid) {
        n = grid.length;
        count = 4 * n * n;
        int[] p = new int[4 * n * n];
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) {
                    union(c(i - 1, j, 2), c(i, j, 0), p);
                }
                if (j > 0) {
                    union(c(i, j - 1, 1), c(i, j, 3), p);
                }
                if (grid[i].charAt(j) != '/') {
                    union(c(i, j, 0), c(i, j, 1), p);
                    union(c(i, j, 3), c(i, j, 2), p);
                }
                if (grid[i].charAt(j) != '\\') {
                    union(c(i, j, 3), c(i, j, 0), p);
                    union(c(i, j, 1), c(i, j, 2), p);
                }
            }
        }
        return count;
    }

    private int c(int i, int j, int no) {
        return (i * n + j) * 4 + no;
    }

    private void union(int x, int y, int[] p) {
        int xP = find(x, p);
        int yP = find(y, p);
        if (xP != yP) {
            p[xP] = yP;
            count--;
        }
    }

    private int find(int x, int[] p) {
        if (p[x] != x) {
            p[x] = find(p[x], p);
        }
        return p[x];
    }

    @Parameter
    public String[] grid;
    @Parameter(1)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {" /", "/ "}, 2},
                {new String[] {" /", "  "}, 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, regionsBySlashes(grid));
    }

}