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
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : CountUnreachablePairsOfNodesInAnUndirectedGraph.java, v 0.1 2022年06月30日 09:14 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CountUnreachablePairsOfNodesInAnUndirectedGraph {

    @LetCodeCommit(title = "2316. Count Unreachable Pairs of Nodes in an Undirected Graph")
    public long countPairs(int n, int[][] edges) {

        int[] parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int rU = find(parent, u), rV = find(parent, v);
            if (rU == rV) {
                continue;
            }
            parent[rU] = rV;
            size[rV] += size[rU];
        }
        long ret = 0;
        for (int i = 0; i < n; i++) {
            ret += (n - size[find(parent, i)]);
        }
        return ret / 2;
    }

    public int find(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent, parent[n]);
    }

    @Parameter
    public int     n;
    @Parameter(1)
    public int[][] edges;
    @Parameter(2)
    public long    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3, ArrayUtils.buildArray2Dimension("[[0,1],[0,2],[1,2]]"), 0},
                {7, ArrayUtils.buildArray2Dimension("[[0,2],[0,5],[2,4],[1,6],[5,4]]"), 14},
                {3, ArrayUtils.buildArray2Dimension("[]"), 3}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, countPairs(n, edges));
    }

}