/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.stack;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.List;
import java.util.Stack;

/**
 * @author yueyi
 * @version : KeysAndRooms.java, v 0.1 2022年05月22日 08:39 yueyi Exp $
 */
public class KeysAndRooms {

    @LetCodeCommit(title = "841. Keys and Rooms",
            diff = "m",
            selfRemark = "bfs即可解决。这里用stack实现树的先序遍历.")
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        boolean[] v = new boolean[rooms.size()];
        Stack<List<Integer>> s = new Stack<>();
        s.push(rooms.get(0));
        v[0] = true;
        while (!s.isEmpty()) {
            // 先序遍历
            List<Integer> pop = s.pop();
            for (Integer i : pop) {
                if (!v[i]) {
                    v[i] = true;
                    s.push(rooms.get(i));
                }
            }
        }
        for (boolean b : v) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}