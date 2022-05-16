/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
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
import java.util.List;

/**
 * @author yueyi
 * @version : FindEventualSafeStates.java, v 0.1 2022年05月15日 22:16 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindEventualSafeStates {

    @LetCodeCommit(title = "802. Find Eventual Safe States")
    public List<Integer> eventualSafeNodes(int[][] graph) {

        List<Integer> res = new ArrayList<>();
        int[] color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (dfs(graph, i, color)) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 判断node是否safe node，即题解.
     * 这里用color代表了备忘录.
     * 这里也用color代表了path.
     */
    public boolean dfs(int[][] graph, int node, int[] color) {
        if (color[node] > 0) {
            return color[node] == 2;
        }
        if (graph[node].length == 0) {
            color[node] = 2;
            return true;
        }
        color[node] = 1;
        for (int nextNode : graph[node]) {
            if (!dfs(graph, nextNode, color)) {
                return false;
            }
        }
        color[node] = 2;
        return true;
    }

    @Parameter
    public int[][] graph;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2],[2,3],[5],[0],[5],[],[]]")},
                {ArrayUtils.buildArray2Dimension("[[1,2,3,4],[1,2],[3,4],[0,4],[]]")}
        };
    }

    @Test
    public void test_() {
        System.out.println(eventualSafeNodes(graph));
    }

}