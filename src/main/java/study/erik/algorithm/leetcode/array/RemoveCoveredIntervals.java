/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

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
 * @version : RemoveCoveredIntervals.java, v 0.1 2021年07月31日 8:55 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RemoveCoveredIntervals {

    @LetCodeCommit(title = "Remove Covered Intervals")
    public int removeCoveredIntervals(int[][] intervals) {
        return resolve1(intervals);
    }

    public int resolve1(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals,
                (o1, o2) ->
                        o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);

        int[] curInterval = intervals[0];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[1] <= curInterval[1]) {
                continue;
            }
            curInterval = interval;
            count++;
        }
        return count;
    }

    @Parameter
    public int[][] intervals;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,4],[3,6],[2,8]]"), 2},
                {ArrayUtils.buildArray2Dimension("[[1,4],[2,3]]"), 1},
                {ArrayUtils.buildArray2Dimension("[[0,10],[5,12]]"), 2},
                {ArrayUtils.buildArray2Dimension("[[3,10],[4,10],[5,11]]"), 2},
                {ArrayUtils.buildArray2Dimension("[[1,2],[1,4],[3,4]]"), 1},

        };
    }

    @Test
    public void test_intervals() {
        Assert.assertEquals(expect, removeCoveredIntervals(intervals));
    }

}