package study.erik.algorithm.classic.tsp;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.grid.ImageOverlap;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/9/22 ，时间：15:59
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Tsp {

    //旅行商问题，也是状态压缩法，但是使用搜索+记忆的形式来求解。这个形式对于我们来讲也是新的知识点之一。
    public int solution1(int n, int[][] d) {
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        int ans = rec(dp, d, 0, 0);
        return ans;
    }

    public int rec(int[][] dp, int[][] d, int s, int v) {
        if (dp[s][v] >= 0) {
            return dp[s][v];
        }
        if (s == (1 << n) - 1 && v == 0) {
            return dp[s][v] = 0;
        }
        int res = INF;
        for (int u = 0; u < n; u++) {
            if (((s >> u) & 1) == 0) { //集合s中不包括u节点
                res = Math.min(res, rec(dp, d, s | (1 << u), u) + d[v][u]);
            }
        }
        return dp[s][v] = res;
    }

    public static class PathNode {

        public int value;
        public int status;
        public int node;
        public PathNode next;
        public boolean isValid;

        public PathNode(int value, int status, int node) {
            this.value = value;
            this.status = status;
            this.node = node;
        }

        @Override
        public String toString() {
            return "PathNode{" +
                    "value=" + value +
                    ", status=" + status +
                    ", node=" + node +
                    ", next=" + next +
                    ", isValid=" + isValid +
                    '}';
        }
    }

    //放弃这种写法，采用上面的搜索+记忆
    public int solution2(int n, int[][] d) {
        int[][] dp = new int[1 << n][n];
        PathNode[][] pathDp = new PathNode[1 << n][n];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[(1 << n) - 1][0] = 0;
        pathDp[(1 << n) - 1][0] = new PathNode(0, 0, 0);
        for (int i = 0; i < n; i++) {
            pathDp[(1 << n) - 1][i] = new PathNode(0, 0, 0);
        }

        for (int S = (1 << n) - 2; S >= 0; S--) {
            for (int v = 0; v < n; v++) {
//                if (((S >> v) & 1) == 0) {
//                    continue;
//                }
                /*
                每一个状态都有v吗？
                为什么不需要限制v节点在s中呢？
                对于v节点不再s中的dp值的计算，有什么意义呢，后面也用不着
                因为在下面这个for循环中，使用到的oldStatus中肯定是有u节点的啊。
                所以我就一直都想不明白为什么不需要限制v节点在s中。
                话说回来，如果限制也是说不通，比如dp[0][v]，无论v是哪个节点都不在状态0中，那么dp[0][v]就都是0了。
                其实真没说来dp[0][0]就是有毛病的，dp[0][0]的解释是，当前状态是0——还没有遍历任何一个节点，那么如果从0节点开始呢。
                同样也有一些dp不好理解，比如dp[1<<n][1]，表示已经访问了所有的节点，当前节点为1出发，访问完剩下(其实已经不剩下什么了)
                的节点并且到0，的权重应该为多少呢？
                * */

                for (int u = 0; u < n; u++) {
                    if (((S >> u) & 1) == 0) {
                        //这个递推公式本来就很明白。
                        int oldStatue = S | (1 << u);
                        int oldDp = dp[oldStatue][u] == -1 ? 1000000000 : dp[oldStatue][u];
                        int distance = d[v][u];
                        int newValue = oldDp + distance;
                        int curDp = dp[S][v] == -1 ? Integer.MAX_VALUE : dp[S][v];
                        if (curDp > newValue) {
                            dp[S][v] = newValue;
                            if (pathDp[S][v] == null) {
                                pathDp[S][v] = new PathNode(newValue, S, v);
                            } else {
                                pathDp[S][v].value = newValue;
                            }
                            pathDp[S][v].next = pathDp[oldStatue][u];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < pathDp.length; i++) {
            for (int j = 0; j < pathDp[i].length; j++) {
                pathDp[i][j].isValid = ((i >> j) & 1) == 1;
            }
        }
        List<PathNode> list = new ArrayList<>();
        for (int i = 0; i < pathDp.length; i++) {
            for (int j = 0; j < pathDp[i].length; j++) {
                PathNode head = pathDp[i][j];
                PathNode p = head.next;
                while (p != null) {
                    if (!p.isValid) {
                        list.add(head);
                        break;
                    }
                    p = p.next;
                }
            }
        }// list是空的，可以证明，除了对每一个dp[s][v]做了计算之后，没有一个invalid的dp[s][v]被使用了。。

        list.forEach(PathNode::toString);
        return dp[0][0];
    }


    public static int INF = 9999;
    public static int DEFAULT = (int) 1e9 + 7;


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {
                        22, 5, new int[][]{
                        {0, 3, INF, 4, INF},
                        {INF, 0, 5, INF, INF},
                        {4, INF, 0, 5, INF},
                        {INF, INF, INF, 0, 3},
                        {7, 6, INF, INF, 0}
                },
                        }
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public int[][] d;

    @Test
    public void test() {
//        Assert.assertEquals(expect, solution1(n, d));
        Assert.assertEquals(expect, solution2(n, d));
    }

}
