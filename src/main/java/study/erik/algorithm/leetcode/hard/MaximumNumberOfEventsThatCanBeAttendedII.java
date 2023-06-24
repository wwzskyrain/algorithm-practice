/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author yueyi
 * @version : MaximumNumberOfEventsThatCanBeAttendedII.java, v 0.1 2023年06月23日 16:31 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumNumberOfEventsThatCanBeAttendedII {

    @LetCodeCommit(title = "1751. Maximum Number of Events That Can Be Attended II",
            diff = "h",
            selfRemark = "真没想到这么简单的hard。简单的dfs+备忘录")
    public int maxValue(int[][] events, int k) {
        ComparablePair[] pairs = new ComparablePair[events.length];
        for (int i = 0; i < events.length; i++) {
            int[] event = events[i];
            pairs[i] = new ComparablePair(event[0], event[1], event[2]);
        }
        Arrays.sort(pairs);
        int length = events.length;
        return dfs(pairs, 0, k, new Integer[length][k]);
    }

    public static class ComparablePair implements Comparable<ComparablePair> {
        public ComparablePair(Integer x, Integer y, Integer value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        Integer x;
        Integer y;
        Integer value;

        @Override
        public int compareTo(ComparablePair o) {
            return !Objects.equals(o.x, this.x) ? this.x - o.x : this.y - o.y;
        }
    }

    public int dfs(ComparablePair[] pairs, int i, int k, Integer[][] dp) {

        if (k == 0) {
            return 0;
        }
        if (i == pairs.length) {
            return 0;
        }
        if (dp[i][k - 1] != null) {
            return dp[i][k - 1];
        }
        int end = pairs[i].y;
        int insertIndex = Arrays.binarySearch(pairs, new ComparablePair(end + 1, end + 1, 0));
        if (insertIndex < 0) {
            insertIndex = -(insertIndex + 1);
        }
        int v1 = pairs[i].value + dfs(pairs, insertIndex, k - 1, dp);
        int v2 = dfs(pairs, i + 1, k, dp);
        return dp[i][k - 1] = Math.max(v1, v2);
    }

    @Parameter
    public int[][] events;
    @Parameter(1)
    public int     k;
    @Parameter(2)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2,4],[3,4,3],[2,3,1]]"), 2, 7},
                {ArrayUtils.buildArray2Dimension("[[1,2,4],[3,4,3],[2,3,10]]"), 2, 10},
                {ArrayUtils.buildArray2Dimension("[[1,1,1],[2,2,2],[3,3,3],[4,4,4]]"), 3, 9},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxValue(events, k));
    }

}