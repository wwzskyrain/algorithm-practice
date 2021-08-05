/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : JumpGameIV.java, v 0.1 2021年08月05日 12:30 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class JumpGameIV {

    @LetCodeCommit(title = "Jump Game IV")
    public int minJumps(int[] arr) {
        //return resolveWithDFS(arr);
        //return resolveWithBFS2PriorityQueue(arr);
        return resolveWithBFS(arr);
    }

    /**
     * 找最短距离，这个一看就知道要用BFS。
     * 但是一定要注意一个小优化，那就是要把nextIndexes给及时清理
     * 不然，单靠visited来判断是否要入队的效率在特定case下也消耗时间的，
     * 不如尽量减少nextIndexes，这样就不用判断visited了。
     * 我们提交的bfs就差这一句小优化，导致TLE。
     *
     * 我们还尝试了优先级队列——为此我们使用了双队列
     * 但是却没有任何优化效果，还是TLE。
     * 而且加上小技巧之后，通过了TLE，但是成绩不好，所以没有起到优化的作用
     *
     * 我们还尝试了DFS——我们没有尝试dp，那是因为这个问题的子问题和原问题之间
     * 没有很好的规模收缩的趋势，也就是说它是跳跃性的。
     * 我们用DFS的时候，加了备忘录，但是最终答案还是不对——陷入了StackOverflowError的漩涡
     * 为什么呢？因为这个过程出现了，dfs(x)和dfs(y)相互依赖的情况。除非再试试回溯法
     *
     * @param arr
     * @return
     */
    public int resolveWithBFS(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] == arr[arr.length - 1]) {
            return 1;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            List<Integer> indexes = map.getOrDefault(v, new ArrayList<>());
            indexes.add(i);
            map.put(v, indexes);
        }
        boolean[] visited = new boolean[arr.length];
        PriorityQueue<Integer>[] queues = new PriorityQueue[2];
        queues[0] = new PriorityQueue<>(Comparator.reverseOrder());
        queues[1] = new PriorityQueue<>(Comparator.reverseOrder());

        int qIndex = 0;
        queues[qIndex % 2].add(0);
        visited[0] = true;
        while (!queues[qIndex % 2].isEmpty()) {
            PriorityQueue<Integer> queue = queues[qIndex % 2];
            while (!queue.isEmpty()) {
                Integer curIndex = queue.poll();
                if (curIndex == arr.length - 1) {
                    return qIndex;
                }
                List<Integer> nextIndexes = map.getOrDefault(arr[curIndex], new ArrayList<>());
                nextIndexes.add(curIndex - 1);
                nextIndexes.add(curIndex + 1);
                for (Integer nextIndex : nextIndexes) {
                    if (nextIndex >= 0 && nextIndex < arr.length && !visited[nextIndex]) {
                        queues[(qIndex + 1) % 2].offer(nextIndex);
                        visited[nextIndex] = true;
                    }
                }
                // 小技巧：就在这个小技巧中-没有这个小技巧，就TLE了
                nextIndexes.clear();
            }
            qIndex++;
        }
        throw new IllegalArgumentException();
    }

    /**
     * 双优先级队列
     *
     * @param arr
     * @return
     */
    public int resolveWithBFS2PriorityQueue(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] == arr[arr.length - 1]) {
            return 1;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            List<Integer> indexes = map.getOrDefault(v, new ArrayList<>());
            indexes.add(i);
            map.put(v, indexes);
        }
        boolean[] visited = new boolean[arr.length];
        PriorityQueue<Integer>[] queues = new PriorityQueue[2];
        queues[0] = new PriorityQueue<>(Comparator.reverseOrder());
        queues[1] = new PriorityQueue<>(Comparator.reverseOrder());

        int qIndex = 0;
        queues[qIndex % 2].add(0);
        visited[0] = true;
        while (!queues[qIndex % 2].isEmpty()) {
            PriorityQueue<Integer> queue = queues[qIndex % 2];
            while (!queue.isEmpty()) {
                Integer curIndex = queue.poll();
                if (curIndex == arr.length - 1) {
                    return qIndex;
                }
                List<Integer> nextIndexes = map.getOrDefault(arr[curIndex], new ArrayList<>());
                nextIndexes.add(curIndex - 1);
                nextIndexes.add(curIndex + 1);
                for (Integer nextIndex : nextIndexes) {
                    if (nextIndex >= 0 && nextIndex < arr.length && !visited[nextIndex]) {
                        queues[(qIndex + 1) % 2].offer(nextIndex);
                        visited[nextIndex] = true;
                    }
                }
                // 就在这个小技巧中
                nextIndexes.clear();
            }
            qIndex++;
        }
        throw new IllegalArgumentException();
    }

    public int resolveWithDFS(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];
            List<Integer> indexes = map.getOrDefault(v, new ArrayList<>());
            indexes.add(i);
            map.put(v, indexes);
        }
        int[] dp = new int[arr.length];
        dp[0] = 0;
        return recursive(arr, dp, map, arr.length - 1);
    }

    public int recursive(int[] arr, int[] dp, Map<Integer, List<Integer>> map, int index) {
        if (dp[index] > 0) {
            return dp[index];
        }
        int min = arr.length - 1;
        if (index - 1 >= 0) {
            min = Math.min(min, recursive(arr, dp, map, index - 1));
        }
        if (index + 1 < arr.length) {
            min = Math.min(min, recursive(arr, dp, map, index + 1));
        }
        List<Integer> nextIndexes = map.getOrDefault(arr[index], new ArrayList<>());
        for (Integer nextIndex : nextIndexes) {
            if (nextIndex == index) {
                continue;
            }
            min = Math.min(min, recursive(arr, dp, map, nextIndex));
        }
        return dp[index] = min + 1;
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[100,-23,-23,404,100,23,23,23,3,404]"), 3},
                {ArrayUtils.buildArray("[7]"), 0},
                {ArrayUtils.buildArray("[7,6,9,6,9,6,9,7]"), 1},
                {ArrayUtils.buildArray("[6,1,9]"), 2},
                {ArrayUtils.buildArray("[11,22,7,7,7,7,7,7,7,22,13]"), 3},
        };
    }

    @Test
    public void test_arr() {
        Assert.assertEquals(expect, minJumps(arr));
    }

}