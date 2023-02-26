/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : AsFarFromLandAsPossible.java, v 0.1 2023年02月26日 18:32 yueyi Exp $
 */
public class AsFarFromLandAsPossible {

    @LetCodeCommit(title = "1162. As Far from Land as Possible",
            selfRemark = "很简单，多源bfs")
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        // store all the coordinates of 1 (multi source BFS)
        // we will have multiple islands
        // so we will need to do a BFS from each island
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // add the coordinate
                    q.offer(new int[] {i, j});
                }
            }
        }

        // 4 directions: right, up, left, down
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int result = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] curr = q.poll();  // pull out current cell
                // get the current cell's coordinates
                int x = curr[0], y = curr[1];
                // try all 4 directions
                for (int[] d : dir) {
                    // compute new coordinates
                    int newX = x + d[0];
                    int newY = y + d[1];
                    // if not in-bounds or has a distance recorded (means already visited) -> skip
                    if (newX < 0 || newY < 0 || newX >= n || newY >= n || grid[newX][newY] != 0) {
                        continue;
                    }
                    // new cell's distance is previous cell's distance + 1
                    grid[newX][newY] = 1 + grid[x][y];
                    // get the maximum ditance so far
                    result = Math.max(result, grid[newX][newY]);
                    // add the new coordinates to the queue
                    q.add(new int[] {newX, newY});
                }
            }
        }

        // result would remain 0 if we never found water cell or
        // if there are no lands (empty queue), so return -1
        // otherwise return result - 1. (WHY? see image)
        return result == 0 ? -1 : --result;
    }
}