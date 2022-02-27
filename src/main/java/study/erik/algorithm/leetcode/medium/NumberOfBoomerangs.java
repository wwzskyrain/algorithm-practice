/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : NumberOfBoomerangs.java, v 0.1 2022年02月26日 8:11 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfBoomerangs {

    @LetCodeCommit(title = "447. Number of Boomerangs")
    public int numberOfBoomerangs(int[][] points) {
        int ret = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> distance2CountMap = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                int disx = points[i][0] - points[j][0];
                int disy = points[i][1] - points[j][1];
                int distance = disx * disx + disy * disy;
                Integer count = distance2CountMap.getOrDefault(distance, 0);
                count++;
                distance2CountMap.put(distance, count);
            }
            for (Integer count : distance2CountMap.values()) {
                ret += count * (count - 1);
            }
        }
        return ret;
    }

    @Parameter
    public int[][] points;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[0,0],[1,0],[2,0]]"), 2},
                {ArrayUtils.buildArray2Dimension("[[1,1],[2,2],[3,3]]"), 2},
                {ArrayUtils.buildArray2Dimension("[[1,1]]"), 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numberOfBoomerangs(points));
    }
}