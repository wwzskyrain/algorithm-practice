/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author yueyi
 * @version : LFUCache.java, v 0.1 2022年02月08日 7:52 下午 yueyi Exp $
 */
public class LFUCache {

    private int                                  capacity;
    private Map<Integer, Integer>                counterMap     = new HashMap<>();
    private Map<Integer, Integer>                keyValueMap    = new HashMap<>();
    private Map<Integer, LinkedHashSet<Integer>> counterListMap = new HashMap<>();
    private int                                  minCounter     = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!keyValueMap.containsKey(key)) {
            return -1;
        }
        Integer counter = counterMap.getOrDefault(key, 0);
        counterMap.put(key, counter + 1);
        LinkedHashSet<Integer> counterSet = counterListMap.getOrDefault(counter, new LinkedHashSet<>());
        counterSet.remove(key);
        if (counter == minCounter && counterSet.isEmpty()) {
            minCounter++;
        }

        LinkedHashSet<Integer> counterAddOneSet = counterListMap.getOrDefault(counter + 1, new LinkedHashSet<>());
        counterAddOneSet.add(key);
        counterListMap.put(counter + 1, counterAddOneSet);

        return keyValueMap.get(key);
    }

    public void put(int key, int value) {
        if (this.capacity == 0) {
            return;
        }
        if (keyValueMap.containsKey(key)) {
            keyValueMap.put(key, value);
            get(key);
            return;
        }
        // 插入
        if (keyValueMap.size() == capacity) {
            LinkedHashSet<Integer> minCounterSet = counterListMap.get(minCounter);
            Integer firstKey = minCounterSet.iterator().next();
            minCounterSet.remove(firstKey);
            keyValueMap.remove(firstKey);
            counterMap.remove(firstKey);
        }
        keyValueMap.put(key, value);
        counterMap.put(key, 1);
        minCounter = 1;
        LinkedHashSet<Integer> oneSet = counterListMap.getOrDefault(minCounter, new LinkedHashSet<>());
        oneSet.add(key);
        counterListMap.put(1, oneSet);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

}