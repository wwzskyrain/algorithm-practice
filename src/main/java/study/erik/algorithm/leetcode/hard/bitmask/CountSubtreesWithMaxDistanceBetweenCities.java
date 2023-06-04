/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.bitmask;

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
 * @version : CountSubtreesWithMaxDistanceBetweenCities.java, v 0.1 2023年06月04日 17:27 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CountSubtreesWithMaxDistanceBetweenCities {

    @LetCodeCommit(title = "1617. Count Subtrees With Max Distance Between Cities",
            diff = "h",
            selfRemark = "这个学到的不少。"
                    + "1.用bitmask来求子树。"
                    + "2.求最短路径")
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], 100);
        }
        for (int[] edge : edges) {
            int v1 = edge[0] - 1;
            int v2 = edge[1] - 1;
            dist[v1][v2] = dist[v2][v1] = 1;
        }
        for (int k = 0; k < n; k++) {
            // 所有定点之间的最短路径
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //当i=j的时候，dist[i][i] = dist[i][k] * 2 ==(当k是i的临边)== 1*2 = 2;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int[] ret = new int[n - 1];
        for (int state = 0; state < (1 << n); state++) {
            int maxDist = maxDistance(state, dist, n);
            if (maxDist > 0) {
                //maxDis == 0 means invalid subtree according to this state.
                ret[maxDist - 1]++;
            }
        }
        return ret;
    }

    public int maxDistance(int state, int[][] dist, int n) {
        int countEdge = 0;
        int countCity = 0;
        int maxDist = 0;
        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 0) {
                // city i not in state.
                continue;
            }
            countCity++;
            for (int j = i + 1; j < n; j++) {
                if (((state >> j) & 1) == 0) {
                    // city j not in state.
                    continue;
                }
                // there is one edge between city i and city j.
                countEdge += (dist[i][j] == 1) ? 1 : 0;
                maxDist = Math.max(maxDist, dist[i][j]);
            }
        }
        if ((countEdge + 1) != countCity) {
            //state do not form a valid subtree;
            return 0;
        }
        return maxDist;
    }

    @Parameter
    public int     n;
    @Parameter(1)
    public int[][] edges;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {4, ArrayUtils.buildArray2Dimension("[[1,2],[2,3],[2,4]]")},
                {3, ArrayUtils.buildArray2Dimension("[[1,2],[2,3]]")},
                {2, ArrayUtils.buildArray2Dimension("[[1,2]]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(countSubgraphsForEachDiameter(n, edges)));
    }
}