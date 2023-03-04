/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.graph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : ShortestPathWithAlternatingColors.java, v 0.1 2023年03月04日 19:32 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ShortestPathWithAlternatingColors {

    @LetCodeCommit(title = "1129. Shortest Path with Alternating Colors")
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] ret = new int[n];
        Arrays.fill(ret, Integer.MAX_VALUE);
        ret[0] = 0;
        Map<Integer, List<Integer>> redEdgeMap = build(redEdges);
        Map<Integer, List<Integer>> blueEdgeMap = build(blueEdges);
        bfs(ret, redEdgeMap, blueEdgeMap, true);
        bfs(ret, redEdgeMap, blueEdgeMap, false);
        for (int i = 0; i < ret.length; i++) {
            ret[i] = ret[i] == Integer.MAX_VALUE ? -1 : ret[i];
        }
        return ret;
    }

    public Map<Integer, List<Integer>> build(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            List<Integer> toNodes = map.getOrDefault(from, new ArrayList<>());
            toNodes.add(to);
            map.put(from, toNodes);
        }
        return map;
    }

    public void bfs(int[] ret, Map<Integer, List<Integer>> redEdges, Map<Integer, List<Integer>> blueEdges, boolean blue) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int path = 1;
        Set<Integer> visited = new TreeSet<>();
        visited.add(0);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                Integer poll = q.poll();
                size--;
                List<Integer> toNodes = blue ? blueEdges.get(poll) : redEdges.get(poll);
                toNodes = toNodes == null ? Collections.emptyList() : toNodes;
                for (Integer node : toNodes) {
                    int relNode = node * (blue ? 1 : -1);
                    if (!visited.contains(relNode)) {
                        ret[node] = Math.min(ret[node], path);
                        q.add(node);
                        visited.add(relNode);
                    }
                }
            }
            blue = !blue;
            path++;
        }
    }

    @Parameter
    public int     n;
    @Parameter(1)
    public int[][] redEdges;
    @Parameter(2)
    public int[][] blueEdges;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {5, ArrayUtils.buildArray2Dimension("[[0,1],[1,2],[2,3],[3,4]]"), ArrayUtils.buildArray2Dimension("[[1,2],[2,3],[3,1]]")},
                {3, ArrayUtils.buildArray2Dimension("[[0,1],[1,2]]"), ArrayUtils.buildArray2Dimension("[]")},
                {3, ArrayUtils.buildArray2Dimension("[[0,1]]"), ArrayUtils.buildArray2Dimension("[[2,1]]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(shortestAlternatingPaths(n, redEdges, blueEdges)));
    }

}