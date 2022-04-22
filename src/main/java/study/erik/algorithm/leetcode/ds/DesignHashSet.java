/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DesignHashSet.java, v 0.1 2022年04月21日 9:50 下午 yueyi Exp $
 */
public class DesignHashSet {

    @LetCodeCommit(title = "705. Design HashSet",
            diff = "e",
            selfRemark = "哈哈，欺负这个easy——看代码就知道是如何的水了")
    public static class MyHashSet {

        boolean[] t = new boolean[1000001];

        public MyHashSet() {

        }

        public void add(int key) {
            t[key] = true;
        }

        public void remove(int key) {
            t[key] = false;
        }

        public boolean contains(int key) {
            return t[key];
        }
    }

}