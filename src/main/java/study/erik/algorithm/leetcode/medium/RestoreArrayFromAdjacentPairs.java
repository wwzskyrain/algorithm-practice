/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yueyi
 * @version : RestoreArrayFromAdjacentPairs.java, v 0.1 2021年07月25日 9:39 上午 yueyi Exp $
 */
public class RestoreArrayFromAdjacentPairs {

    @LetCodeCommit(title = "Restore the Array From Adjacent Pairs")
    public int[] restoreArray(int[][] adjacentPairs) {
        return resolve(adjacentPairs);
    }

    public int[] resolve(int[][] adjacentPairs) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> headSet = new HashSet<>();
        for (int[] pair : adjacentPairs) {
            List<Integer> nextNums = map.getOrDefault(pair[0], new ArrayList<>());
            nextNums.add(pair[1]);
            map.put(pair[0], nextNums);
            nextNums = map.getOrDefault(pair[1], new ArrayList<>());
            nextNums.add(pair[0]);
            map.put(pair[1], nextNums);

            if (headSet.contains(pair[0])) {
                headSet.remove(pair[0]);
            } else {
                headSet.add(pair[0]);
            }
            if (headSet.contains(pair[1])) {
                headSet.remove(pair[1]);
            } else {
                headSet.add(pair[1]);
            }
        }

        Integer head = headSet.stream().findAny().get();
        Set<Integer> visited = new HashSet<>();

        int[] result = new int[adjacentPairs.length + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = head;
            visited.add(head);
            List<Integer> next = map.get(head);
            for (Integer integer : next) {
                if (!visited.contains(integer)) {
                    head = integer;
                }
            }
        }
        return result;
    }

    @Test
    public void test_adjacentPairs() {
        int[][] adjacentPairs = new int[][] {{2, 1}, {3, 4}, {3, 2}};
        int[] expect = new int[] {1, 2, 3, 4};
        Assert.assertArrayEquals(expect, restoreArray(adjacentPairs));
    }

    @Test
    public void test_adjacentPairs1() {
        int[][] adjacentPairs = new int[][] {{4, -2}, {1, 4}, {-3, 1}};
        int[] expect = new int[] {-2, 4, 1, -3};
        Assert.assertArrayEquals(expect, restoreArray(adjacentPairs));
    }

    @Test
    public void test_adjacentPairs2() {
        int[][] adjacentPairs = new int[][] {{-3, -9}, {-5, 3}, {2, -9}, {6, -3}, {6, 1}, {5, 3}, {8, 5}, {-5, 1}, {7, 2}};
        int[] expect = new int[] {7, 2, -9, -3, 6, 1, -5, 3, 5, 8};
        Assert.assertArrayEquals(expect, restoreArray(adjacentPairs));
    }

}