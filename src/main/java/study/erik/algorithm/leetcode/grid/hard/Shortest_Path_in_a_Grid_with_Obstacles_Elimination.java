package study.erik.algorithm.leetcode.grid.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 日期：2023/10/8 ，时间：10:54
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1293. Shortest Path in a Grid with Obstacles Elimination",
        selfRemark = "哎，这道题被我拧巴了。最短路径大概就是dfs了。" +
                "我们也转换了思路，转换到可以经过k个障碍物这一点上。" +
                "但是我们拧巴在可以回走到同一个格子上。这就麻烦了。" +
                "其实，不用担心，回来了，那就与最短距离差远了，真的不用担心。" +
                "其次，我们下次遇到题目拧巴了也要做下去的，知道吗？要是在面试中一定硬钢到底。")
public class Shortest_Path_in_a_Grid_with_Obstacles_Elimination {




    public int shortestPath(int[][] grid, int k) {
        int[] dirs = {0, 1, 0, -1, 0};
        int m = grid.length, n = grid[0].length;
        if (k >= m + n - 2) return m + n - 2; // if we can go by manhattan distance -> let's go

        boolean[][][] visited = new boolean[m][n][k + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, k, 0}); // r, c, k, dist
        visited[0][0][k] = true;

        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0], c = top[1], curK = top[2], dist = top[3];
            if (r == m - 1 && c == n - 1) {
                return dist; // Found the result
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i], nc = c + dirs[i + 1];
                if (nr < 0 || nr == m || nc < 0 || nc == n) {
                    continue; // skip out of bound cells!
                }
                int newK = curK - grid[nr][nc];
                if (newK >= 0 && !visited[nr][nc][newK]) {
                    //不担心又回去了一个square——致敬mm
                    visited[nr][nc][newK] = true;
                    q.offer(new int[]{nr, nc, newK, dist + 1});
                }
            }
        }
        return -1; // Not found
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {6, ArrayUtils.buildArray2Dimension("[[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]]"), 1},
                {-1, ArrayUtils.buildArray2Dimension("[[0,1,1],[1,1,1],[1,0,0]]"), 1},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] grid;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, shortestPath(grid, k));
    }

}
