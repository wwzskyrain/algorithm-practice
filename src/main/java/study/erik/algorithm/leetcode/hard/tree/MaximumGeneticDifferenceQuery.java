/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.tree;

import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : MaximumGeneticDifferenceQuery.java, v 0.1 2023年07月27日 10:19 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumGeneticDifferenceQuery {

    @LetCodeCommit(title = "1938. Maximum Genetic Difference Query",
            selfRemark = "是一个有一定难度和一定复杂度的hard。"
                    + "1.把查询当前节点路径，转化为遍历树。"
                    + "2.把XOR操作的最大值转化为tire来找求最值。")
    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }

        int root = -1;
        for (int i = 0; i < parents.length; i++) {
            int p = parents[i];
            if (p == -1) {
                root = i;
            } else {
                graph[p].add(i);
            }
        }
        List<Pair<Integer, Integer>>[] query = new List[n];
        for (int i = 0; i < query.length; i++) {
            query[i] = new LinkedList<>();
        }
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            query[q[0]].add(new Pair<>(q[1], i));
        }
        int[] ans = new int[queries.length];
        bfs(new Tire(), root, graph, query, ans);
        return ans;
    }

    public void bfs(Tire tire, int node, List<Integer>[] graph, List<Pair<Integer, Integer>>[] query, int[] ans) {
        tire.increase(node);
        for (Pair<Integer, Integer> pair : query[node]) {
            Integer val = pair.getKey();
            Integer index = pair.getValue();
            ans[index] = tire.findMax(val);
        }
        for (Integer child : graph[node]) {
            bfs(tire, child, graph, query, ans);
        }
        tire.remove(node);
    }

    public static class Tire {
        private Tire[] children = new Tire[2];
        private int    count;
        //root的节点，其count=0，其实是一个头结点。

        public void increase(int num, int c) {
            Tire cur = this;
            for (int i = 18; i >= 0; i--) {
                //第i位，体现在cur的两个子孩子上。
                int bit = ((num >> i) & 1);
                if (cur.children[bit] == null) {
                    cur.children[bit] = new Tire();
                }
                cur = cur.children[bit];
                cur.count += c;
            }
        }

        public void remove(int num) {
            increase(num, -1);
        }

        public void increase(int num) {
            increase(num, 1);
        }

        public int findMax(int num) {
            Tire cur = this;
            int ans = 0;
            for (int i = 18; i >= 0; i--) {
                int bit = ((num >> i) & 1);
                if (cur.children[1 - bit] != null && cur.children[1 - bit].count > 0) {
                    ans |= (1 << i);
                    cur = cur.children[1 - bit];
                } else {
                    //这棵树其实是18层树，每一个节点cur，肯定要通过左孩子或者右孩子到达底部的
                    cur = cur.children[bit];
                }
            }
            return ans;
        }
    }

    @Parameter
    public int[]   parents;
    @Parameter(1)
    public int[][] queries;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[-1,0,1,1]"), ArrayUtils.buildArray2Dimension("[[0,2],[3,2],[2,5]]")},
                {ArrayUtils.buildArray("[3,7,-1,2,0,7,0,2]"), ArrayUtils.buildArray2Dimension("[[4,6],[1,15],[0,5]]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(maxGeneticDifference(parents, queries)));
    }
}