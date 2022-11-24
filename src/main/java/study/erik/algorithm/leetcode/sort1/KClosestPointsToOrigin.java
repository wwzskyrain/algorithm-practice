/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.sort1;

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
 * @version : KClosestPointsToOrigin.java, v 0.1 2022年11月24日 22:18 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class KClosestPointsToOrigin {

    @LetCodeCommit(title = "973. K Closest Points to Origin",
            diff = "m",
            selfRemark = "终于写好出来了，大顶堆来请最小的k个数")
    public int[][] kClosest(int[][] points, int k) {
        HeapSort heapSort = new HeapSort(k);
        for (int[] point : points) {
            heapSort.adjust(point);
        }
        return Arrays.copyOfRange(heapSort.points, 1, heapSort.points.length);
    }

    public static class HeapSort {
        private int     n;
        private int[][] points;

        public HeapSort(int n) {
            this.n = n + 1;
            points = new int[this.n][2];
            for (int i = 0; i < points.length; i++) {
                points[i] = new int[] {10000, 10000};
            }
        }

        private void adjust(int[] point) {
            // 大顶堆
            int curV = value(point);
            if (value(points[1]) <= curV) {
                return;
            }
            int i = 1;
            for (; i < n; ) {
                int l = i * 2;
                int lV = l < n ? value(points[l]) : 0;
                int r = l + 1;
                int rV = r < n ? value(points[r]) : 0;
                if (curV > lV && curV > rV) {
                    break;
                }
                int nextMaxIndex = lV > rV ? l : r;
                points[i] = points[nextMaxIndex];
                i = nextMaxIndex;
            }
            points[i] = point;
        }

        private int value(int[] p) {
            return p[0] * p[0] + p[1] * p[1];
        }
    }

    @Parameter
    public int[][] points;
    @Parameter(1)
    public int     k;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension(" [[1,3],[-2,2],[2,-2]]"), 2},
                {ArrayUtils.buildArray2Dimension("[[1,3],[-2,2]]"), 1},
                {ArrayUtils.buildArray2Dimension("[[3,3],[5,-1],[-2,4]]"), 2}
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.deepToString(kClosest(points, k)));
    }

}