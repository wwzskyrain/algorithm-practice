/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.geometry;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : MinimumAreaRectangle.java, v 0.1 2022年11月08日 09:36 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumAreaRectangle {

    @LetCodeCommit(title = "939. Minimum Area Rectangle",
            diff = "m",
            selfRemark = "思路清晰，实现巧妙")
    public int minAreaRect(int[][] points) {

        Map<Integer, List<Integer>> rows = new TreeMap<>();
        for (int[] point : points) {
            rows.computeIfAbsent(point[0], z -> new ArrayList<>()).add(point[1]);
        }
        int ans = Integer.MAX_VALUE;
        Map<String, Integer> lastX = new HashMap<>();
        for (Integer x : rows.keySet()) {
            List<Integer> yList = rows.get(x);
            yList.sort(Integer::compareTo);
            for (int i = 0; i < yList.size(); i++) {
                for (int j = i + 1; j < yList.size(); j++) {
                    Integer y1 = yList.get(i);
                    Integer y2 = yList.get(j);
                    String key = y1 + "_" + y2;
                    if (lastX.containsKey(key)) {
                        ans = Math.min(ans, (x - lastX.get(key)) * (y2 - y1));
                    }
                    lastX.put(key, x);
                }
            }
        }
        return ans < Integer.MAX_VALUE ? ans : 0;//0 表示没有这样的矩形
    }
}