package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author erik.wang
 * @date 2020-03-11 15:40
 * @description
 */
@RunWith(Parameterized.class)
public class JumpGameIII {

    @LetCodeCommit(title = "Jump Game III")
    public boolean canReach(int[] arr, int start) {
        return resolveWithDfs(arr, start);
    }

    /**
     * 成绩：99.86%,51.22%
     * 这道题目还是没有看清楚，这就是一个树题，看人家的dfs效率多高。
     * 但是，也可以看出，递归解法的空间复杂度确别我的bfs低很多的。
     * 技巧学习：把arr的值取负，表示visited
     *
     * @param arr
     * @param start
     * @return
     */
    public boolean resolveWithDfs(int[] arr, int start) {
        return start >= 0 && start < arr.length && arr[start] >= 0 &&
                ((arr[start] = -arr[start]) == 0 || resolveWithDfs(arr, start + arr[start]) || resolveWithDfs(arr, start - arr[start]));
    }

    /**
     * 成绩：24.75% ，95.74%
     * 用队列来解答，有点bfs的感觉，时间不是很好
     *
     * @param arr
     * @param start
     * @return
     */
    public boolean resolveWithBfs(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            Integer curIndex = queue.poll();
            int leftIndex = curIndex - arr[curIndex];
            if (leftIndex >= 0) {
                if (arr[leftIndex] == 0) {
                    return true;
                }
                if (!visited[leftIndex]) {
                    visited[leftIndex] = true;
                    queue.offer(leftIndex);
                }
            }

            int rightIndex = curIndex + arr[curIndex];
            if (rightIndex < arr.length) {
                if (arr[rightIndex] == 0) {
                    return true;
                }
                if (!visited[rightIndex]) {
                    visited[rightIndex] = true;
                    queue.offer(rightIndex);
                }
            }
        }
        return false;
    }

    @Parameter
    public int[]   arr;
    @Parameter(1)
    public int     start;
    @Parameter(2)
    public boolean except;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[4,2,3,0,3,1,2]"), 5, true},
                {ArrayUtils.buildArray("[4,2,3,0,3,1,2]"), 0, true},
                {ArrayUtils.buildArray("[3,0,2,1,2]"), 2, false}
        };
    }

    @Test
    public void test_arr() {
        Assert.assertEquals(except, canReach(arr, start));
    }
}
