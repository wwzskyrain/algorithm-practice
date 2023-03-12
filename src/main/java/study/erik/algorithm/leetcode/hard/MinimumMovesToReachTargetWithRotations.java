/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author yueyi
 * @version : MinimumMovesToReachTargetWithRotations.java, v 0.1 2023年03月12日 13:33 yueyi Exp $
 */
public class MinimumMovesToReachTargetWithRotations {

    @LetCodeCommit(title = "1210. Minimum Moves to Reach Target with Rotations",
            selfRemark = "这是一个hard题，首先思路不难——bfs求最短路径，题意也算清楚，但是细节复杂。"
                    + "处理细节这里也用了一些常用技巧。")
    public int minimumMoves(int[][] g) {
        int n = g.length;
        int[] start = {0, 0, 0, 0}, target = {n - 1, n - 2, 0};
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        Set<String> seen = new HashSet<>();
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int r = pos[0], c = pos[1], dr = pos[2], steps = pos[3];        // snake tail row, column, row difference, steps.
            if (Arrays.equals(Arrays.copyOf(pos, 3), target))               // reach target.
            {return steps;}
            if (seen.add(r + "," + c + "," + dr)) {                         // prune duplicates.
                if (dr == 0) {                                              // horizontal position.
                    if (r + 1 < n && g[r + 1][c] + g[r + 1][c + 1] == 0)    // the two cells below are empty: down and colock-wise rotation.
                    {q.addAll(Arrays.asList(new int[] {r + 1, c, 0, steps + 1}, new int[] {r, c, 1, steps + 1}));}
                    if (c + 2 < n && g[r][c + 2] == 0)                      // the right cell is empty.
                    {
                        q.offer(new int[] {r, c + 1, 0, steps + 1});         // right.
                    }
                } else {                                                     // vertical position.
                    if (c + 1 < n && g[r][c + 1] + g[r + 1][c + 1]
                            == 0)    // the two cells right are empty: right and counterclock-wise rotation.
                    {q.addAll(Arrays.asList(new int[] {r, c + 1, 1, steps + 1}, new int[] {r, c, 0, steps + 1}));}
                    if (r + 2 < n && g[r + 2][c] == 0)                      // the below cell is empty.
                    {
                        q.offer(new int[] {r + 1, c, 1, steps + 1});         // down.
                    }
                }
            }
        }
        return -1;
    }

}