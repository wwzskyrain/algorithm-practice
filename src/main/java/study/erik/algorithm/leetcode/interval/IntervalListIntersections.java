/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.interval;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : IntervalListIntersections.java, v 0.1 2022年12月03日 11:08 yueyi Exp $
 */
public class IntervalListIntersections {

    @LetCodeCommit(title = "986. Interval List Intersections",
            diff = "m",
            selfRemark = "挺简单的")
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0;
        int j = 0;
        List<int[]> res = new ArrayList<>();
        while (i < firstList.length && j < secondList.length) {
            int[] f = firstList[i];
            int[] s = secondList[j];
            if (!(f[0] > s[1] || s[0] > f[1])) {
                // 相交
                res.add(new int[] {Math.max(f[0], s[0]), Math.min(f[1], s[1])});
            }
            if (f[1] < s[1]) {
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new int[0][2]);
    }

}