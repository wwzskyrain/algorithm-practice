/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yueyi
 * @version : BusRoutes.java, v 0.1 2022年06月29日 09:25 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BusRoutes {

    @LetCodeCommit(title = "815. Bus Routes",
            diff = "h",
            selfRemark = "最短路径题目。去年写的代码，为啥都不注释呢？" +
                    "怪不得不写注释，因为确实就只有一个考点：BFS。")
    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target) {
            return 0;
        }
        //公交路线映射，key：公交路线（从0开始编号）；value：可以换成到的公交路线集合。
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            Arrays.sort(routes[i]);
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < routes.length; i++) {
            int[] routeI = routes[i];
            for (int j = i + 1; j < routes.length; j++) {
                int[] routeJ = routes[j];
                for (int stop : routeJ) {
                    if (Arrays.binarySearch(routeI, stop) >= 0) {
                        map.get(i).add(j);
                        map.get(j).add(i);
                        break;
                    }
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> q = new LinkedList<>();
        Set<Integer> targetBus = new HashSet<>();
        for (int i = 0; i < routes.length; i++) {
            int[] routeI = routes[i];
            if (Arrays.binarySearch(routeI, source) >= 0) {
                visited.add(i);
                q.add(i);
            }
            if (Arrays.binarySearch(routeI, target) >= 0) {
                targetBus.add(i);
            }
        }
        int time = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                Integer bus = q.pop();
                size--;
                if (targetBus.contains(bus)) {
                    return time;
                }
                List<Integer> nextBuses = map.get(bus);
                for (Integer nextBus : nextBuses) {
                    if (visited.contains(nextBus)) {
                        continue;
                    }
                    visited.add(nextBus);
                    q.add(nextBus);
                }
            }
            time++;
        }
        return -1;
    }

    @Parameter
    public int[][] routes;
    @Parameter(1)
    public int     source;
    @Parameter(2)
    public int     target;
    @Parameter(3)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,7],[3,5]]"), 5, 5, 0},
                {ArrayUtils.buildArray2Dimension("[[1,2,7],[3,6,7]]"), 1, 6, 2},
                {ArrayUtils.buildArray2Dimension("[[7,12],[4,5,15],[6],[15,19],[9,12,13]]"), 15, 12, -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numBusesToDestination(routes, source, target));
    }

}