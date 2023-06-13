/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.graph;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable.java, v 0.1 2023年05月29日 10:59 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {

    @LetCodeCommit(title = "1579. Remove Max Number of Edges to Keep Graph Fully Traversable",
            diff = "h",
            selfRemark = "这个题目在于思考：当然前提是我们已经会用UNION_FIND。"
                    + "假设1：只有type1的边，则用u_f就可以计算出需要删除多余的边。同理type2."
                    + "假设2：只有type1和type2，则两个u_f独立计算，不会重合。"
                    + "假设3：只有type3，同上。"
                    + "假设4：只有type3和type1，则type3和type1同质"
                    + "假设5：只有type3和type2，同假设4"
                    + "最后来到题设了，我们这里个贪心思想，先处理type3，后处理type2和type1。贪心策略不证自明。")
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> Integer.compare(b[0], a[0]));

        DSU alice = new DSU(n + 1);
        DSU bob = new DSU(n + 1);
        int removedEdges = 0, totalEdges = 0;

        for (int[] e : edges) {
            if (e[0] == 3) {
                boolean isEdgeValidForAlice = alice.union(e[1], e[2]);
                boolean isEdgeValidForBob = bob.union(e[1], e[2]);

                if (isEdgeValidForAlice) {
                    totalEdges++;//made the edge
                }
                if (isEdgeValidForBob) {
                    totalEdges++;//made the edge
                }

                if (!isEdgeValidForAlice || !isEdgeValidForBob) {
                    removedEdges++;//remove the edge if it's not valid
                }
            } else if (e[0] == 1) {
                boolean isEdgeValidForAlice = alice.union(e[1], e[2]);
                if (isEdgeValidForAlice) {
                    totalEdges++;
                } else {
                    removedEdges++;
                }
            } else {
                boolean isEdgeValidForBob = bob.union(e[1], e[2]);
                if (isEdgeValidForBob) {
                    totalEdges++;
                } else {
                    removedEdges++;
                }
            }
        }

        if (totalEdges != 2 * n - 2) {
            return -1;//n - 1 for alice and n - 1 for bob
        }

        return removedEdges;
    }
}

class DSU {
    private int parent[];

    DSU(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {parent[i] = i;}
    }

    public int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];//path compression
            x = parent[x];
        }
        return x;
    }

    // 这是u_f算法在找最小连接树的最好的模板；注意返回boolean，用以计算这条边是否有贡献
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {return false;}

        parent[rootX] = rootY;
        return true;
    }

}