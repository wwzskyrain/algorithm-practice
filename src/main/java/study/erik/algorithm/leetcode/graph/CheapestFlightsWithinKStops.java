/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.graph;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : CheapestFlightsWithinKStops.java, v 0.1 2022年05月11日 07:25 yueyi Exp $
 */
public class CheapestFlightsWithinKStops {

    @LetCodeCommit(title = "787. Cheapest Flights Within K Stops",
            selfRemark = "有一个最短距离。看来图的问题总是爱出最短距离的题目"
                    + "bellman ford方法有两个要诀"
                    + "1.最多次节点次数的遍历边"
                    + "2.跳过距离还是默认值的节点，说明它们还没有加入进来，它们就不能当跳板"
                    + "3.要用一个temp数组，不然会在同一层遍历中用到本层的最新值，这是不应该的.")
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // bellman ford
        int defaultCost = 0x7fffffff;
        int[] cost = new int[n];
        Arrays.fill(cost, defaultCost);
        cost[src] = 0;

        for (int i = 0; i <= k; i++) {
            int[] temp = Arrays.copyOf(cost, n);
            for (int[] flight : flights) {
                int from = flight[0], to = flight[1], c = flight[2];
                if (cost[from] == defaultCost) {
                    continue;
                }
                temp[to] = Math.min(temp[to], cost[from] + c);
            }
            cost = temp;
        }
        return cost[dst] == defaultCost ? -1 : cost[dst];
    }

}