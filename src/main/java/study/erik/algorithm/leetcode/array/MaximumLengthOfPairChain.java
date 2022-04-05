/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : MaximumLengthOfPairChain.java, v 0.1 2022年04月05日 3:21 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumLengthOfPairChain {

    @LetCodeCommit(title = "646. Maximum Length of Pair Chain",
            diff = "m",
            selfRemark = "很经典的dp题;"
                    + "加速优化失败")
    public int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int[] dp = new int[pairs.length];
        TreeSet<Integer> set = new TreeSet<>(
                (a, b) -> dp[a] != dp[b] ? dp[a] - dp[b] : a - b);
        set.add(0);
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            Iterator<Integer> iterator = set.descendingIterator();
            int[] pairI = pairs[i];
            while (iterator.hasNext()) {
                Integer index = iterator.next();
                int[] pairJ = pairs[index];
                if (pairI[0] > pairJ[1]) {
                    dp[i] = Math.max(dp[i], dp[index] + 1);
                    break;
                }
            }
            set.add(i);
        }
        return dp[dp.length - 1];

    }

    @Parameter
    public int[][] pairs;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[-6,9],[1,6],[8,10],[-1,4],[-6,-2],[-9,8],[-5,3],[0,3]]"), 3},
                {ArrayUtils.buildArray2Dimension("[[9,10],[-4,9],[-5,6],[-5,9],[8,9]]"), 2},
                {ArrayUtils.buildArray2Dimension("[[1,2],[2,3],[3,4]]"), 2},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findLongestChain(pairs));
    }

}