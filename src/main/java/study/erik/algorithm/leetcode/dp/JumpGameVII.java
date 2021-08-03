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
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : JumpGameVII.java, v 0.1 2021年08月02日 8:43 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class JumpGameVII {

    @LetCodeCommit(title = "Jump Game VII")
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length(), pre = 0;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 1; i < n; ++i) {
            if (i >= minJump && dp[i - minJump]) {
                pre++;
            }
            if (i > maxJump && dp[i - maxJump - 1]) {
                pre--;
            }
            dp[i] = pre > 0 && s.charAt(i) == '0';
        }
        return dp[n - 1];
    }

    /**
     * 滑动窗口的解法，很强的。
     * 为什么这么强，因为滑动窗口是一个更抽象的解法，相比BFS和DFS，
     * 其一个滑动窗口融合的好多中情况，从而大大提高了效率。
     * 这个题目也就滑动窗口好玩，其他的，比如BFS和DFS都会超时，尽管
     * 我在DFS中还用了优先级队列做优化，没用——在真正的实力面前，一切技能都是花里胡哨
     *
     * @param s
     * @param minJump
     * @param maxJump
     * @return
     */
    public boolean resolveWithSlidWindow(String s, int minJump, int maxJump) {
        int n = s.length(), pre = 0;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = 1; i < n; ++i) {
            if (i >= minJump && dp[i - minJump]) {
                pre++;
            }
            if (i > maxJump && dp[i - maxJump - 1]) {
                pre--;
            }
            dp[i] = pre > 0 && s.charAt(i) == '0';
        }
        return dp[n - 1];
    }

    public boolean resolveWithBfs(String s, int minJump, int maxJump) {
        boolean[] visited = new boolean[s.length()];
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        queue.offer(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll == s.length() - 1) {
                return true;
            }
            for (int i = maxJump; i >= minJump; i--) {
                if (poll + i < s.length() &&
                        !visited[poll + i] &&
                        s.charAt(poll + i) == '0') {
                    queue.offer(poll + i);
                    visited[poll + i] = true;
                }
            }
        }
        return false;
    }

    /**
     * 递归法，超时了，超时用例是：满屏的001010101，和1-49999的jump。
     *
     * @param s
     * @param curIndex
     * @param minJump
     * @param maxJump
     * @return
     */
    public boolean resolveWithRecursive(String s, int curIndex, int minJump, int maxJump) {
        if (curIndex == s.length() - 1) {
            return true;
        }
        for (int i = maxJump; i >= minJump; i--) {
            if (curIndex + i < s.length()
                    && s.charAt(curIndex + i) == '0'
                    && resolveWithRecursive(s, curIndex + i, minJump, maxJump)) {
                return true;
            }
        }
        return false;
    }

    @Parameter
    public String  s;
    @Parameter(1)
    public int     minJump;
    @Parameter(2)
    public int     maxJump;
    @Parameter(3)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"011010", 2, 3, true},
                {"01101110", 2, 3, false}
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, canReach(s, minJump, maxJump));
    }
}