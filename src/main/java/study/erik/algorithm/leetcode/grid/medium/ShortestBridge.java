/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.medium;

import javafx.util.Pair;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : ShortestBridge.java, v 0.1 2022年11月07日 09:23 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ShortestBridge {

    @LetCodeCommit(title = "934. Shortest Bridge",
            diff = "m",
            selfRemark = "理解题意之后，方法如下"
                    + "1.先找到一个岛，可用dfs"
                    + "2.在用bfs找最短路径")
    public int shortestBridge(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean found = false;
        Queue<Pair<Integer, Integer>> q = new LinkedList();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            if (found) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, q, visited, i, j, directions);
                    found = true;
                    break;
                }
            }
        }
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                Pair<Integer, Integer> poll = q.poll();
                for (int[] direction : directions) {
                    int nextI = poll.getKey() + direction[0];
                    int nextJ = poll.getValue() + direction[1];
                    if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && !visited[nextI][nextJ]) {
                        if (grid[nextI][nextJ] == 1) {
                            return step;
                        }
                        visited[nextI][nextJ] = true;
                        q.add(new Pair<>(nextI, nextJ));
                    }
                }
                size--;
            }
            step++;
        }
        return 0;
    }

    private void dfs(int[][] grid, Queue<Pair<Integer, Integer>> q, boolean[][] visited, int i, int j, int[][] directions) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || visited[i][j] || grid[i][j] == 0) {
            return;
        }
        visited[i][j] = true;
        q.add(new Pair<>(i, j));
        for (int[] direction : directions) {
            dfs(grid, q, visited, i + direction[0], j + direction[1], directions);
        }
    }
}