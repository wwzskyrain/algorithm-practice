/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author yueyi
 * @version : MinimumHeightTrees.java, v 0.1 2021年12月17日 12:49 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumHeightTrees {

    @LetCodeCommit(title = "310. Minimum Height Trees",
            selfRemark = "拓扑排序的题目，可惜没有理解。"
                    + "1.解法中的level和newLevel的交换其实也是使用队列的本质。"
                    + "2.结束条件是n>2，这一点有点不好想到，单其实当使用队列时，只需要保持最后一层入队的就可以了。这一条待实践——先看第三条"
                    + "3.不必要非写成队列的进出，同样用level和newLevel就行。关键是结束条件。待实践（本条是对2的强调和说明）",
    related = "https://leetcode.com/problems/accounts-merge/"
            + "https://leetcode.com/problems/loud-and-rich/"
            + "https://leetcode.com/problems/count-sub-islands/")
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {return Collections.singletonList(0);}
        List<Set<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            g.get(edge[0]).add(edge[1]);
            g.get(edge[1]).add(edge[0]);
        }
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < g.size(); i++) {
            if (g.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i = 0; i < leaves.size(); i++) {
                Integer node = leaves.get(i);
                Integer j = g.get(node).iterator().next();
                Set<Integer> nextNodes = g.get(j);
                nextNodes.remove(node);
                if (nextNodes.size() == 1) {
                    newLeaves.add(j);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }

    @Parameter
    public Integer       n;
    @Parameter(1)
    public int[][]       edges;
    @Parameter(2)
    public List<Integer> expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {5, ArrayUtils.buildArray2Dimension("[[0,1],[0,2],[0,3],[3,4]]"), Arrays.asList(0, 3)},
                {4, ArrayUtils.buildArray2Dimension("[[1,0],[1,2],[1,3]]"), Arrays.asList(1)},
                {6, ArrayUtils.buildArray2Dimension("[[3,0],[3,1],[3,2],[3,4],[5,4]]"), Arrays.asList(3, 4)},
                {1, ArrayUtils.buildArray2Dimension("[]"), Arrays.asList(0)},
                {2, ArrayUtils.buildArray2Dimension("[[0,1]]"), Arrays.asList(0, 1)},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findMinHeightTrees(n, edges));
    }

}