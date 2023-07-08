/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.graph.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yueyi
 * @version : LargestColorValueInADirectedGraph.java, v 0.1 2023年07月08日 10:58 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LargestColorValueInADirectedGraph {

    @LetCodeCommit(title = "1857. Largest Color Value in a Directed Graph",
            diff = "h",
            selfRemark = "这个题目不算难。我首先写了一个回溯法的，思路清晰，就是超时了TLE了。"
                    + "然后学习了另外一个版本，遍历0入度的节点，层层推进，层层累计节点颜色")
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        int[] inDegree = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }
        Queue<Integer> zeroInDegree = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                zeroInDegree.offer(i);
            }
        }
        int[][] counts = new int[n][26];
        for (int i = 0; i < n; i++) {
            counts[i][colors.charAt(i) - 'a']++;
        }
        int maxCount = 0;
        int visited = 0;
        while (!zeroInDegree.isEmpty()) {
            int zeroNode = zeroInDegree.poll();
            visited++;
            for (int toNode : graph.get(zeroNode)) {
                for (int i = 0; i < 26; i++) {
                    counts[toNode][i] = Math.max(counts[toNode][i], counts[zeroNode][i] + (colors.charAt(toNode) - 'a' == i ? 1 : 0));
                }
                inDegree[toNode]--;
                if (inDegree[toNode] == 0) {
                    zeroInDegree.offer(toNode);
                }
            }
            maxCount = Math.max(maxCount, Arrays.stream(counts[zeroNode]).max().orElse(-1));
        }
        return visited == n ? maxCount : -1;
    }

    @Parameter
    public String  colors;
    @Parameter(1)
    public int[][] edges;
    @Parameter(2)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"abaca", ArrayUtils.buildArray2Dimension("[[0,1],[0,2],[2,3],[3,4]]"), 3},
                {"a", ArrayUtils.buildArray2Dimension("[[0,0]]"), -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, largestPathValue(colors, edges));
    }

}