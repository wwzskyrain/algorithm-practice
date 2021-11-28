/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : AllPathsFromSourcetoTarget.java, v 0.1 2021年11月28日 11:40 下午 yueyi Exp $
 */
public class AllPathsFromSourcetoTarget {

    @LetCodeCommit(title = "797. All Paths From Source to Target",
            selfRemark = "dp题目，用备忘录即可。但是注意点："
                    + "不要在子解上直接构造当前解，要copy出一个副本",
            related = "1976. Number of Ways to Arrive at Destination-哇塞都1976题了")
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return dp(graph, 0, new HashMap<>());
    }

    /**
     * 返回index开头的路径
     *
     * @param graph
     * @param index
     * @param map
     * @return
     */
    public List<List<Integer>> dp(int[][] graph, int index, Map<Integer, List<List<Integer>>> map) {
        List<List<Integer>> subPaths = map.get(index);
        if (subPaths != null) {
            return subPaths;
        }

        List<List<Integer>> allPaths = new ArrayList<>();
        if (index == graph.length - 1) {
            List<Integer> p = new ArrayList<>();
            p.add(graph.length - 1);
            allPaths.add(p);
        } else {
            for (int i : graph[index]) {
                List<List<Integer>> subPathList = dp(graph, i, map);
                for (List<Integer> path : subPathList) {
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(0, index);
                    allPaths.add(newPath);
                }
            }
        }
        map.put(index, allPaths);
        return allPaths;
    }

    @Test
    public void test_() {
        int[][] graph = new int[4][];
        graph[0] = ArrayUtils.buildArray("[1,2]");
        graph[1] = ArrayUtils.buildArray("[3]");
        graph[2] = ArrayUtils.buildArray("[3]");
        graph[3] = new int[0];

        List<List<Integer>> lists = allPathsSourceTarget(graph);
        System.out.println(lists);
    }
}