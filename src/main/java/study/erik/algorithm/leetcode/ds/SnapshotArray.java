/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.TreeMap;

/**
 * @author yueyi
 * @version : SnapshotArray.java, v 0.1 2023年03月04日 18:13 yueyi Exp $
 */
@LetCodeCommit(title = "1146. Snapshot Array",
        selfRemark = "还是无聊的设计题")
public class SnapshotArray {

    TreeMap<Integer, Integer>[] maps;
    int                         curSnapShot = 0;

    public SnapshotArray(int length) {
        maps = new TreeMap[length];
    }

    public void set(int index, int val) {
        TreeMap<Integer, Integer> map = maps[index];
        if (map == null) {
            map = new TreeMap<>();
            maps[index] = map;
        }
        map.put(curSnapShot, val);
    }

    public int snap() {
        curSnapShot++;
        return curSnapShot - 1;
    }

    public int get(int index, int snap_id) {
        TreeMap<Integer, Integer> map = maps[index];
        if (map == null) {
            return 0;
        }
        Integer floorKey = map.floorKey(snap_id);
        return floorKey == null ? 0 : map.get(floorKey);
    }

    public static void main(String[] args) {
        //["SnapshotArray","snap","get","get","set","get","set","get","set"]
        //[[2],[],[1,0],[0,0],[1,8],[1,0],[0,20],[0,0],[0,7]]
        SnapshotArray snapshotArray = new SnapshotArray(2);
        snapshotArray.snap();
        snapshotArray.get(1, 0);
        snapshotArray.get(0, 0);
        snapshotArray.set(1, 8);
        snapshotArray.get(1, 0);
        snapshotArray.set(0, 20);
        snapshotArray.get(0, 0);
        snapshotArray.set(0, 7);
    }

    public static void test1(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0, 5);
        snapshotArray.snap();
        snapshotArray.set(0, 6);
        int v = snapshotArray.get(0, 0);
        System.out.println(v);
    }
}