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
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ChampagneTower.java, v 0.1 2022年05月12日 07:36 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ChampagneTower {

    @LetCodeCommit(title = "799. Champagne Tower",
    diff = "m",
    selfRemark = "应用型好题。"
            + "模拟整个过程即可。不过我相信肯定有数学方法."
            + "")
    public double champagneTower(int poured, int query_row, int query_glass) {

        double[] res = new double[query_row + 2];
        res[0] = poured;
        for (int rowI = 1; rowI <= query_row; rowI++) {
            for (int j = rowI; j >= 0; j--) {
                res[j] = Math.max(0, (res[j] - 1) / 2);
                res[j + 1] += res[j];
            }
        }
        return Math.min(1, res[query_glass]);
    }

    @Parameter
    public int    poured;
    @Parameter(1)
    public int    query_row;
    @Parameter(2)
    public int    query_grass;
    @Parameter(3)
    public double expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, 1, 1, 0.00},
                {2, 1, 1, 0.5},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, champagneTower(poured, query_row, query_grass), 0.0002);
    }
}