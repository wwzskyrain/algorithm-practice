/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.graph;

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
 * @version : GraphConnectivityWithThreshold.java, v 0.1 2023年06月04日 20:11 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1627. Graph Connectivity With Threshold",
        diff = "h",
        selfRemark = "思路很明确，用union_find就可以了，连变形都不用"
                + "1.快速找到联通的两个点去union，不用gcd，用下面这种穷举，别union好的。"
                + "2.union_find肯定要用size来优化一下")
public class GraphConnectivityWithThreshold {

    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind unionFind = new UnionFind(n + 1);
        for (int z = threshold + 1; z <= n; z++) {for (int x = z * 2; x <= n; x += z) {unionFind.union(z, x);}}
        List<Boolean> list = new ArrayList<>();
        for (int[] query : queries) {
            int i = query[0];
            int j = query[1];
            list.add(unionFind.find(i) == unionFind.find(j));
        }
        return list;
    }

    public static class UnionFind {

        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < this.parent.length; i++) {
                this.parent[i] = i;
            }
            this.size = new int[n];
            Arrays.fill(size, 1);
        }

        public int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int xP = find(x);
            int yP = find(y);
            if (xP == yP) {
                return;
            }
            if (size[xP] > size[yP]) {
                parent[yP] = xP;
            } else {
                parent[xP] = yP;
            }
        }
    }

    @Parameter
    public int     n;
    @Parameter(1)
    public int     threshold;
    @Parameter(2)
    public int[][] queries;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {6, 2, ArrayUtils.buildArray2Dimension("[[1,4],[2,5],[3,6]]")},
                {6, 2, ArrayUtils.buildArray2Dimension("[[4,5],[3,4],[3,2],[2,6],[1,3]]")},
                {5, 1, ArrayUtils.buildArray2Dimension("[[4,5],[4,5],[3,2]")},
                {9, 1, ArrayUtils.buildArray2Dimension("[[6,8],[6,9],[8,9]]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(areConnected(n, threshold, queries));
    }
}