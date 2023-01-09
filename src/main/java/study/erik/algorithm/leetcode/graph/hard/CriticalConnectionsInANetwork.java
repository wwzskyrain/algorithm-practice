/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.graph.hard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : CriticalConnectionsInANetwork.java, v 0.1 2023年01月08日 09:24 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CriticalConnectionsInANetwork {

    @LetCodeCommit(title = "1192. Critical Connections in a Network")
    // We record the timestamp that we visit each node. For each node,
    // we check every neighbor except its parent and return a smallest timestamp in all its neighbors.
    // If this timestamp is strictly less than the node's timestamp, we know that this node is somehow in a cycle.
    // Otherwise, this edge from the parent to this node is a critical connection
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {graph[i] = new ArrayList<>();}

        for (List<Integer> oneConnection : connections) {
            graph[oneConnection.get(0)].add(oneConnection.get(1));
            graph[oneConnection.get(1)].add(oneConnection.get(0));
        }
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int[] timeStampAtThatNode = new int[n];
        criticalConnectionsUtil(graph, -1, 0, visited, results, timeStampAtThatNode);
        return results;
    }

    private int timer = 0;

    public void criticalConnectionsUtil(List<Integer>[] graph, int parent, int node, boolean[] visited,
                                        List<List<Integer>> results, int[] timeStampAtThatNode) {
        visited[node] = true;
        timeStampAtThatNode[node] = timer++;
        int currentTimeStamp = timeStampAtThatNode[node];

        for (int child : graph[node]) {
            if (child == parent) {continue;}
            if (!visited[child]) {
                criticalConnectionsUtil(graph, node, child, visited, results, timeStampAtThatNode);
            }
            //如果是一个圈上的节点，通过这个取较小值，则统统打平到该圈的第一个节点的timer了。

            timeStampAtThatNode[node] = Math.min(timeStampAtThatNode[node], timeStampAtThatNode[child]);
            if (currentTimeStamp < timeStampAtThatNode[child]) {
                //如果孩子不在这个圈上，其timestamp会小于当前node的timestamp，直接判断为关键边
                results.add(Arrays.asList(node, child));
            }
        }
    }

    @Parameter
    public int                 n;
    @Parameter(1)
    public List<List<Integer>> connections;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {4, ArrayUtils.buildList2Dimension("[[0,1],[1,2],[2,0],[1,3]]")},
                {2, ArrayUtils.buildList2Dimension("[[0,1]]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(criticalConnections(n, connections));
    }

}