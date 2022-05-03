/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yueyi
 * @version : AsteroidCollision.java, v 0.1 2022年05月03日 15:16 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class AsteroidCollision {

    @LetCodeCommit(title = "735. Asteroid Collision",
            selfRemark = "正觉得这会脑子不好使而打算休息呢，可一提交就过了!"
                    + "这道题目考什么？考栈的应用？")
    public int[] asteroidCollision(int[] asteroids) {
        // 优化，自己写栈.
        Deque<Integer> stack = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            if (stack.isEmpty()) {
                stack.addLast(asteroid);
                continue;
            }
            boolean needAddAsteroid = true;
            int peek;
            while (!stack.isEmpty() && (peek = stack.peekLast()) > 0 && asteroid < 0) {
                int sum = peek + asteroid;
                if (sum > 0) {
                    // 被吃.
                    needAddAsteroid = false;
                    break;
                } else if (sum == 0) {
                    // 一起消失
                    needAddAsteroid = false;
                    stack.pollLast();
                    break;
                } else {
                    // 吃掉栈顶元素，继续
                    stack.pollLast();
                }
            }
            if (needAddAsteroid) {
                stack.addLast(asteroid);
            }
        }

        int[] ret = new int[stack.size()];
        for (int i = ret.length - 1; i >= 0; i--) {
            ret[i] = stack.pollLast();
        }
        return ret;
    }

    @Parameter
    public int[] asteroids;
    @Parameter(1)
    public int[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[5,10,-5]"), ArrayUtils.buildArray("[5,10]")},
                {ArrayUtils.buildArray("[8,-8]"), ArrayUtils.buildArray("[]")},
                {ArrayUtils.buildArray("[10,2,-5]"), ArrayUtils.buildArray("[10]")},
        };
    }

    @Test
    public void test_() {
        Assert.assertArrayEquals(expect, asteroidCollision(asteroids));
    }
}