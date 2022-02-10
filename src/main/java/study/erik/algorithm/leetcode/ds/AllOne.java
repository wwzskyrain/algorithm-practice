package study.erik.algorithm.leetcode.ds;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

class AllOne {

    private Map<String, Integer>              keyValueMap = new HashMap<>();
    private TreeMap<Integer, HashSet<String>> counterMap  = new TreeMap<>();

    public AllOne() {

    }

    BiConsumer<Integer, String> appendKeyToCounter = (count, key) -> {
        HashSet<String> set = counterMap.getOrDefault(count, new HashSet<>());
        counterMap.put(count, set);
        set.add(key);
    };

    BiConsumer<Integer, String> removeKey = (count, key) -> {
        HashSet<String> counterSet = counterMap.get(count);
        counterSet.remove(key);
        if (counterSet.isEmpty()) {
            counterMap.remove(count);
        }
    };

    public void inc(String key) {
        Integer counter = keyValueMap.get(key);

        if (counter == null) {
            keyValueMap.put(key, 1);
            appendKeyToCounter.accept(1, key);
            return;
        }
        keyValueMap.put(key, counter + 1);
        removeKey.accept(counter, key);
        appendKeyToCounter.accept(counter + 1, key);

    }

    public void dec(String key) {
        Integer counter = keyValueMap.get(key);
        if (counter == 1) {
            keyValueMap.remove(key);
            removeKey.accept(counter, key);
            return;
        }
        keyValueMap.put(key, counter - 1);
        removeKey.accept(counter, key);
        appendKeyToCounter.accept(counter - 1, key);

    }

    public String getMaxKey() {
        if (counterMap.isEmpty()) {
            return "";
        }
        Iterator<String> maxIterator = counterMap.lastEntry().getValue().iterator();
        if (maxIterator.hasNext()) {
            return maxIterator.next();
        }
        return "";
    }

    public String getMinKey() {
        if (counterMap.isEmpty()) {return "";}
        Iterator<String> minIterator = counterMap.firstEntry().getValue().iterator();
        if (minIterator.hasNext()) {
            return minIterator.next();
        }
        return "";
    }

    public static void test1() {
        AllOne allOne = new AllOne();
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.dec("b");
        allOne.dec("b");
        System.out.println(allOne.getMinKey()); //[a]
        allOne.dec("a");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

    public static void test_2() {
        //["AllOne","getMaxKey","getMinKey"]
        //[[],[],[]]
        AllOne allOne = new AllOne();
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());

    }

    public static void test_3() {
        AllOne allOne = new AllOne();
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("d");
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("d");
        allOne.inc("c");
        allOne.inc("d");
        allOne.inc("d");
        allOne.inc("a");

        System.out.println(allOne.getMinKey()); //[a]
    }

    public static void main(String[] args) {
        test1();
        test_2();
        test_3();
    }

}