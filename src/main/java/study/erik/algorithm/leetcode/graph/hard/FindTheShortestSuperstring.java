/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.graph.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author yueyi
 * @version : FindTheShortestSuperstring.java, v 0.1 2022年11月18日 09:44 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindTheShortestSuperstring {

    public String solutionWithGuanFang(String[] A) {
        int N = A.length;

        // Populate overlaps
        int[][] overlaps = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i != j) {
                    int m = Math.min(A[i].length(), A[j].length());
                    for (int k = m; k >= 0; --k) {
                        if (A[i].endsWith(A[j].substring(0, k))) {
                            overlaps[i][j] = k;
                            break;
                        }
                    }
                }
            }
        }

        // dp[mask][i] = most overlap with mask, ending with ith element
        int[][] dp = new int[1 << N][N];
        int[][] parent = new int[1 << N][N];
        for (int mask = 0; mask < (1 << N); ++mask) {
            Arrays.fill(parent[mask], -1);

            for (int bit = 0; bit < N; ++bit) {
                if (((mask >> bit) & 1) > 0) {
                    // Let's try to find dp[mask][bit].  Previously, we had
                    // a collection of items represented by pmask.
                    int pmask = mask ^ (1 << bit);
                    if (pmask == 0) {continue;}
                    for (int i = 0; i < N; ++i) {
                        if (((pmask >> i) & 1) > 0) {
                            // For each bit i in pmask, calculate the value
                            // if we ended with word i, then added word 'bit'.
                            int val = dp[pmask][i] + overlaps[i][bit];
                            if (val > dp[mask][bit]) {
                                dp[mask][bit] = val;
                                parent[mask][bit] = i;
                            }
                        }
                    }
                }
            }
        }

        // # Answer will have length sum(len(A[i]) for i) - max(dp[-1])
        // Reconstruct answer, first as a sequence 'perm' representing
        // the indices of each word from left to right.

        int[] perm = new int[N];
        boolean[] seen = new boolean[N];
        int t = 0;
        int mask = (1 << N) - 1;

        // p: the last element of perm (last word written left to right)
        int p = 0;
        for (int j = 0; j < N; ++j) {if (dp[(1 << N) - 1][j] > dp[(1 << N) - 1][p]) {p = j;}}

        // Follow parents down backwards path that retains maximum overlap
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }

        // Reverse perm
        for (int i = 0; i < t / 2; ++i) {
            int v = perm[i];
            perm[i] = perm[t - 1 - i];
            perm[t - 1 - i] = v;
        }

        // Fill in remaining words not yet added
        for (int i = 0; i < N; ++i) {if (!seen[i]) {perm[t++] = i;}}

        // Reconstruct final answer given perm
        StringBuilder ans = new StringBuilder(A[perm[0]]);
        for (int i = 1; i < N; ++i) {
            int overlap = overlaps[perm[i - 1]][perm[i]];
            ans.append(A[perm[i]].substring(overlap));
        }

        return ans.toString();
    }

    @LetCodeCommit(title = "943. Find the Shortest Superstring",
            diff = "h",
            selfRemark = "旅行收货商的问题，用位集的方法也只能处理n=15左右规模。"
                    + "这个答案特别好——好理解，具体看代码注释就行。"
                    + "另外注意一下，解题的过程和分析问题的过程还是不一样的，"
                    + "分析的时候是从上往下，而解题的过程是从下往上的."
                    + "最后，很遗憾，这个答案不能ac，因为有bad case——words之间有包含关系")
    public String solutionWithBitSet(String[] A) {
        int n = A.length;
        int[][] graph = new int[n][n];
        // build the graph
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = calc(A[i], A[j]);
                graph[j][i] = calc(A[j], A[i]);
            }
        }
        /**
         * dp[i][j]表示状态i时，以j作为出点的最优值。其中状态i表示的是方位了的节点的结合。
         * path[i][k]，首先i也同dp中的i，而k表示通过k而走到了j(这里就是dp中的j).
         * 其实j和k也可以放到一起啦。
         */
        int[][] dp = new int[1 << n][n];
        int[][] path = new int[1 << n][n];
        int last = -1, min = Integer.MAX_VALUE;

        // start TSP DP
        for (int i = 1; i < (1 << n); i++) {
            // 过滤所有的状态啦。记住目标状态是1<<n。对于目标状态是，出点是哪个，则用min和last来给出。
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    // 如果当前目标状态中不包含j点，那么这个状态就不用求以j为出点的最优值dp[i][j]了。
                    int prev = i - (1 << j);
                    if (prev == 0) {
                        // 这里其实就是只有一个节点的状态啦。
                        dp[i][j] = A[j].length();
                    } else {
                        for (int k = 0; k < n; k++) {
                            // 有对节点进行了一个全局遍历。其实这里只需要对当前状态集合中的节点进行就好了。我们改造一下
                            if (dp[prev][k] < Integer.MAX_VALUE && dp[prev][k] + graph[k][j] < dp[i][j]) {
                                // 赋值
                                dp[i][j] = dp[prev][k] + graph[k][j];
                                path[i][j] = k;
                            }
                        }
                    }
                }
                if (i == (1 << n) - 1 && dp[i][j] < min) {
                    min = dp[i][j];
                    last = j;
                }
            }
        }

        // build the path
        StringBuilder sb = new StringBuilder();
        int cur = (1 << n) - 1;
        Stack<Integer> stack = new Stack<>();
        while (cur > 0) {
            stack.push(last);
            int temp = cur;
            cur -= (1 << last);
            last = path[temp][last];
        }

        // build the result
        int i = stack.pop();
        sb.append(A[i]);
        while (!stack.isEmpty()) {
            int j = stack.pop();
            sb.append(A[j].substring(A[j].length() - graph[i][j]));
            i = j;
        }
        return sb.toString();
    }

    private int calc(String a, String b) {
        for (int i = 1; i < a.length(); i++) {
            if (b.startsWith(a.substring(i))) {
                return b.length() - a.length() + i;
            }
        }
        return b.length();
    }

    @Parameter
    public String[] words;
    @Parameter(1)
    public String   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"alex", "loves", "leetcode"}, "leetcodelovesalex"},
                {new String[] {"catg", "ctaagt", "gcta", "ttca", "atgcatc"}, "gctaagttcatgcatc"},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, solutionWithGuanFang(words));
    }

}