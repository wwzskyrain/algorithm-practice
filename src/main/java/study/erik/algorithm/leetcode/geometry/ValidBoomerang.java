/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.geometry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : ValidBoomerang.java, v 0.1 2022年12月10日 20:12 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ValidBoomerang {

    @LetCodeCommit(title = "1037. Valid Boomerang")
    public boolean isBoomerang(int[][] points) {
        List<Double> distances = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            int[] p1 = points[i];
            int[] p2 = points[(i + 1) % points.length];
            distances.add(Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1])));
        }
        Double delta = 1E-6;
        distances.sort(Double::compareTo);
        double c1 = distances.get(0);
        double c2 = distances.get(1);
        double c3 = distances.get(2);
        return Math.abs(c3 - c2 - c1) > delta;

    }

    @Parameter
    public int[][] points;
    @Parameter(1)
    public boolean except;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[0,0],[1,0],[2,2]]"), true},
                {ArrayUtils.buildArray2Dimension("[[1,1],[2,3],[3,2]]"), true},
                {ArrayUtils.buildArray2Dimension("[[1,1],[2,2],[3,3]]"), false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(except, isBoomerang(points));
    }
}