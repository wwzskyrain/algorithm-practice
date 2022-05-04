/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.graph;

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
 * @version : NetworkDelayTime.java, v 0.1 2022年05月03日 20:27 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NetworkDelayTime {

    @LetCodeCommit(title = "743. Network Delay Time",
            selfRemark = "求图的最短距离——迪杰特斯拉算法。"
                    + "这里没有用地杰斯特拉算法."
                    + "")
    public int networkDelayTime(int[][] times, int n, int k) {
        int defaultDistanceLimit = 100 * 100;
        int[] distanceTo = new int[n];
        Arrays.fill(distanceTo, defaultDistanceLimit);
        distanceTo[k - 1] = 0;
        for (int i = 0; i < n; i++) {
            for (int[] time : times) {
                int u = time[0];
                int v = time[1];
                int w = time[2];
                distanceTo[v - 1] = Math.min(distanceTo[v - 1], distanceTo[u - 1] + w);
            }
        }
        int maxDistance = 0;
        for (int distance : distanceTo) {
            maxDistance = Math.max(maxDistance, distance);
        }
        return maxDistance == defaultDistanceLimit ? -1 : maxDistance;
    }

    @Parameter
    public int[][] times;
    @Parameter(1)
    public int     n;
    @Parameter(2)
    public int     k;
    @Parameter(3)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2,1],[2,3,7],[1,3,4],[2,1,2]]"), 3, 2, 6},
                {ArrayUtils.buildArray2Dimension("[[2,1,1],[2,3,1],[3,4,1]]"), 4, 2, 2},
                {ArrayUtils.buildArray2Dimension("[[1,2,1]]"), 2, 2, -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, networkDelayTime(times, n, k));
    }
}