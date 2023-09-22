/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yueyi
 * @version : MakingALargeIsland.java, v 0.1 2022年07月02日 21:54 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MakingALargeIsland {

    @LetCodeCommit(title = "827. Making A Large Island",
            diff = "h",
            selfRemark = "这个题目用一般的方法，是会超时的；"
                    + "所以这里用了着色法。" +
                    "还有一个就是并查集发，不过需要把二维进行一个维度处理。")
    public int largestIsland(int[][] grid) {
        int N = grid.length;
        //不同的颜色，you不同的区域面积，最坏也不可能有N*N中颜色要用的。
        int[] colorArea = new int[N * N + 2];
        int color = 2; //从2开始着色。
        int maxArea = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (grid[x][y] == 1) {
                    colorArea[color] = areaDfs(x, y, color, grid);
                    //这些都是原先的小岛面积
                    maxArea = Math.max(maxArea, colorArea[color]);
                    color++;
                }
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (grid[x][y] == 0) {
                    //来，看看这个单元能否链接一下小岛。
                    Set<Integer> seenColor = new HashSet<>(); //存放链接的小岛
                    List<Integer> neighbor = neighbor(x, y, grid.length);
                    for (Integer pos : neighbor) {
                        int xx = pos / N, yy = pos % N;
                        int col = grid[xx][yy];
                        if (col > 1 && !seenColor.contains(col)) {
                            seenColor.add(col);
                        }
                    }
                    int newArea = 1;
                    //计算连城一片的小岛的面积，并更新最大值。
                    for (Integer col : seenColor) {
                        newArea += colorArea[col];
                    }
                    maxArea = Math.max(maxArea, newArea);
                }
            }
        }
        return maxArea;
    }

    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    //把grid[x][y]着色为color，并dfs下去，最后计算被上色的面积。
    public int areaDfs(int x, int y, int color, int[][] grid) {
        int res = 1;
        grid[x][y] = color;
        for (Integer pos : neighbor(x, y, grid.length)) {
            int xX = pos / grid.length, yY = pos % grid.length;
            if (grid[xX][yY] == 1) {
                res += areaDfs(xX, yY, color, grid);
            }
        }
        return res;
    }

    public List<Integer> neighbor(int x, int y, int N) {
        List<Integer> pos = new ArrayList<>();
        for (int[] dir : dirs) {
            int xNext = x + dir[0], yNext = y + dir[1];
            if (xNext >= 0 && xNext < N &&
                    yNext >= 0 && yNext < N) {
                pos.add(xNext * N + yNext);
            }
        }
        return pos;
    }

    @Parameter
    public int[][] gridTest;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{

                {ArrayUtils.buildArray2Dimension("[[1]]"), 1},
                {ArrayUtils.buildArray2Dimension("[[0,0],[0,0]]"), 1},
                {ArrayUtils.buildArray2Dimension("[[1,0],[0,1]]"), 3},
                {ArrayUtils.buildArray2Dimension("[[1,1],[1,0]]"), 4},
                {ArrayUtils.buildArray2Dimension("[[1,1],[1,1]]"), 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, largestIsland(gridTest));
    }

}