/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.graph;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author yueyi
 * @version : MinimumCostToReachDestinationInTime.java, v 0.1 2023年07月16日 09:32 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1928. Minimum Cost to Reach Destination in Time",
        diff = "h",
        selfRemark = "一上午整理这一个题目，哈哈，然后发现了这个题目的本质。"
                + "本质是队列遍历。"
                + "只需要遍历可行解。"
                + "可以剪枝，从而减少遍历——入队的次数。"
                + "剪枝是需要依赖局部最优解的。用优先级队列，可以加快这种局部最优解的出队。")
public class MinimumCostToReachDestinationInTime {

    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int time = edge[2];
            adj[from].add(new int[] {passingFees[to], time, to});
            adj[to].add(new int[] {passingFees[from], time, from});
        }
        int[] costs = new int[n];
        int[] times = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        Arrays.fill(times, Integer.MAX_VALUE);
        costs[0] = passingFees[0];
        times[0] = 0;
        int ret = dijkstra2(adj, n, maxTime, costs, times);
        return ret == Integer.MAX_VALUE ? -1 : ret;

    }

    public int dijkstra(List<int[]>[] adj, int n, int maxTime, int[] costs, int[] times) {
        int src = 0;
        Comparator<int[]> c = (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (a[1] - b[1]);
        PriorityQueue<int[]> q = new PriorityQueue<>(c);
        //q中保存的是可行解，按照cost、time从小到大以此排列
        q.add(new int[] {costs[src], times[src], src});
        while (!q.isEmpty()) {
            int[] z = q.poll();
            int cost = z[0];
            int time = z[1];
            int fromVertex = z[2];

            for (int i = 0; i < adj[fromVertex].size(); i++) {
                int[] nextVertexInfo = adj[fromVertex].get(i);
                int nextCost = nextVertexInfo[0];
                int nextTime = nextVertexInfo[1];
                int nextVertex = nextVertexInfo[2];
                if (time + nextTime <= maxTime) {
                    //满足if的都是可行解，所有的可行解都可以入队，但是只有更优解才有必要入队。
                    //时间的最优解和cost的最优解是不相干的
                    //leetcode的原版代码中，这个if是分开的。如dijkstra2.
                    if (costs[nextVertex] > cost + nextCost || times[nextVertex] > time + nextTime) {
                        //该if这里只是剪枝而已。
                        costs[nextVertex] = Math.min(costs[nextVertex], cost + nextCost); //
                        times[nextVertex] = Math.min(times[nextVertex], time + nextTime); //这里才是取最优值
                        q.add(new int[] {cost + nextCost, time + nextTime, nextVertex});  //下一个状态
                    }

                }
            }
        }
        return costs[n - 1];
    }

    public int dijkstra2(List<int[]>[] adj, int n, int maxTime, int[] costs, int[] times) {
        int src = 0;
        Comparator<int[]> c = (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (a[1] - b[1]);
        PriorityQueue<int[]> q = new PriorityQueue<>(c);
        q.add(new int[] {costs[src], times[src], src});
        while (!q.isEmpty()) {
            int[] z = q.poll();
            int cost = z[0];
            int time = z[1];
            int fromVertex = z[2];

            for (int i = 0; i < adj[fromVertex].size(); i++) {
                int[] nextVertexInfo = adj[fromVertex].get(i);
                int nextCost = nextVertexInfo[0];
                int nextTime = nextVertexInfo[1];
                int nextVertex = nextVertexInfo[2];
                if (time + nextTime <= maxTime) {
                    if (costs[nextVertex] > cost + nextCost) {
                        //可以不要第二个if吗？不可以，因为costs[nextVertex] <= cost + nextCost中，依然有可行解。
                        costs[nextVertex] = cost + nextCost;
                        //这个times[nextVertex]的负值，是必要的吗？没必要，但是去min是必须的。
                        //times[nextVertex] = Math.min(times[nextVertex], time + nextTime); 这样玩也是可以的
                        times[nextVertex] = time + nextTime;
                        q.add(new int[] {costs[nextVertex], times[nextVertex], nextVertex});
                    } else if (times[nextVertex] > time + nextTime) {
                        //有些人在这里思考为啥这个分支不需要更新costs[nextVertex]。哎，真是有点蠢(包括me)。
                        //因为这时候
                        times[nextVertex] = time + nextTime;
                        q.add(new int[] {cost + nextCost, times[nextVertex], nextVertex});
                    }
                    //怎么没有分支了，因为剩下的可行解，都被剪枝了。
                }
            }
        }
        int dest = n - 1;
        return costs[dest];
    }

    public int minCostWithDpAndMemorize(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int time = edge[2];
            adj[from].add(new int[] {passingFees[to], time, to});
            adj[to].add(new int[] {passingFees[from], time, from});
        }
        Integer[][] dp = new Integer[maxTime + 1][n + 1];
        int r = dfs(maxTime, 0, passingFees, dp, adj, new HashSet<>());
        return r == -1 ? -1 : (r + passingFees[n - 1]);
    }

    // dp[i][j]表示对于maxTime=i从节点j到n-1节点的最小cost
    public int dfs(int maxTime, int n, int[] cost, Integer[][] dp, List<int[]>[] adj, Set<String> visitedEdges) {
        if (maxTime < 0) {
            return -1;
        }
        if (n == cost.length - 1) {
            return 0;
        }
        if (dp[maxTime][n] != null) {
            return dp[maxTime][n];
        }
        List<int[]> nextVertexes = adj[n];
        int minCost = 1001 * 100001;
        boolean found = false;
        for (int i = 0; i < nextVertexes.size(); i++) {
            int[] nextVertexInfo = nextVertexes.get(i);
            int nextTime = nextVertexInfo[1];
            int nextVertex = nextVertexInfo[2];
            int[] edg;
            edg = new int[] {n, nextVertex, nextTime};
            //本想对这里做一个优化，让[小定点,大定点,花费]，其实就是唯一键这条edge。但是会报错。
            //另外，还有一点也是很神奇的，那就是如果加上visitedEdge参数，就会超时，不加反而不超时。
            //如果不加的话，访问路径会来来回回的重复。
            String edgCode = String.format("%s_%s_%s", edg[0], edg[1], edg[2]);
            if (visitedEdges.contains(edgCode)) {
                continue;
            }
            visitedEdges.add(edgCode);
            int d = dfs(maxTime - nextTime, nextVertex, cost, dp, adj, visitedEdges);
            if (d != -1) {
                minCost = Math.min(minCost, cost[n] + d);
                found = true;
            }
            visitedEdges.remove(edgCode);
        }
        dp[maxTime][n] = found ? minCost : -1;
        return dp[maxTime][n];
    }

    /**
     * 地三种接法，也是dp，但是直接遍历edge
     *
     * @param maxTime
     * @param edges
     * @param passingFees
     * @return
     */
    public int minCostWithDp(int maxTime, int[][] edges, int[] passingFees) {
        int INFTY = Integer.MAX_VALUE / 2;
        int maxCity = passingFees.length;
        int[][] dp = new int[maxTime + 1][maxCity];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INFTY);
        }
        dp[0][0] = passingFees[0];
        for (int t = 1; t < maxTime + 1; t++) {
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                int cost = edge[2];
                if (t >= cost) {
                    dp[t][to] = Math.min(dp[t][to], dp[t - cost][from] + passingFees[to]);
                    dp[t][from] = Math.min(dp[t][from], dp[t - cost][to] + passingFees[from]);
                }
            }
        }
        int ans = INFTY;
        for (int t = 1; t <= maxTime; ++t) {
            ans = Math.min(ans, dp[t][maxCity - 1]);
        }
        return ans == INFTY ? -1 : ans;
    }

    @Parameter
    public int     maxTime;
    @Parameter(1)
    public int[][] edges;
    @Parameter(2)
    public int[]   passingFees;
    @Parameter(3)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {155, ArrayUtils.buildArray2Dimension(
                        "[[49,31,49],[26,3,40],[32,30,49],[23,19,25],[45,39,17],[17,26,6],[49,40,38],[36,7,44],[27,16,25],[49,41,34],[36,"
                                + "17,24],[41,40,6],[40,1,24],[38,13,2],[7,4,40],[36,29,48],[37,23,15],[48,22,34],[22,45,15],[19,32,24],"
                                + "[16,21,47],[9,20,49],[28,4,32],[30,49,47],[47,9,8],[37,14,11],[14,17,41],[21,33,25],[33,46,2],[37,15,"
                                + "6],[11,35,1],[3,6,13],[25,9,34],[23,0,19],[8,31,28],[34,18,46],[30,12,1],[12,34,39],[26,35,2],[1,0,1],"
                                + "[40,9,4],[18,46,24],[17,6,19],[15,41,4],[45,28,45],[43,23,18],[19,18,9],[24,4,36],[46,47,10],[2,22,"
                                + "43],[47,28,5],[17,18,35],[3,34,1],[45,9,6],[16,11,21],[24,11,33],[18,11,31],[38,32,39],[8,0,15],[44,"
                                + "30,28],[31,13,12],[6,14,4],[37,17,19],[38,32,37],[32,29,32],[3,38,48],[47,31,44],[12,35,14],[41,7,36],"
                                + "[48,39,17],[11,8,14],[13,4,19],[34,2,50],[2,1,37],[19,48,22],[43,13,18],[37,35,41],[48,30,43],[23,36,"
                                + "24],[26,19,19],[12,1,43],[38,46,24],[2,15,42],[44,0,11],[36,7,40],[34,33,40],[32,37,39],[24,17,10],"
                                + "[24,6,37],[42,11,9],[10,3,7],[47,41,19],[23,34,23],[48,46,4],[3,2,3],[0,16,5],[6,45,15],[14,22,34],"
                                + "[27,42,46],[11,4,45],[16,0,30],[40,34,25],[47,6,50],[34,28,19],[29,14,30],[5,3,29],[16,7,31],[25,0,"
                                + "47],[16,10,13],[37,36,26],[12,42,37],[34,9,36],[42,20,27],[31,15,42],[15,8,43],[33,21,12],[11,10,11],"
                                + "[20,12,31],[43,22,38],[6,1,43],[36,39,38],[33,28,3],[19,10,2],[30,11,25],[34,44,15],[29,12,37],[31,38,"
                                + "27],[18,38,4],[15,37,47],[39,8,2],[15,33,24],[35,11,30],[30,34,44],[47,12,11],[32,22,37],[4,0,12],[34,"
                                + "21,22],[14,0,47],[31,44,6],[21,12,47],[22,21,40],[5,6,50],[25,45,25],[48,13,33],[47,23,3],[15,5,34],"
                                + "[29,34,28],[41,16,25],[23,27,7],[41,8,15],[3,25,6],[44,9,8],[21,14,12],[0,13,23],[41,3,28],[20,46,10],"
                                + "[8,3,47],[45,16,2],[7,27,25],[46,20,41],[49,19,26],[43,17,27],[23,12,14],[6,47,10],[10,27,8],[18,16,"
                                + "3],[21,7,13],[43,37,42],[33,36,5],[26,25,40],[9,8,30],[10,3,17],[17,9,34],[46,5,50],[20,7,42],[26,24,"
                                + "13],[41,19,44],[20,0,25],[41,31,9],[11,2,36],[28,39,33],[24,34,34],[46,34,19],[41,2,8],[33,10,12],[25,"
                                + "21,35],[28,12,1],[27,23,44],[1,26,20],[29,20,4],[35,29,11],[14,12,2],[49,42,10],[39,36,17],[45,4, 45]]"),
                        ArrayUtils.buildArray(
                                "[827,527,977,42,8,362,385,102,200,357,190,44,105,583,17,659,544,772,487,782,791,819,983,682,946,20,949,"
                                        + "834,782,556,231,467,663,171,277,394,99,100,986,51,607,121,573,324,247,515,224,953,471,870]"),
                        1880},
                {38, ArrayUtils.buildArray2Dimension(
                        "[[38,6,33],[6,5,3],[28,26,14],[42,36,49],[26,7,47],[18,37,9],[13,6,8],[9,12,13],[40,17,49],[5,1,47],[1,3,32],"
                                + "[11,0,28],[21,19,9],[32,27,34],[33,0,27],[40,16,9],[8,5,3],[43,5,23],[19,12,21],[38,41,32],[1,46,19],"
                                + "[9,21,22],[16,0,10],[34,35,30],[16,10,26],[4,0,50],[40,49,38],[39,15,28],[31,24,35],[9,2,27],[48,41,"
                                + "8],[11,29,26],[1,16,16],[36,1,50],[41,33,30],[35,24,18],[25,4,38],[17,40,27],[3,2,3],[6,1,23],[23,4,"
                                + "36],[38,22,15],[40,0,4],[37,33,21],[41,2,30],[30,46,47],[18,15,42],[15,6,4],[0,23,33],[0,19,31],[16,0,"
                                + "9],[47,4,11],[38,18,16],[18,41,29],[0,34,40],[1,0,18],[8,18,25],[42,19,10],[9,12,12],[22,18,37],[20,"
                                + "48,15],[32,22,46],[18,14,34],[3,28,40],[31,15,41],[44,30,8],[32,20,8],[2,0,17],[10,29,40],[20,4,19],"
                                + "[12,16,14],[30,44,24],[34,39,34],[34,15,46],[10,28,21],[11,3,21],[16,7,2],[47,4,39],[12,8,37],[48,26,"
                                + "44],[46,22,26],[17,7,33],[11,14,3],[29,15,36],[10,7,40],[49,32,29],[3,15,8],[30,14,24],[14,9,30],[1,"
                                + "27,11],[45,5,46],[47,28,24],[28,31,15],[19,47,50],[43,35,24],[11,19,5],[7,0,21],[4,45,12],[27,6,2],"
                                + "[10,2,3],[14,13,44],[24,4,49],[44,16,8]]"),
                        ArrayUtils.buildArray(
                                "[626,266,102,417,117,724,490,414,936,55,500,84,962,578,82,228,925,346,936,396,626,696,676,849,875,352,"
                                        + "856,365,731,682,773,952,945,257,248,420,141,771,227,700,942,411,640,745,48,253,193,582,152,"
                                        + "581]"),
                        -1},
                {500, ArrayUtils.buildArray2Dimension(
                        "[[9,7,18],[26,3,12],[28,45,33],[47,10,27],[34,18,38],[32,13,39],[32,26,32],[12,0,2],[4,1,7],[5,3,2],[39,25,27],"
                                + "[45,10,34],[3,19,5],[25,32,23],[30,10,47],[37,2,31],[10,32,15],[23,14,19],[22,6,14],[45,39,38],[39,21,"
                                + "30],[42,17,42],[20,17,15],[24,0,27],[2,46,11],[2,24,13],[36,22,30],[2,1,31],[41,35,45],[4,19,20],[32,"
                                + "27,33],[38,46,1],[21,11,15],[33,41,2],[45,18,30],[8,33,50],[37,11,6],[25,17,42],[45,39,33],[7,4,49],"
                                + "[17,42,36],[36,16,9],[46,25,24],[43,4,6],[35,13,28],[1,28,1],[34,35,15],[38,1,15],[16,6,28],[13,0,42],"
                                + "[3,30,24],[43,27,35],[8,0,45],[27,20,47],[6,16,47],[0,34,35],[0,35,3],[40,11,24],[1,0,49],[44,20,32],"
                                + "[26,12,17],[3,2,25],[37,25,42],[27,1,15],[36,25,38],[24,47,33],[33,28,15],[25,43,37],[47,31,47],[29,"
                                + "10,50],[11,1,21],[29,3,48],[1,25,10],[48,17,16],[19,24,22],[30,7,2],[11,22,19],[20,42,41],[27,3,48],"
                                + "[17,0,34],[19,14,32],[49,2,20],[10,3,38],[0,49,13],[6,3,28],[42,23,6],[14,8,1],[35,16,3],[17,7,40],"
                                + "[18,7,49],[36,35,13],[14,40,45],[16,33,11],[31,22,33],[38,15,48],[15,14,25],[37,13,37],[44,32,7],[48,"
                                + "1,31],[33,12,20],[22,26,23],[4,10,11],[43,28,43],[19,8,14],[35,31,33],[28,27,19],[40,11,36],[36,43,"
                                + "28],[22,21,15]]"),
                        ArrayUtils.buildArray(
                                "[199,505,107,961,682,400,304,517,512,18,334,627,893,412,922,289,19,161,206,879,336,831,577,802,139,348,"
                                        + "440,219,273,691,99,858,389,955,561,353,937,904,858,704,548,497,787,546,241,67,743,42,87,137]"),
                        336},
                {30, ArrayUtils.buildArray2Dimension("[[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]]"), ArrayUtils.buildArray(
                        "[5,1,2,20,20,3]"), 11},
                {29, ArrayUtils.buildArray2Dimension("[[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]]"), ArrayUtils.buildArray(
                        "[5,1,2,20,20,3]"), 48},
                {25, ArrayUtils.buildArray2Dimension("[[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]]"), ArrayUtils.buildArray(
                        "[5,1,2,20,20,3]"), -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minCostWithDp(maxTime, edges, passingFees));
    }

}