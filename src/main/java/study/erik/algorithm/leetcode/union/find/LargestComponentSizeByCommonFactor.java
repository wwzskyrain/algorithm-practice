/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
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

/**
 * @author yueyi
 * @version : LargestComponentSizeByCommonFactor.java, v 0.1 2022年11月19日 11:37 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LargestComponentSizeByCommonFactor {

    @LetCodeCommit(title = "952. Largest Component Size by Common Factor",
            diff = "h",
            selfRemark = "思路很清晰，找联通分量，然后用并查集找最大size即可。"
                    + "但是这里容易超时，那就是在找联通关系的时候，如果对num来一个两层判断，就会超时。"
                    + "所以这里有一个小技巧，那就是反过来，先把nums中的每个num归类到以其质因数为首的分组中，"
                    + "比如num=6，则会落入2、3两个簇中；num=30，则会落入2、3、5三个簇中。"
                    + "同簇之间是可以union的，union的数据是num的index")
    public int largestComponentSize(int[] nums) {
        int M = 20010;
        int n = nums.length;
        // parent中存放nums的index
        int[] parent = new int[M];
        int[] size = new int[M];
        for (int i = 0; i < M; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            for (int j = 2; j * j <= num; j++) {
                if (num % j == 0) {
                    put(map, j, i);
                }
                while (num % j == 0) {
                    num = num / j;
                }
            }
            if (num > 1) {
                put(map, num, i);
            }
        }

        map.forEach((k, v) -> {
            for (int i = 0; i < v.size() - 1; i++) {
                union(v.get(i), v.get(i + 1), parent, size);
            }
        });

        int maxSize = 0;
        for (int s : size) {
            maxSize = Math.max(maxSize, s);
        }
        return maxSize;
    }

    public void put(Map<Integer, List<Integer>> map, int k, int v) {
        List<Integer> list = map.getOrDefault(k, new ArrayList<>());
        list.add(v);
        map.put(k, list);
    }

    private int find(int x, int[] parent) {
        if (x != parent[x]) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }

    private void union(int x, int y, int[] parent, int[] size) {
        int xParent = find(x, parent);
        int yParent = find(y, parent);
        if (xParent != yParent) {
            // xp挂到yp上
            parent[xParent] = yParent;
            int xParentSize = size[xParent];
            int yParentSize = size[yParent];
            size[xParent] = size[yParent] = xParentSize + yParentSize;
        }
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[4,6,15,35]"), 4},
                {ArrayUtils.buildArray("[20,50,9,63]"), 2},
                {ArrayUtils.buildArray("[2,3,6,7,4,12,21,39]"), 8},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, largestComponentSize(nums));
    }

}