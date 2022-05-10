/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.graph;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : IsGraphBipartite.java, v 0.1 2022年05月10日 07:31 yueyi Exp $
 */
public class IsGraphBipartite {

    @LetCodeCommit(title = "785. Is Graph Bipartite?",
            diff = "m",
            selfRemark = "不是独立读出来的，甚至都没有独立试着去写，只独立思考了一会。"
                    + "1.分两个集合的实现方式是着色，这一点挺好的。当然也可以使用map"
                    + "2.dfs的思想在图的题目中也挺常用的.")
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 0 && !dfs(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 着色：把i着色color，并把i的相邻节点着色-color.
     *
     * @param graph
     * @param colors
     * @param i
     * @param color
     * @return true:着色成功。
     */
    public boolean dfs(int[][] graph, int[] colors, int i, int color) {
        colors[i] = color;
        for (int j = 0; j < graph[i].length; j++) {
            int v = graph[i][j];
            if (colors[v] == -color) {
                continue;
            }
            if (colors[v] == color || !dfs(graph, colors, v, -color)) {
                return false;
            }
        }
        return true;
    }
}