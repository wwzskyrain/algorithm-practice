/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.stack.hard;

import javafx.util.Pair;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yueyi
 * @version : MaxValueOfEquation.java, v 0.1 2023年05月14日 21:24 yueyi Exp $
 */
public class MaxValueOfEquation {

    @LetCodeCommit(title = "1499. Max Value of Equation",
            diff = "h",
            selfRemark = "经过简单的变形，就可以转化为滑动窗口+单调递减栈")
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<Pair<Integer, Integer>> ms = new ArrayDeque<>();
        int res = Integer.MIN_VALUE;
        for (int[] point : points) {
            while (!ms.isEmpty() && point[0] - ms.peekFirst().getValue() > k) {
                ms.pollFirst();
            }
            if (!ms.isEmpty()) {
                res = Math.max(res, ms.peekFirst().getKey() + point[0] + point[1]);
            }
            while (!ms.isEmpty() && point[1] - point[0] > ms.peekLast().getKey()) {
                ms.pollLast();
            }
            ms.offerLast(new Pair<>(point[1] - point[0], point[0]));
        }
        return res;
    }

}