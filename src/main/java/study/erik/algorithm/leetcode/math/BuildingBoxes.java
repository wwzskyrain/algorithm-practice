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
     * @param n
     * @return
     */
    public int resolve(int n) {
        //这个都看不明白了，再写一个
        int cur = 0, i = 0, j = 0;
        while (cur < n) {
            ++j;
            i += j;
            cur += i;
        }
        if (cur == n) {return i;}
        cur -= i;
        i -= j;
        j = 0;
        while (cur < n) {
            ++j;
            cur += j;
        }
        return i + j;
    }

    public int resolveWithExplain(int n) {
        int boxOnFloor = 0;
        int totalBox = 0;
        //for的结束是两部分:boxOnFloor + 1表示在totalBox时，在不增加一个截面时，最多能(按规则)叠放的多少个box
        for (int i = 1; totalBox + (boxOnFloor + 1) <= n; i++) {
            //第一个for是规规矩矩的放到不能放
            // i可以看做靠墙的那一面是当前水平方向的第i个截面
            boxOnFloor += i;
            totalBox += boxOnFloor;
        }
        for (int j = 1; totalBox < n; j++) {
            //第二个for开始下(i)(i+1)(i+2)的基础上放，前提是不需是(i+1)(i+2)(i+3)，前提已经在第一个for达到了
            boxOnFloor++;
            totalBox += j;
        }
        return boxOnFloor;
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