/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.interval;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : MinimumIntervalToIncludeEachQuery.java, v 0.1 2023年07月08日 10:41 yueyi Exp $
 */
public class MinimumIntervalToIncludeEachQuery {

    @LetCodeCommit(title = "1851. Minimum Interval to Include Each Query")
    public int[] minInterval(int[][] intervals, int[] queries) {
        TreeMap<Integer, Integer> sizeToRightMap = new TreeMap<>();
        HashMap<Integer, Integer> indexToResultMap = new HashMap<>();
        int i = 0, n = intervals.length, m = queries.length;
        int[] Q = queries.clone(), res = new int[m];
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(Q);
        for (int queryIndex : Q) {
            while (i < n && intervals[i][0] <= queryIndex) {
                int l = intervals[i][0], r = intervals[i++][1];
                sizeToRightMap.put(r - l + 1, r);
            }
            while (!sizeToRightMap.isEmpty() && sizeToRightMap.firstEntry().getValue() < queryIndex) {
                //sizeToRightMap是按照size大小从小到大的。删除掉不覆盖的interval
                sizeToRightMap.pollFirstEntry();
            }
            //得到到第一个就是最优解
            indexToResultMap.put(queryIndex, sizeToRightMap.isEmpty() ? -1 : sizeToRightMap.firstKey());
        }
        for (int j = 0; j < queries.length; j++) {
            int queryIndex = queries[j];
            res[j] = indexToResultMap.get(queryIndex);
        }
        return res;
    }

}