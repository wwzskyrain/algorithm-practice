/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.stone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : StoneGameVIII.java, v 0.1 2021年06月19日 10:50 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StoneGameVIII {

    @LetCodeCommit(title = "Stone Game VIII")
    public int stoneGameVIII(int[] stones) {

        // 这个就有点难了
        return 0;
    }

    @Parameterized.Parameter
    public int[] stones;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {-1, 2, -3, 4, -5}, 5},
                {new int[] {7, -6, 5, 10, 5, -2, -6}, 13},
                {new int[] {-10, -12}, -22}
        };
    }

    @Test
    public void test() {
        Assert.assertEquals(expect, stoneGameVIII(stones));
    }
}