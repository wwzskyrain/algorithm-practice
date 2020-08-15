package study.erik.algorithm.leetcode.medium;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-08-14 09:01
 */
@LetCodeCommit(no = 146, title = "LRU Cache", diff = "m", related = {"LFU Cache"})
public class LRUCache {


    private Map<Integer, Integer> linkedHashMap;

    public LRUCache(int capacity) {
        linkedHashMap = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        Integer v = linkedHashMap.get(key);
        return v == null ? -1 : v;
    }

    public void put(int key, int value) {
        linkedHashMap.put(key, value);
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        // returns 1
        cache.put(3, 3);
        // evicts key 2
        System.out.println(cache.get(2));      // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }


}
