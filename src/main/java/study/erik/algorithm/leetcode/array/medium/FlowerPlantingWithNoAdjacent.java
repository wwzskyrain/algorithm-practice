/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : FlowerPlantingWithNoAdjacent.java, v 0.1 2022年12月18日 08:40 yueyi Exp $
 */
public class FlowerPlantingWithNoAdjacent {

    @LetCodeCommit(title = "1042. Flower Planting With No Adjacent")
    public int[] gardenNoAdj(int n, int[][] paths) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] path : paths) {
            map.get(path[0]-1).add(path[1]-1);
            map.get(path[1]-1).add(path[0]-1);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int[] colors = new int[5];
            List<Integer> adjacent = map.get(i);
            for (Integer garden : adjacent) {
                colors[res[garden]] = 1;
            }
            for (int j = 4; j > 0; j--) {
                if (colors[j] == 0) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }

}