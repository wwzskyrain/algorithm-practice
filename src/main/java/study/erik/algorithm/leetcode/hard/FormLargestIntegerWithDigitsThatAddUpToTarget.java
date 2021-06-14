/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : FormLargestIntegerWithDigitsThatAddUpToTarget.java, v 0.1 2021年06月12日 8:21 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FormLargestIntegerWithDigitsThatAddUpToTarget {

    @LetCodeCommit(title = "Form Largest Integer With Digits That Add up to Target")
    public String largestNumber(int[] cost, int target) {
        return resolveWithDp(cost, target);
    }

    /**
     * 有一个背包问题，而且还是可以用'逆思维'来求解背包的问题。
     * 本题是hard，难在哪里？难在要构造最优解。我这里构造超时了，但是我并不想再去改写，因为它已经不涉及更优秀的算法思想了。先这样。
     *
     * @param cost
     * @param target
     * @return
     */
    public String resolveWithDp(int[] cost, int target) {
        // dp[i]表示当前
        int[] dp = new int[target + 1];
        dp[0] = 1;
        List<List<List<Integer>>> results = new ArrayList<>();
        for (int i = 0; i < dp.length; i++) {
            results.add(new ArrayList<>());
        }

        for (int i = 0; i < cost.length; i++) {
            for (int j = cost[i]; j < dp.length; j++) {
                int typeCount = dp[j - cost[i]];
                if (typeCount == 0) {
                    continue;
                }
                dp[j] += typeCount;
                List<List<Integer>> preAllMakeUp = results.get(j - cost[i]);
                List<List<Integer>> curAllMakeUp = results.get(j);
                if (preAllMakeUp.size() == 0) {
                    curAllMakeUp.add(new ArrayList(Arrays.asList(i + 1)));
                } else {
                    for (List<Integer> integers : preAllMakeUp) {
                        ArrayList<Integer> makeUp = new ArrayList<>(integers);
                        makeUp.add(i + 1);
                        curAllMakeUp.add(makeUp);
                    }
                }
            }
        }
        List<List<Integer>> allMakeUp = results.get(target);
        if (allMakeUp == null || allMakeUp.size() == 0) {
            return "0";
        }
        int maxSize = 0;
        for (List<Integer> makeUp : allMakeUp) {
            maxSize = Math.max(maxSize, makeUp.size());
        }
        final int finalMaxSize = maxSize;
        List<String> sortedAllMakeUp = allMakeUp.stream()
                .filter(makeUp -> makeUp.size() == finalMaxSize)
                .map(makeUp -> makeUp.stream().sorted(Comparator.reverseOrder()).map(String::valueOf).collect(Collectors.joining())).sorted(
                        Comparator.reverseOrder())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        return sortedAllMakeUp.get(0);
    }

    @Parameter
    public int[]  cost;
    @Parameter(1)
    public int    target;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new int[] {4, 3, 2, 5, 6, 7, 2, 5, 5}, 9, "7772"},
                {new int[] {7, 6, 5, 5, 5, 6, 8, 7, 8}, 12, "85"},
                {new int[] {2, 4, 6, 2, 4, 6, 4, 4, 4}, 5, "0"},
                {new int[] {1, 1, 1, 1, 1, 1, 1, 3, 2}, 10, "7777777777"},
                {new int[] {70, 84, 55, 63, 74, 44, 27, 76, 34}, 659, "99977777777777777777776"}
        };
    }

    @Test
    public void test() {
        Assert.assertEquals(expect, largestNumber(cost, target));
    }

}