/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : PairsOfSongsWithTotalDurationsDivisibleBy60.java, v 0.1 2022年12月04日 16:34 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {

    @LetCodeCommit(title = "1010. Pairs of Songs With Total Durations Divisible by 60",
            diff = "m",
            selfRemark = "一遍过")
    public int numPairsDivisibleBy60(int[] time) {
        int[] map = new int[60];
        int c = 0;
        for (int t : time) {
            t %= 60;
            int p = (60 - t) % 60;
            c += map[p];
            map[t]++;
        }
        return c;
    }

    @Parameter
    public int[] time;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[30,20,150,100,40]"), 3},
                {ArrayUtils.buildArray("[60,60,60]"), 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numPairsDivisibleBy60(time));
    }
}