/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : TimeBasedKey_ValueStore.java, v 0.1 2022年11月26日 22:04 yueyi Exp $
 */
@LetCodeCommit(title = "981. Time Based Key-Value Store")
public class TimeBasedKey_ValueStore {

    public static class TimeMap {

        Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> treeMap = map.getOrDefault(key, new TreeMap<>());
            treeMap.put(timestamp, value);
            map.put(key, treeMap);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }
            TreeMap<Integer, String> treeMap = map.get(key);
            Integer floorKey = treeMap.floorKey(timestamp);
            return floorKey != null ? treeMap.get(floorKey) : "";
        }
    }

    public static void main(String[] args) {
        /**
         * ["TimeMap", "set", "get", "get", "set", "get", "get"]
         * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
         */

        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        System.out.println(timeMap.get("foo", 1));
        System.out.println(timeMap.get("foo", 3));
        timeMap.set("foo", "bar2", 4);
        System.out.println(timeMap.get("foo", 4));
        System.out.println(timeMap.get("foo", 5));

    }

}