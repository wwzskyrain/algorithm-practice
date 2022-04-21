/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.union;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RedundantConnection.java, v 0.1 2022年04月21日 9:29 下午 yueyi Exp $
 */
public class RedundantConnection {

    @LetCodeCommit(title = "684. Redundant Connection",
            diff = "m",
            selfRemark = "做到一个并查集的题目")
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[1001];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (find(from, parent) == find(to, parent)) {
                return edge;
            }
            union(from, to, parent);
        }
        throw new IllegalArgumentException();
    }

    private int find(int node, int[] parent) {
        while (node != parent[node]) {
            node = parent[node];
        }
        return node;
    }

    private void union(int a, int b, int[] parent) {
        int rootA = find(a, parent);
        int rootB = find(b, parent);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }

}