/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : MinimumTimeDifference.java, v 0.1 2022年03月24日 7:50 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumTimeDifference {

    @LetCodeCommit(title = "539. Minimum Time Difference",
            diff = "m",
            selfRemark = "一个弯弯，就是Mod比较大小")
    public int findMinDifference(List<String> timePoints) {
        int[] m = new int[timePoints.size()];
        for (int i = 0; i < m.length; i++) {
            String s = timePoints.get(i);
            String[] ss = s.split(":");
            m[i] = Integer.parseInt(ss[0]) * 60 + Integer.parseInt(ss[1]);
        }
        Arrays.sort(m);
        int Mod = 24 * 60;
        int preM = m[0];
        int min = 0x7ffffff;
        for (int i = 1; i <= m.length; i++) {
            int thisM = m[i % m.length];
            thisM = (thisM >= preM) ? thisM : thisM + Mod;
            min = Math.min(min, thisM - preM);
            preM = m[i % m.length];
        }
        return min;
    }

    @Parameter
    public List<String> timePoints;
    @Parameter(1)
    public int          expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {Arrays.asList("00:00", "23:59", "00:00"), 0},
                {Arrays.asList("23:50", "00:00", "22:50"), 10},
                {Arrays.asList("22:50", "00:00"), 70}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findMinDifference(timePoints));
    }

}