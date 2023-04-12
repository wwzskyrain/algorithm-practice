/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bfs.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : MaximumCandiesYouCanGetFromBoxes.java, v 0.1 2023年04月08日 10:25 yueyi Exp $
 */
public class MaximumCandiesYouCanGetFromBoxes {

    @LetCodeCommit(title = "1298. Maximum Candies You Can Get from Boxes",
            selfRemark = "这个题目其实不难，bfs的思路一下就能想到。"
                    + "但是题目有一个巧妙的解法，才有价值的。"
                    + "我这里用了未操作，来标识box的状态，第0位标识open/close，第1位标识有无key，第2位标识有无box。"
                    + "但是未操作不及lee大神解法中的大数操作，关键点是未操作是幂等的，而大数操作是不幂等的。"
                    + "所以采用未操作，则需要另外有一个全局的visited集合，或者把status统一做赋值-1并统计检查处理。")
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int ret = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < initialBoxes.length; i++) {
            int initialBox = initialBoxes[i];
            if ((status[initialBox] |= 4) == 5) {
                q.add(initialBox);
                status[initialBox] = -1;
            }
        }
        // 已有的box其状态都加上了5000
        while (!q.isEmpty()) {
            Integer box = q.remove();
            ret += candies[box];
            for (int key : keys[box]) {
                if (status[key] < 0) {
                    continue;
                }
                status[key] |= 2;
                if (status[key] > 4) {
                    q.add(key);
                    status[key] = -1;
                }
            }
            for (int containedBox : containedBoxes[box]) {
                if (status[containedBox] == -1) {
                    continue;
                }
                status[containedBox] |= 4;
                if (status[containedBox] > 4) {
                    q.add(containedBox);
                    status[containedBox] = -1;
                }
            }
        }
        return ret;
    }

    public int maxCandiesByLee215(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int res = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i : initialBoxes) {if ((status[i] += 5000) > 5000) {q.add(i);}}
        while (q.size() > 0) {
            int b = q.remove();
            res += candies[b];
            for (int i : keys[b]) {
                if ((status[i] += 5) == 5005)
                // 这一点可以处理重复的key的情况，而上一个解法则不行
                // 本质是大数加操作是不幂等的，是累加的，而未操作则是幂等的。
                {q.add(i);}
            }
            for (int i : containedBoxes[b]) {if ((status[i] += 5000) > 5000) {q.add(i);}}
        }
        return res;
    }
}