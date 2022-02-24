/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.interval;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.TreeMap;

/**
 * @author yueyi
 * @version : FindRightInterval.java, v 0.1 2022年02月24日 9:30 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindRightInterval {

    @LetCodeCommit(title = "436. Find Right Interval",
            selfRemark = "使用了TreeMap的ceiling方法。"
                    + "当然可以自己写二叉查找的。todo吧")
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> startValue2IndexMap = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            startValue2IndexMap.put(interval[0], i);
        }
        int[] ret = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            Integer leastStartValue = startValue2IndexMap.ceilingKey(interval[1]);
            ret[i] = leastStartValue == null ? -1 : startValue2IndexMap.get(leastStartValue);
        }
        return ret;
    }

    @Parameter
    public int[][] intervals;
    @Parameter(1)
    public int[]   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2]]"), ArrayUtils.buildArray("[-1]")},
                {ArrayUtils.buildArray2Dimension("[[3,4],[2,3],[1,2]]"), ArrayUtils.buildArray("[-1,0,1]")},
                {ArrayUtils.buildArray2Dimension("[[1,4],[2,3],[3,4]]"), ArrayUtils.buildArray("[-1,2,-1]")},
        };
    }

    @Test
    public void test_() {
        Assert.assertArrayEquals(expect, findRightInterval(intervals));
    }

}