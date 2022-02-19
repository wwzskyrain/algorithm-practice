package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
@LetCodeCommit(title = "")
class RandomizedCollection {

    private Random                     random = new Random(System.currentTimeMillis());
    private Map<Integer, Set<Integer>> value2IndicesMap;
    private ArrayList<Integer>         allValue;

    public RandomizedCollection() {
        value2IndicesMap = new HashMap<>();
        allValue = new ArrayList<>();
    }

    public boolean insert(int val) {
        Set<Integer> indices = value2IndicesMap.getOrDefault(val, new HashSet<>());
        allValue.add(val);
        indices.add(allValue.size() - 1);
        value2IndicesMap.put(val, indices);
        return indices.size() == 1;
    }

    public boolean remove(int val) {
        Set<Integer> indices = value2IndicesMap.getOrDefault(val, new HashSet<>());
        if (indices.size() == 0) {
            return false;
        }
        Integer oneIndex = indices.iterator().next();
        indices.remove(oneIndex);
        if (indices.size() == 0) {
            value2IndicesMap.remove(val);
        }

        int lastIndex = allValue.size() - 1;
        Integer lastValue = allValue.get(lastIndex);
        allValue.set(oneIndex, lastValue);
        Set<Integer> indicesOfLastValue = value2IndicesMap.get(lastValue);
        if (indicesOfLastValue != null) {
            indicesOfLastValue.remove(lastIndex);
            indicesOfLastValue.add(oneIndex);
        }// else 刚好oneIndex就是lastIndex
        allValue.remove(lastIndex);

        return true;
    }

    public int getRandom() {
        return allValue.get(random.nextInt(allValue.size()));
    }

    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        RandomizedCollection collection = new RandomizedCollection();
        System.out.println(collection.insert(1));
        System.out.println(collection.insert(1));
        System.out.println(collection.insert(2));
        System.out.println(collection.getRandom());
        System.out.println(collection.remove(1));
        System.out.println(collection.getRandom());
    }

    public static void test2() {
        RandomizedCollection collection = new RandomizedCollection();
        System.out.println(collection.insert(1));
        System.out.println(collection.remove(2));
        System.out.println(collection.insert(2));
        System.out.println(collection.getRandom());
        System.out.println(collection.remove(1));
        System.out.println(collection.insert(2));
        System.out.println(collection.getRandom());
    }
}