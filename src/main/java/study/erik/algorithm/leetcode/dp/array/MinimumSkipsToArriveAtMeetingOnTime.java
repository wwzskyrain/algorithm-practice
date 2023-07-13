/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : MinimumSkipsToArriveAtMeetingOnTime.java, v 0.1 2023年07月09日 10:09 yueyi Exp $
 */
@LetCodeCommit(title = "1883. Minimum Skips to Arrive at Meeting On Time",
        diff = "h",
        selfRemark = "这个自顶向下的解法，还是挺高效的。"
                + "而lee215还有一个自下向上的，而且是一维dp。"
                + "题解出自： https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time/solutions/1242066/2d-dp/")
@RunWith(Parameterized.class)
public class MinimumSkipsToArriveAtMeetingOnTime {

    public int minSkips(int[] dist, int speed, int hoursBefore) {

        int n = dist.length;
        int totalDist = Arrays.stream(dist).sum();
        if (totalDist > hoursBefore * speed) {
            return -1;
        }

        for (int skip = 0; skip < n; skip++) {
            int distance = dfs(dist, n - 2, skip, speed);
            if (distance <= hoursBefore * speed - dist[n - 1]) {
                return skip;
            }
        }
        return -1;
    }

    Integer[][] dp = new Integer[1000][1000];

    //返回在跳跃skip次的条件下走完dist[0...i]条路的最小路程km。这个定义确实有点不好明白。
    //去看lee215的 'dp[j]/speed' 表示在跳跃j次下走完所有路程的最短时间。
    int dfs(int[] dist, int i, int skips, int speed) {
        if (skips < 0 || i < 0) {
            return skips < 0 ? 1_000_000_000 : 0;
        }
        if (dp[i][skips] == null) {
            dp[i][skips] = Math.min(
                    dist[i] + dfs(dist, i - 1, skips - 1, speed), //这是跳过
                    (dfs(dist, i - 1, skips, speed) + speed - 1 + dist[i]) / speed * speed // 这是不跳过，就等下一个speed的背数
            );
        }
        return dp[i][skips];
    }

    @Parameter
    public int[] dist;
    @Parameter(1)
    public int   speed;
    @Parameter(2)
    public int   hoursBefore;
    @Parameter(3)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,3,2]"), 4, 2, 1},
                {ArrayUtils.buildArray("[7,3,5,5]"), 2, 10, 2},
                {ArrayUtils.buildArray("[7,3,5,5]"), 1, 10, -1}

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minSkips(dist, speed, hoursBefore));
    }
}