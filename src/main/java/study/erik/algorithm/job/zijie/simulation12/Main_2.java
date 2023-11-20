package study.erik.algorithm.job.zijie.simulation12;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/14 10:39
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] g = new int[n + 1][n + 1];
        int INF = 0x3f3f3f3f;
        for (int[] ints : g) {
            Arrays.fill(ints, INF);
        }
        for (int[] time : times) {
            g[time[0]][time[1]] = time[2];
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[k] = 0;
        Set<Integer> visited = new HashSet<>();
        while (visited.size() < n) {
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            for (int i = 1; i < dp.length; i++) {
                if(!visited.contains(i) && min > dp[i]) {
                    min = dp[i];
                    minIdx = i;
                }
            }
            visited.add(minIdx);
            for (int i = 1; i <= n; i++) {
                if (visited.contains(i) || i == minIdx || g[minIdx][i] == INF) {
                    continue;
                }
                int newDp = dp[minIdx] + g[minIdx][i];
                dp[i] = Math.min(dp[i], newDp);
            }
        }
        int ret = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            ret = Math.max(ret, dp[i]);
        }
        return ret == INF ? -1 : ret;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray2Dimension("[[2,1,1],[2,3,1],[3,4,1]]"), 4, 2},
                {1, ArrayUtils.buildArray2Dimension("[[1,2,1]]"), 2, 1}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] times;
    @Parameterized.Parameter(2)
    public int n;
    @Parameterized.Parameter(3)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, networkDelayTime(times, n, k));
    }

}
