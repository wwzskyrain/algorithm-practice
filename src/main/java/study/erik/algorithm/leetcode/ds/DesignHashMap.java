/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DesignHashMap.java, v 0.1 2022年04月21日 9:55 下午 yueyi Exp $
 */
public class DesignHashMap {

    @LetCodeCommit(title = "706. Design HashMap",
            diff = "e",
            selfRemark = "再水一个")
    class MyHashMap {

        int[] t;

        public MyHashMap() {
            this.t = new int[1000001];
        }

        public void put(int key, int value) {
            t[key] = value + 1;
        }

        public int get(int key) {
            return t[key] - 1;
        }

        public void remove(int key) {
            t[key] = 0;
        }
    }
}