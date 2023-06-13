/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.graph;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : MinimumCostToConnectTwoGroupsOfPoints.java, v 0.1 2023年05月29日 14:43 yueyi Exp $
 */
public class MinimumCostToConnectTwoGroupsOfPoints {

    @LetCodeCommit(title = "1595. Minimum Cost to Connect Two Groups of Points",
            diff = "h",
            selfRemark = "这个题目算是蛮难的了，用了状态dp的解法。"
                    + "dp的维度有1<<size2，所有注定size2不能太大，大概12个以内。"
                    + "还是去好好理解这个状态变化。")
    public int connectTwoGroups(List<List<Integer>> cost) {
        int MAX = 1000_000_000;
        int size1 = cost.size();
        int size2 = cost.get(0).size();
        int[][] dp = new int[size1 + 1][1 << size2];
        for (int[] ints : dp) {
            //这里不能是MAX_VALUE，因为要进行加的。
            Arrays.fill(ints, MAX);
        }
        dp[0][0] = 0;
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                for (int s = 0; s < (1 << size2); s++) {
                    int min = dp[i + 1][s | (1 << j)];
                    // s有可能已经包含j了，没关系，重复计算而已。
                    min = Math.min(min, dp[i + 1][s] + cost.get(i).get(j));
                    //当dp[i][s]值是MAX时，其实是说明当前i还没有能到达s状态，这时候异或上j也是没什么用的，还是一个MAX+cost的大数。
                    //所以这里有很多的这样的计算（MAX+cost）
                    min = Math.min(min, dp[i][s] + cost.get(i).get(j));
                    dp[i + 1][s | (1 << j)] = min;
                }
            }
        }
        return dp[size1][(1 << size2) - 1];
    }
}