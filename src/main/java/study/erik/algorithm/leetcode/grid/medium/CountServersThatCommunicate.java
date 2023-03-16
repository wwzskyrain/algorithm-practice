/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CountServersThatCommunicate.java, v 0.1 2023年03月17日 00:38 yueyi Exp $
 */
public class CountServersThatCommunicate {

    @LetCodeCommit(title = "1267. Count Servers that Communicate",
    selfRemark = "这个题又一次是那种从根本出发的解法，如果不是看到lee215的解法，"
            + "我估计都不会写这道题目。"
            + "我第一个感觉是找连通集合，用bfs即可。"
            + "这里确实'摸排法'去实现，真的很方便，感觉它就是我思维的另一个维度。"
            + "我以后要多多从这个角度想问题。")
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] x = new int[m];
        int[] y = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    x[i]++;
                    y[j]++;
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (x[i] > 1 || y[j] > 1)) {
                    ret++;
                }
            }
        }
        return ret;
    }

}