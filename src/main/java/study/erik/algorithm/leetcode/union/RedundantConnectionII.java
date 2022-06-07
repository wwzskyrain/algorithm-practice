/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.union;

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
 * @version : RedundantConnectionII.java, v 0.1 2022年06月07日 12:59 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RedundantConnectionII {

    @LetCodeCommit(title = "685. Redundant Connection II")
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                can2 = new int[] {edges[i][0], edges[i][1]};
                can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0;
                // 技巧点：把can2这个边删除
            }
        }
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int child = edges[i][1], father = edges[i][0];
            int fatherRoot = root(parent, father);
            if (fatherRoot == child) {
                // 有环.
                if (can1[0] == -1) {
                    // case1.没有发现can1和can2，即没有具有两个父亲的节点，单纯的出现了一个环
                    return edges[i];
                }
                // case2.发现了can1和can2，并且已经把can2边删了的情况下，还能找到一个环，
                // 说明就是can1就在环中。为什么呢，因为分析题意后，就那么三种情况.
                return can1;
            }
            parent[child] = father;
        }
        // 没有发现环，即发现了具有两个父亲的节点，两个边分别是can1和can2。删除后者can2
        return can2;
    }

    int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    @Parameter
    public int[][] edges;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{ArrayUtils.buildArray("[[1,2],[1,3],[2,3]]")},
                {ArrayUtils.buildArray2Dimension("[[1,2],[3,1],[2,3]]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(findRedundantDirectedConnection(edges)));
    }
}