/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MinimumTimeVisitingAllPoints.java, v 0.1 2023年03月17日 00:28 yueyi Exp $
 */
public class MinimumTimeVisitingAllPoints {

    @LetCodeCommit(title = "1266. Minimum Time Visiting All Points",
            selfRemark = "很小巧的一个easy题目")
    public int minTimeToVisitAllPoints(int[][] points) {
        int ret = 0;
        int n = points.length;
        for (int i = 1; i < n; i++) {
            int x = Math.abs(points[i][0] - points[i - 1][0]);
            int y = Math.abs(points[i][1] - points[i - 1][1]);
            ret += Math.min(x, y) + Math.abs(x - y);
        }
        return ret;
    }
}