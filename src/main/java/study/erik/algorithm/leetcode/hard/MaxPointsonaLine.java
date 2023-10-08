/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

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
 * @version : MaxPointsonaLine.java, v 0.1 2021年12月19日 9:05 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaxPointsonaLine {

    @LetCodeCommit(title = "149. Max Points on a Line",
            selfRemark = "这个hard题做的真难受，其实就是个数学题，这里注意一下三点："
                    + "1.用除了gcd的dx、dy做为key"
                    + "2.这里是他们的一个必要充分条件，可惜我只能看到必要条件（好像不是的，我们也没有用到它的充分性）"
                    + "3.这里的gcd函数不是数据上的那种，而只是为了求得一个最大的共除数，让dx和dy的同比缩小，而且缩小的最小")
    public int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        int ret = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int max = 0;
            //从任何一个点出发，看其他点和它构成的线，有多少条是同一个斜率，斜率相同则处在同一条直线上。
            for (int j = i + 1; j < points.length; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;
                //这里能这样写，还有一个暗含的条件，那就是有坐标都是整数。
                String key = dx + "_" + dy;
                Integer c = map.getOrDefault(key, 0);
                c++;
                map.put(key, c);
                max = Math.max(max, c);
            }
            ret = Math.max(ret, max + 1);
        }
        return ret;
    }

    /**
     * 这里并不是数学上的最大公约数，因为数学上不存在负数情况。这里包含了负数情况。
     *
     * @param x
     * @param y
     * @return
     */
    public int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }

    @Parameter
    public int[][] points;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {ArrayUtils.buildArray2Dimension("[[1,1],[2,2],[3,3]]"), 3},
                {ArrayUtils.buildArray2Dimension("[[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]"), 4},
                };
    }

    @Test
    public void test_() {
        //System.out.println(gcd(1, -1));
        //System.out.println(gcd(-1, 1));
        //System.out.println(gcd(-4, 2));
        //System.out.println(gcd(4, -2));
        //System.out.println(gcd(-2, 4));
        //System.out.println(gcd(2, -4));

        Assert.assertEquals(expect, maxPoints(points));
    }

}