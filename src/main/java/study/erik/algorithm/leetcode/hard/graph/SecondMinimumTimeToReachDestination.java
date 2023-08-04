package study.erik.algorithm.leetcode.hard.graph;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/8/4 ，时间：11:24
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class SecondMinimumTimeToReachDestination {

    @LetCodeCommit(title = "2045. Second Minimum Time to Reach Destination",
            diff = "h",
            selfRemark = "实在编不出不到好题目了吗？找第二小？" +
                    "这里的分析是很对题目的，也可以算是一个具体分析题目。" +
                    "1.用bfs来找最短路径。" +
                    "2.看破迷雾：time和change" +
                    "3.第二小：min+1和min+2")
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<List<Integer>> al = new ArrayList<>();

        int[] minSteps = new int[n + 1];
        Arrays.fill(minSteps, 10001);
        //转化edge，构造图结构
        for (int i = 0; i <= n; ++i)
            al.add(new ArrayList<Integer>());
        for (int[] e : edges) {
            al.get(e[0]).add(e[1]);
            al.get(e[1]).add(e[0]);
        }

        int step = -1;
        List<Integer> q = new ArrayList<>();
        q.add(1);

        while (!q.isEmpty() && ++step <= minSteps[n] + 1) {
            List<Integer> q1 = new ArrayList<>();
            for (int i : q) {
                if (step == minSteps[i] || step > minSteps[i] + 1) {
                    //不够min就不再进队列。
                    continue;
                }
                minSteps[i] = Math.min(minSteps[i], step);
                if (i == n && step > minSteps[n]) {
                    //此时step=minSteps[n]+1，迂回了一下
                    return stepsTime(step, time, change);
                }
                for (int j : al.get(i)) {
                    //统统进队列。这里并不是用visited来，而是用minSteps[]来控制的
                    q1.add(j);
                }
            }
            q = q1;
        }
        return stepsTime(minSteps[n] + 2, time, change);
    }

    int stepsTime(int steps, int time, int change) {
        //走steps需要多少时间？
        int res = 0;
        while (--steps > 0) {
            res += time;
            if ((res / change) % 2 == 1) res = (res / change + 1) * change;
        }
        return res + time;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{{13, 5, ArrayUtils.buildArray2Dimension("[[1,2],[1,3],[1,4],[3,4],[4,5]]"), 3, 5}, {11, 2, ArrayUtils.buildArray2Dimension("[[1,2]]"), 3, 2},});
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public int[][] edges;
    @Parameterized.Parameter(3)
    public int time;
    @Parameterized.Parameter(4)
    public int change;

    @Test
    public void test() {
        Assert.assertEquals(expect, secondMinimum(n, edges, time, change));
    }

}
