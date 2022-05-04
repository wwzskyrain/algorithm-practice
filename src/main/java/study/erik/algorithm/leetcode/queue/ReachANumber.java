/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.queue;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : ReachANumber.java, v 0.1 2022年05月04日 10:31 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ReachANumber {

    @LetCodeCommit(title = "754. Reach a Number",
            selfRemark = "这是一个计算题.解法也是贪心算法，而暂无证明。")
    public int reachNumber(int target) {
        target = Math.abs(target);
        long sum = 0;
        long step = 0;
        while (sum < target) {
            step++;
            sum += step;
        }
        while ((sum - target) % 2 == 1) {
            step++;
            sum += step;
        }
        return ((int) step);
    }

    /**
     * 超时.
     *
     * @param target
     * @return
     */
    public int solution1(int target) {

        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(0, 0));
        while (!q.isEmpty()) {
            Pair<Integer, Integer> poll = q.poll();
            if (poll.getKey() == target) {
                return poll.getValue();
            }
            Integer nextStep = poll.getValue() + 1;
            int nextKey1 = poll.getKey() + nextStep;
            q.add(new Pair<>(nextKey1, nextStep));

            int nextKey2 = poll.getKey() - nextStep;
            q.add(new Pair<>(nextKey2, nextStep));

        }
        return -1;
    }

    @Parameter
    public int target;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {-1000000000, 44723},
                {5, 5},
                {2, 3},
                {3, 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, reachNumber(target));
    }
}
