/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : CarFleetII.java, v 0.1 2023年07月02日 16:43 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CarFleetII {

    @LetCodeCommit(title = "1776. Car Fleet II")
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] res = new double[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            res[i] = -1.0;
            int position = cars[i][0];
            int speed = cars[i][1];
            while (stack.size() > 0) {
                // 要捉了。捉哪个呢？
                int j = stack.peekLast();
                int positionFront = cars[j][0];
                int speedFront = cars[j][1];
                double timeToCatchFront = 1.0 * (positionFront - position) / (speed - speedFront);
                if (speed <= speedFront
                        || timeToCatchFront >= res[j] && res[j] > 0) {
                    // 速度慢，肯定追不上；把前面哪个踢走，看看能不能追前面的前面。
                    // 速度大之后，肯定能追得上，就是花的时间是否比前面的追前面前面的时间多，如果多，那就不追前面的了，直接追前面前面的。
                    //  如果少，那就追前面的这个就行了。
                    stack.pollLast();
                } else {
                    res[i] = timeToCatchFront;
                    break;
                }
            }
            stack.add(i);
        }
        return res;
    }

    @Parameter
    public int[][] cars;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2],[2,1],[4,3],[7,2]]")},
                {ArrayUtils.buildArray2Dimension("[[3,4],[5,4],[6,3],[9,1]]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(getCollisionTimes(cars)));
    }
}