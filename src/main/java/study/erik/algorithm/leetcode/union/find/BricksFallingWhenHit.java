/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.union.find;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : BricksFallingWhenHit.java, v 0.1 2022年06月26日 15:25 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BricksFallingWhenHit {

    @LetCodeCommit(title = "Bricks Falling When Hit",
            diff = "h",
            selfRemark = "反着用的一个并查集.")
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int R = grid.length, C = grid[0].length;
        int[][] A = new int[R][C];

        for (int i = 0; i < R; i++) {
            A[i] = grid[i].clone();
        }
        for (int[] hit : hits) {
            A[hit[0]][hit[1]] = 0;
        }
        UnionFind unionFind = new UnionFind(R * C + 1);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (A[i][j] == 1) {
                    int idx = i * C + j;
                    if (i == 0) {
                        unionFind.union(idx, R * C);
                    }
                    if (i > 0 && A[i - 1][j] == 1) {
                        unionFind.union(idx, (i - 1) * C + j);
                    }
                    if (j > 0 && A[i][j - 1] == 1) {
                        unionFind.union(idx, i * C + j - 1);
                    }
                }
            }
        }
        int[] res = new int[hits.length];
        int[] xD = new int[] {1, 0, -1, 0};
        int[] yD = new int[] {0, 1, 0, -1};
        for (int i = hits.length - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            if (grid[x][y] == 1) {
                int preSize = unionFind.size();
                int idx = x * C + y;
                for (int j = 0; j < 4; j++) {
                    int nx = x + xD[j];
                    int ny = y + yD[j];
                    if (nx >= 0 && nx < R && ny >= 0 && ny < C && A[nx][ny] == 1) {
                        // unionFind.union(idx, nx * C + ny);  交换参数也没事
                        unionFind.union(nx * C + ny, idx);
                    }
                }
                if (x == 0) {
                    unionFind.union(idx, R * C);
                }
                A[x][y] = 1;
                int curSize = unionFind.size();
                res[i] = Math.max(0, curSize - preSize - 1);
            }
        }

        return res;
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] high;

        public UnionFind(int N) {
            parent = new int[N];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            size = new int[N];
            Arrays.fill(size, 1);
            high = new int[N];
        }

        /**
         * x和y是可以调换的.
         * @param x
         * @param y
         */
        public void union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) {
                return;
            }
            if (high[px] < high[py]) {
                parent[px] = py;
                size[py] += size[px];
            } else if (high[px] == high[py]) {
                high[px]++;
                parent[py] = px;
                size[px] += size[py];
            } else {
                parent[py] = px;
                size[px] += size[py];
            }
        }

        public int find(int n) {
            while (parent[n] != n) {
                parent[n] = parent[parent[n]];
                n = parent[n];
            }
            return n;
        }

        public int size() {
            return size[find(parent.length - 1)] - 1;
        }
    }

    @Parameter
    public int[][] grid;
    @Parameter(1)
    public int[][] hits;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{
                //        ArrayUtils.buildArray2Dimension("[[0,1],[0,1],[0,1],[0,1]]"),
                //        ArrayUtils.buildArray2Dimension("[[0,1]]")},
                {
                        ArrayUtils.buildArray2Dimension("[[1,0,0,0],[1,1,1,0]]"),
                        ArrayUtils.buildArray2Dimension("[[1,0]]")
                }
        };
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(hitBricks(grid, hits)));
    }

}