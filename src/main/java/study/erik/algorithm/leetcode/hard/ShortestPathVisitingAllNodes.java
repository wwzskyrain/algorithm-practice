package study.erik.algorithm.leetcode.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;

public class ShortestPathVisitingAllNodes {
    private int[][] cache;
    private int     endingMask;

    public int dp(int node, int mask, int[][] graph) {
        if (cache[node][mask] != 0) {
            return cache[node][mask];
        }
        if ((mask & (mask - 1)) == 0) {
            // Base case - mask only has a single "1", which means
            // that only one node has been visited (the current node)
            return 0;
        }

        cache[node][mask] = Integer.MAX_VALUE - 1; // Avoid infinite loop in recursion
        for (int neighbor : graph[node]) {
            if ((mask & (1 << neighbor)) != 0) { // 倒着来的，如果==0说明已经减去了.
                // case1:neighbor已经被访问过
                int alreadyVisited = dp(neighbor, mask, graph);
                // case2:neighbor没有被访问过
                int notVisited = dp(neighbor, mask ^ (1 << node), graph);
                int betterOption = Math.min(alreadyVisited, notVisited);
                cache[node][mask] = Math.min(cache[node][mask], 1 + betterOption);
            }
        }

        return cache[node][mask];
    }

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        endingMask = (1 << n) - 1;
        cache = new int[n + 1][endingMask + 1];

        int best = Integer.MAX_VALUE;
        for (int node = 0; node < n; node++) {
            best = Math.min(best, dp(node, endingMask, graph));
        }

        return best;
    }

    @LetCodeCommit(title = "847. Shortest Path Visiting All Nodes",
            selfRemark = "正着来：自底向上。"
                    + "这个代码很好，要收藏")
    public int shortestPathLength1(int[][] graph) {
        if (graph.length == 1) {
            return 0;
        }

        int n = graph.length;
        int endingMask = (1 << n) - 1;
        boolean[][] seen = new boolean[n][endingMask];
        ArrayList<int[]> queue = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            queue.add(new int[] {i, 1 << i});
            seen[i][1 << i] = true;
        }

        int steps = 0;
        while (!queue.isEmpty()) {
            ArrayList<int[]> nextQueue = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                int[] currentPair = queue.get(i);
                int node = currentPair[0];
                int mask = currentPair[1];
                for (int neighbor : graph[node]) {
                    // 正着来的时候，就不用分析两个case了，这样理解起来就没有负担了。
                    int nextMask = mask | (1 << neighbor);
                    if (nextMask == endingMask) {
                        return 1 + steps;
                    }

                    if (!seen[neighbor][nextMask]) {
                        seen[neighbor][nextMask] = true;
                        nextQueue.add(new int[] {neighbor, nextMask});
                    }
                }
            }
            steps++;
            queue = nextQueue;
        }

        return -1;
    }
}