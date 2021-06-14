/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LastStoneWeightII.java, v 0.1 2021年06月08日 8:12 上午 yueyi Exp $
 */
public class LastStoneWeightII {

    @LetCodeCommit(title = "Last Stone Weight II")
    public int lastStoneWeightII(int[] stones) {
        return resolveWithTree(stones, 0, 0, Integer.MAX_VALUE);
    }

    /**
     * 超时了，算法本身是没问题的。
     *
     * @param stones
     * @param index
     * @param curResult
     * @param bestResult
     * @return
     */
    private int resolveWithTree(int[] stones, int index, int curResult, int bestResult) {
        if (index == stones.length - 1) {
            bestResult = Math.min(Math.abs(curResult + stones[index]), bestResult);
            bestResult = Math.min(Math.abs(curResult - stones[index]), bestResult);
            return bestResult;
        }

        int left = resolveWithTree(stones, index + 1, curResult + stones[index], bestResult);
        int right = resolveWithTree(stones, index + 1, curResult - stones[index], bestResult);
        return Math.min(left, right);
    }

    @Test
    public void test1() {
        int[] stones = {2, 7, 4, 1, 8, 1};
        Assert.assertEquals(1, lastStoneWeightII(stones));
    }

    @Test
    public void test2() {
        int[] stones = {31, 26, 33, 21, 40};
        Assert.assertEquals(5, lastStoneWeightII(stones));
    }

    @Test
    public void test3() {
        int[] stones = {1, 2};
        Assert.assertEquals(1, lastStoneWeightII(stones));
    }
}