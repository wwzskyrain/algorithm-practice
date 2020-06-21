package study.erik.algorithm.leetcode.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


/**
 * @author erik.wang
 * @date 2020-06-18 22:49
 * title = Shortest Path Visiting All Nodes
 */
public class ShortestPathVisitingAllNodes {


    @Test
    public void test_() {


        int[][] graph5 = new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11}, {0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11}, {0, 1, 2, 3, 4, 6, 7, 8, 9, 10, 11}, {0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11}, {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11}, {0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}};
        Assert.assertEquals(17, shortestPathLength(graph5));

        int[][] graph4 = new int[][]{{7}, {3}, {3, 9}, {1, 2, 4, 5, 7, 11}, {3}, {3}, {9}, {3, 10, 8, 0}, {7}, {11, 6, 2}, {7}, {3, 9}};
        Assert.assertEquals(17, shortestPathLength(graph4));

        int[][] graph3 = new int[][]{{2, 3, 5, 7}, {2, 3, 7}, {0, 1}, {0, 1}, {7}, {0}, {10}, {9, 10, 0, 1, 4}, {9}, {7, 8}, {7, 6}};
        Assert.assertEquals(14, shortestPathLength(graph3));

        int[][] graph2 = new int[][]{{}};
        Assert.assertEquals(0, shortestPathLength(graph2));

        int[][] graph1 = new int[][]{{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}};
        Assert.assertEquals(4, shortestPathLength(graph1));

        int[][] graph = new int[][]{{1, 2, 3}, {0}, {0}, {0}};
        Assert.assertEquals(4, shortestPathLength(graph));
    }

    public int shortestPathLength(int[][] graph) {

        if (graph.length == 0) {
            return 0;
        }

        if (graph.length == 1) {
            if (graph[0].length == 0) {
                return 0;
            } else {
                return 1;
            }
        }


        for (int i = 0; i < graph.length; i++) {

            boolean[][] visited = new boolean[graph.length][];
            for (int i1 = 0; i1 < visited.length; i1++) {
                visited[i1] = new boolean[graph.length];
            }

            dfs(graph, visited, 0, i);
        }
        return shortestPath;
    }

    int shortestPath = Integer.MAX_VALUE;

    /**
     * 超时，超时，这个方法是超时，而不是错误。
     * 这是一个回溯法的解答方式；
     * 对于超时的情况，一般都是选错了解题思路，此处也是。
     * 正确的解题思路是BFS——广度搜索，而且是图的广度搜索。
     *
     * @param graph
     * @param visited
     * @param curPathLength
     * @param curPoint
     */
    public void dfs(int[][] graph, boolean[][] visited, int curPathLength, int curPoint) {

        if (curPathLength > shortestPath) {
            //剪枝
            return;
        }

        boolean[] visitedNode = new boolean[graph.length];
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j] == true) {
                    visitedNode[i] = true;
                    visitedNode[j] = true;
                }

            }
        }

        boolean allVisited = true;
        for (int i = 0; i < visitedNode.length; i++) {
            if (!visitedNode[i]) {
                allVisited = false;
                break;
            }
        }
        if (allVisited) {
            shortestPath = Math.min(shortestPath, curPathLength);
            return;
        }


        for (int nextPoint : graph[curPoint]) {

            if (!visited[curPoint][nextPoint]) {
                visited[curPoint][nextPoint] = true;
                dfs(graph, visited, curPathLength + 1, nextPoint);
                visited[curPoint][nextPoint] = false;
            }
        }

    }


    static class Tuple {
        int bitMask;
        int curr;
        int cost;

        public Tuple(int bit, int n, int c) {
            bitMask = bit;
            curr = n;
            cost = c;
        }

        @Override
        public boolean equals(Object o) {
            Tuple p = (Tuple) o;
            return bitMask == p.bitMask && curr == p.curr && cost == p.cost;
        }

        @Override
        public int hashCode() {
            return 1331 * bitMask + 7193 * curr + 727 * cost;
        }
    }

    /**
     * 图的广度搜索，我们还是第一次接触呢；
     * 这还不是一般的广度搜索，一般的广度搜索：
     * 从某一个指定结点出发，一层一层的遍历；按照这种思路，其他每个结点都和指定结点有一个距离。
     * 而这里的广度搜索是多路齐发，而且是全路齐发呢；
     * 而且随着层次的递增，路数就变得更多，同时保持这不同路数不同状态。
     * 其中，状态是用一个全局bit图+一个当前节点表示。
     * 这为我们提供了一个新的思路，也不亏我们看了那么久。
     *
     * @param graph
     * @return
     */
    public int shortestPathLength1(int[][] graph) {

        int N = graph.length;

        Queue<Tuple> queue = new LinkedList<>();
        Set<Tuple> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int tmp = (1 << i);
            set.add(new Tuple(tmp, i, 0));
            queue.add(new Tuple(tmp, i, 1));
        }

        while (!queue.isEmpty()) {
            Tuple curr = queue.remove();

            if (curr.bitMask == (1 << N) - 1) {
                return curr.cost - 1;
            } else {
                int[] neighbors = graph[curr.curr];

                for (int v : neighbors) {
                    int bitMask = curr.bitMask;
                    bitMask = bitMask | (1 << v);

                    Tuple t = new Tuple(bitMask, v, 0);
                    if (!set.contains(t)) {
                        queue.add(new Tuple(bitMask, v, curr.cost + 1));
                        set.add(t);
                    }
                }
            }
        }
        return -1;
    }

}
