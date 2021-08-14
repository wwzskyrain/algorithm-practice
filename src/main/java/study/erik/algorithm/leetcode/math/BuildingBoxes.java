/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : BuildingBoxes.java, v 0.1 2021年08月14日 3:00 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BuildingBoxes {

    @LetCodeCommit(title = "Building Boxes")
    public int minimumBoxes(int n) {
        return resolve(n);
    }

    /**
     * 这是一个hard题，也是个数学题，或者说这是个找规律的题
     * https://leetcode.com/problems/building-boxes/discuss/1032016/C%2B%2B-Python-3-variables-solution-with-drawing-explanation
     * 这篇文章找规律找的特别好。
     * 我承认自己没有找到很好，虽然和这篇文章的思路一致，但不够深刻
     *
     *
     * @param n
     * @return
     */
    public int resolve(int n) {
        int cur = 0, i = 0, j = 0;
        while (cur < n) {
            ++j;
            i += j;
            cur += i;
        }
        if (cur == n) { return i; }
        cur -= i;
        i -= j;
        j = 0;
        while (cur < n) {
            ++j;
            cur += j;
        }
        return i + j;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3, 3,},
                {4, 3,},
                {10, 6,},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, minimumBoxes(n));
    }
}