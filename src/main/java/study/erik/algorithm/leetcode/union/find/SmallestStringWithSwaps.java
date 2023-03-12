/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.union.find;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : SmallestStringWithSwaps.java, v 0.1 2023年03月11日 22:50 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SmallestStringWithSwaps {

    @LetCodeCommit(title = "1202. Smallest String With Swaps")
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        int[] parent = new int[n];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (List<Integer> pair : pairs) {
            unionFind(parent, pair.get(0), pair.get(1));
        }

        Map<Integer, List<Integer>> parent2IndexGroupMap = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            int p = find(parent, i);
            List<Integer> indexGroup = parent2IndexGroupMap.getOrDefault(p, new ArrayList<>());
            indexGroup.add(i);
            parent2IndexGroupMap.putIfAbsent(p, indexGroup);
        }
        char[] arr = new char[n];
        for (List<Integer> indexGroup : parent2IndexGroupMap.values()) {
            List<Character> characters = indexGroup.stream().map(s::charAt).sorted().collect(Collectors.toList());
            indexGroup.sort(Integer::compare);
            for (int i = 0; i < indexGroup.size(); i++) {
                Integer index = indexGroup.get(i);
                Character c = characters.get(i);
                arr[index] = c;
            }
        }
        return String.valueOf(arr);
    }

    public void unionFind(int[] parent, int a, int b) {
        int aP = find(parent, a);
        int bP = find(parent, b);
        if (aP < bP) {
            parent[bP] = aP;
        } else {
            parent[aP] = bP;
        }
    }

    public int find(int[] parent, int x) {
        while (parent[x] != x) {
            //不带这一句优化，就超时了
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    @Parameter
    public String              s;
    @Parameter(1)
    public List<List<Integer>> pairs;
    @Parameter(2)
    public String              expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{"dcab", ArrayUtils.buildList2Dimension("[[0,3],[1,2]]"), "bacd"},
                {"dcab", ArrayUtils.buildList2Dimension("[[0,3],[1,2],[0,2]]"), "abcd"},
                {"cba", ArrayUtils.buildList2Dimension("[[0,1],[1,2]]"), "abc"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, smallestStringWithSwaps(s, pairs));
    }
}
