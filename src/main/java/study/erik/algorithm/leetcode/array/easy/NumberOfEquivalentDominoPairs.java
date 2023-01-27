/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : NumberOfEquivalentDominoPairs.java, v 0.1 2023年01月18日 23:20 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfEquivalentDominoPairs {

    @LetCodeCommit(title = "1128. Number of Equivalent Domino Pairs",
            diff = "e",
            selfRemark = "这个计数是有意思的。")
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> counter = new HashMap<>();
        int res = 0;
        for (int[] domino : dominoes) {
            int one = domino[0];
            int two = domino[1];
            int max = Math.max(one, two);
            int min = Math.min(one, two);
            int key = max * 4 * 1000 + min;
            Integer c = counter.getOrDefault(key, 0);
            res += c;
            counter.put(key, c + 1);
        }
        return res;
    }

    @Parameter
    public int[][] dominoes;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2],[2,1],[3,4],[5,6]]"), 1},
                {ArrayUtils.buildArray2Dimension("[[1,2],[1,2],[1,1],[1,2],[2,2]]"), 3},
                {ArrayUtils.buildArray2Dimension("[[1,2],[1,2],[1,2],[1,2],[1,2]]"), 10},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, numEquivDominoPairs(dominoes));
    }

}