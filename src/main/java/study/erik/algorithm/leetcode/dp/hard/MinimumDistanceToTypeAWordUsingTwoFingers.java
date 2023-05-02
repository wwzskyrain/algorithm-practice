/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : MinimumDistanceToTypeAWordUsingTwoFingers.java, v 0.1 2023年04月30日 13:55 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumDistanceToTypeAWordUsingTwoFingers {

    @LetCodeCommit(title = "1320. Minimum Distance to Type a Word Using Two Fingers",
            selfRemark = "这个题目，我们独立做出来的，但是花了好久的时间，其中改了三版（想了大概5版本）。"
                    + "我看不懂lee的题解，实在是思维能力跟不上。"
                    + "我的这个解法，其实从一开始想的比较简单，但是实际上有有点不清楚，"
                    + "于是就加细节，越加越多，最后才把整个过程描述清楚，并通过。"
                    + "虽然AC了，但是效率很低，才5%")
    public int minimumDistance(String word) {
        int n = word.length();
        char[] chars = word.toCharArray();
        int[] preSum = new int[n];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + distance(chars[i - 1], chars[i]);
        }
        Map<String, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int d = preSum[i];
                d += dp(i, j, chars, map, n);
                d += preSum[j] - preSum[i + 1];
                min = Math.min(min, d);
            }
        }
        return min;
    }

    public String getKey(int i, int j) {
        return String.format("%d_%d", i, j);
    }

    public int dp(int i, int j, char[] chars, Map<String, Integer> map, int n) {
        String key = getKey(i, j);
        if (map.containsKey(key)) {return map.get(key);}
        int min = Integer.MAX_VALUE;
        if (j == n - 1) {
            min = 0;
        } else {
            int d1 = distance(chars[i], chars[j + 1]);
            int dp1 = dp(j, j + 1, chars, map, n);
            min = Math.min(min, d1 + dp1);

            d1 = distance(chars[j], chars[j + 1]);
            dp1 = dp(i, j + 1, chars, map, n);
            min = Math.min(min, d1 + dp1);
        }
        map.put(key, min);
        return min;
    }

    public int distance(char a, char b) {
        int aX = (a - 'A') % 6;
        int bX = (b - 'A') % 6;

        int aY = (a - 'A') / 6;
        int bY = (b - 'A') / 6;

        int ret = aX > bX ? (aX - bX) : (bX - aX);
        ret += aY > bY ? (aY - bY) : (bY - aY);
        return ret;
    }

    @Parameter
    public String word;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"IBZR", 6},
                {"QIBZR", 7},
                {"YEAR", 7},
                {"CAKE", 3},
                {"HAPPY", 6},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minimumDistance(word));
    }
}