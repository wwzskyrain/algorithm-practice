/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : PerfectRectangle.java, v 0.1 2022年02月15日 10:04 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PerfectRectangle {

    @LetCodeCommit(title = "391. Perfect Rectangle")
    public boolean isRectangleCover(int[][] rectangles) {

        Set<String> pointSet = new HashSet<>();
        int minLeft = Integer.MAX_VALUE;
        int minBottom = Integer.MAX_VALUE;
        int maxRight = Integer.MIN_VALUE;
        int maxTop = Integer.MIN_VALUE;

        Long totalArea = 0L;
        for (int[] rectangle : rectangles) {
            minLeft = Math.min(minLeft, rectangle[0]);
            minBottom = Math.min(minBottom, rectangle[1]);
            maxRight = Math.max(maxRight, rectangle[2]);
            maxTop = Math.max(maxTop, rectangle[3]);

            totalArea += (long) (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);

            String point = formatPoint(rectangle[0], rectangle[1]);
            if (!pointSet.add(point)) {
                pointSet.remove(point);
            }
            point = formatPoint(rectangle[0], rectangle[3]);
            if (!pointSet.add(point)) {
                pointSet.remove(point);
            }
            point = formatPoint(rectangle[2], rectangle[1]);
            if (!pointSet.add(point)) {
                pointSet.remove(point);
            }
            point = formatPoint(rectangle[2], rectangle[3]);
            if (!pointSet.add(point)) {
                pointSet.remove(point);
            }
        }

        return pointSet.contains(formatPoint(minLeft, minBottom)) &&
                pointSet.contains(formatPoint(minLeft, maxTop)) &&
                pointSet.contains(formatPoint(maxRight, minBottom)) &&
                pointSet.contains(formatPoint(maxRight, maxTop)) &&
                pointSet.size() == 4 &&
                totalArea == (long) (maxRight - minLeft) * (maxTop - minBottom);
    }

    private String formatPoint(Integer x, Integer y) {
        return String.format("%d_%d", x, y);
    }

    public boolean isRectangleCover1(int[][] rectangles) {

        Set<int[]> rectSet = new TreeSet<>((r1, r2) -> {
            if (r1[1] >= r2[3]) {
                return 1;
            } else if (r1[3] <= r2[1]) {
                return -1;
            } else if (r1[2] <= r2[0]) {
                return -1;
            } else if (r1[0] >= r2[2]) {
                return 1;
            } else {
                // 两个矩形有相交了。这种case都表示是相同矩形，会导致add失败。
                return 0;
            }
        });

        int left = Integer.MAX_VALUE, bottom = Integer.MAX_VALUE, right = Integer.MIN_VALUE, top = Integer.MIN_VALUE, area = 0;
        for (int[] rect : rectangles) {
            if (!rectSet.add(rect)) {
                // add的返回值为false，但是add这个动作还是成功的，
                // 其值会被替换的，虽然比较值是相同的，但是真实的key已经不同。
                // 有交集，就一定不是perfect_rectangle了
                return false;
            }
            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);

            right = Math.max(right, rect[2]);
            left = Math.min(left, rect[0]);
            top = Math.max(top, rect[3]);
            bottom = Math.min(bottom, rect[1]);
        }
        return (right - left) * (top - bottom) == area;
    }

    @Parameter
    public int[][] rectangles;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]"), true},
                {ArrayUtils.buildArray2Dimension("[[1,1,2,3],[1,3,2,4],[3,1,4,2],[3,2,4,4]]"), false},
                {ArrayUtils.buildArray2Dimension("[[1,1,3,3],[3,1,4,2],[1,3,2,4],[2,2,4,4]]"), false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, isRectangleCover(rectangles));
    }

}