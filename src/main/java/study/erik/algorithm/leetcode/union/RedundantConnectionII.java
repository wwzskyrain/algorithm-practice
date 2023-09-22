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

    @LetCodeCommit(title = "685. Redundant Connection II," +
            "整体思路，总结发现就三种情况：" +
            "case1：存在一个环，并且剩下的最后一个节点E是指向环中的某个节点N的；" +
            "   此时N节点是有两个入度的，既N节点是有两个父亲的。这个时候，需要删除在环内的父亲。" +
            "case2：存在一个环，但是剩下的最后一个节点E是被环中的某个节点N指向的；" +
            "   此时，只需要断开环中任意一个边即可啦。" +
            "case3：不存在环，那就肯定存在一个节点N具有两个父亲节点P1/P2。删除后出现的那个父节点即可。" +
            "以上三种case是分析完整的。但是下面的代码组织方式稍有不同。" +
            "代码组织是按照另外一个维度区分的。请看代码及其注释。")
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                //因为是赋值，所以can2是最后一个引起入度为2的边，而且孩子节点是edges[i][1]。
                can2 = new int[]{edges[i][0], edges[i][1]};
                can1 = new int[]{parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0;    // 重点：把can2这个边删除，注意，只可能执行到这里一次
            }
        }
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            //准备并查集
        }
        for (int i = 0; i < edges.length; i++) {
            //逐个加入parent数组，构建并查集
            if (edges[i][1] == 0) {
                //跳过最后一个引起入度为2的边。
                continue;
            }
            int child = edges[i][1], father = edges[i][0];
            int fatherRoot = root(parent, father);
            //这个root，不是直接父亲，而是并查集合中的根节点。
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
        // case3：没有发现环，即发现了具有两个父亲的节点，两个边分别是can1和can2。删除后者can2
        return can2;
    }

    int root(int[] parent, int i) {
        //查询root，并同时维护并查集
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
        return new Object[][]{
                {ArrayUtils.buildArray2Dimension("[[1,2],[1,3],[2,3]]")},
                {ArrayUtils.buildArray2Dimension("[[1,2],[3,1],[2,3]]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(findRedundantDirectedConnection(edges)));
    }
}