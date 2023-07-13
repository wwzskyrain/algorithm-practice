/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.tree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : CountWaysToBuildRoomsInAnAntColony.java, v 0.1 2023年07月12日 07:31 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1916. Count Ways to Build Rooms in an Ant Colony")
public class CountWaysToBuildRoomsInAnAntColony {

    int MOD = 1_000_000_007;

    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        List<Integer>[] tree = new List[n];
        Integer[] dp = new Integer[n];
        Integer[] nodeCount = new Integer[n];
        long[] perm = new long[n + 1];
        perm[0] = 1;
        for (int i = 1; i < perm.length; i++) {
            perm[i] = i * perm[i - 1] % MOD;
        }
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new LinkedList<>();
        }
        for (int i = 1; i < prevRoom.length; i++) {
            int pre = prevRoom[i];
            tree[pre].add(i);
        }
        dfs(tree, 0, dp, perm, nodeCount);
        return dp[0];
    }

    void dfs(List<Integer>[] tree, int cur, Integer[] dp, long[] perm, Integer[] nodeCount) {
        if (tree[cur].isEmpty()) {
            dp[cur] = 1;
            nodeCount[cur] = 1;
            return;
        }
        //后续遍历：先遍历孩子节点
        for (Integer child : tree[cur]) {
            dfs(tree, child, dp, perm, nodeCount);
        }

        //计算孩子节点的个数
        int childNodeCount = 0;
        for (Integer child : tree[cur]) {
            childNodeCount += nodeCount[child];
        }
        nodeCount[cur] = childNodeCount + 1;

        //开始计算dp[cur]。
        long ret = perm[childNodeCount];
        for (Integer child : tree[cur]) {
            // ret = ret / (nodeCount[c1]! * nodeCount[c2]!...)
            // 在MOD取模的层面，/x 等价于==>  *inv(x)%MOD
            ret = ret * inv(perm[nodeCount[child]]) % MOD;
        }

        for (Integer child : tree[cur]) {
            ret = ret * dp[child] % MOD;
        }
        dp[cur] = ((int) ret);
    }

    long inv(long x) {
        long s = 1;
        for (; x > 1; x = MOD % x) {s = s * (MOD - MOD / x) % MOD;}
        return s;
    }

    @Parameter
    public int[] prevRoom;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{ArrayUtils.buildArray("[-1,0,1]"), 1},
                //{ArrayUtils.buildArray("[-1,0,0,1,2]"), 6},
                {ArrayUtils.buildArray("[-1,0,0,1,2,0,1,5,0,7,3,2,9,12,1,1,9,7,14,5,5,0,9,22,0,5,9,1,19,6,13,22,5,9,13,11,27,13,32,20,38,34,17,23,8,38,34,40,37,39,29,8,48,24,48,19,3,2,15,29,16,53,13,8,46,17,13,21,9,38,41,6,54,24,17,68,8,70,10,30,61,45,14,79,78,57,17,84,65,70,22,45,88,21,79,28,87,48,42,93,97,64,8,91,61,71,80,37,23,43,94,82,18,20,75,39,71,12,2,111,24,73,59,7,63,59,54,65,54,105,23,111,96,54,28,27,101,80,85,111,109,68,121,88,113,93,138,64,41,129,5,135,2,115,129,138,103,98,105,78,39,102,116,155,145,99,154,122,90,132,70,18,98,77,111,172,90,85,147,129,99,42,50,121,54,121,140,68,146,110,143,100,112,152,162,184,74,10,118,165,98,12,46,78,3,9,157,112,201,11,199,136,170,167,201,87,26,1,129,97,148,20,8,142,216,179,27,209,211,163,116,38,19,130,81,148,231,29,197,20,112,113,179,90,223,102,173,245,102,133,96,185,121,240,234,185,169,139,152,51,239,206,34,153,74,221,90,87,252,267,255,23,67,136,60,106,163,56,189,214,7,145,70,117,237,91,185,107,72,56,59,102,70,232,253,13,89,137,258,208,59,161,226,132,115,41,57,22,129,164,51,69,187,191,217,244,47,93,186,270,281,0,166,117,90,117,195,138,232,0,253,205,331,253,333,66,101,335,30,311,116,282,330,44,326,149,1,274,320,230,270,128,223,347,300,256,66,314,124,124,263,294,338,330,177,116,262,161,11,163,205,260,58,97,337,301,96,298,59,48,113,238,165,243,128,62,300,364,210,254,58,298,296,154,294,35,228,325,11,105,104,167,184,157,99,34,166,244,193,154,126,181,303,117,222,299,149,38,247,282,204,313,355,81,73,193,17,274,145,100,411,319,166,349,204,135,424,341,38,245,337,334,74,88,54,147,4,300,145,397,94,27,55,439,397,341,346,416,451,115,301,405,77,400,216,218,460,369,201,219,356,67,41,147,236,110,171,407,223,467,479,75,136,340,379,378,436,117,482,160,407,83,55,8,201,430,133,220,474,99]"), 732023092}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, waysToBuildRooms(prevRoom));
    }

}