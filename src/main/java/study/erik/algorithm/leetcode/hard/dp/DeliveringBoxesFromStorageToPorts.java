/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yueyi
 * @version : 从仓库到码头运输箱子.java, v 0.1 2023年06月18日 06:46 yueyi Exp $
 */
@LetCodeCommit(title = "1687. Delivering Boxes from Storage to Ports")
public class DeliveringBoxesFromStorageToPorts {

    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {

        int n = boxes.length;

        long[] ws = new long[n + 1];
        long[] cs = new long[n];
        for (int i = 0; i < n; i++) {
            int p = boxes[i][0], w = boxes[i][1];
            ws[i + 1] = ws[i] + w;
            if (i < n - 1) {
                cs[i + 1] = cs[i] + (p != boxes[i + 1][0] ? 1 : 0);
            }
        }

        long[] f = new long[n + 1];
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        for (int i = 1; i <= n; ++i) {
            //优先级队列
            while (!q.isEmpty() && (i - q.peekFirst() > maxBoxes || ws[i] - ws[q.peekFirst()] > maxWeight)) {
                q.pollFirst();
            }
            if (!q.isEmpty()) {
                f[i] = cs[i - 1] + f[q.peekFirst()] - cs[q.peekFirst()] + 2;
            }
            if (i < n) {
                while (!q.isEmpty() && f[q.peekLast()] - cs[q.peekLast()] >= f[i] - cs[i]) {
                    q.pollLast();
                }
                q.offer(i);
            }
        }
        return ((int) f[n]);
    }
}