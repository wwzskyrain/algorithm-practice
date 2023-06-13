/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : CountAllPossibleRoutes.java, v 0.1 2023年05月29日 10:18 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1575. Count All Possible Routes",
        selfRemark = "思路没问题，也ac了，就是成绩不好7%。"
                + "有待提高的是可以用dp数组的，而且数组是locations的长度和fuel，不会太多辅助空间的。"
                + "不优化了，下一道题")
public class CountAllPossibleRoutes {

    private int MOD = 1_000_000_007;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int startValue = locations[start];
        int finishValue = locations[finish];
        Arrays.sort(locations);
        int startIndex = Arrays.binarySearch(locations, startValue);
        int finishIndex = Arrays.binarySearch(locations, finishValue);
        return dfs(locations, startIndex, finishIndex, fuel, new HashMap<>());
    }

    public int dfs(int[] locations, int sIdx, int fIdx, int fuel, Map<String, Integer> map) {
        int n = locations.length;
        int s = locations[sIdx];
        int f = locations[fIdx];
        if (fuel < Math.abs(s - f)) {
            return 0;
        }
        String key = String.format("%s_%s", sIdx, fuel);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int total = 0;
        if (sIdx == fIdx) {
            total++;
        }
        if (fuel < 0) {
            map.put(key, total);
            return total;
        }

        int right = sIdx + 1;

        while (right < n) {
            int newFuel = fuel - (locations[right] - locations[sIdx]);
            if (newFuel < 0) {
                break;
            }
            total = (total + dfs(locations, right, fIdx, newFuel, map)) % MOD;
            right++;
        }
        int left = sIdx - 1;
        while (left >= 0) {
            int newFuel = fuel - (locations[sIdx] - locations[left]);
            if (newFuel < 0) {
                break;
            }
            total = (total + dfs(locations, left, fIdx, newFuel, map)) % MOD;
            left--;
        }
        map.put(key, total);
        return total;
    }

    @Parameter
    public int[] locations;
    @Parameter(1)
    public int   start;
    @Parameter(2)
    public int   finish;
    @Parameter(3)
    public int   fuel;
    @Parameter(4)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3]"), 0, 2, 40, 615088286},
                {ArrayUtils.buildArray("[4,3,1]"), 1, 0, 6, 5},
                {ArrayUtils.buildArray("[5,2,1]"), 0, 2, 3, 0},
                {ArrayUtils.buildArray("[2,3,6,8,4]"), 1, 3, 5, 4},
                {ArrayUtils.buildArray("[2,3,6,8,4]"), 1, 3, 5, 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, countRoutes(locations, start, finish, fuel));
    }

}