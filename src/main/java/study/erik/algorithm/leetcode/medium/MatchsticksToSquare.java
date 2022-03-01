/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : MatchsticksToSquare.java, v 0.1 2022年02月28日 9:59 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MatchsticksToSquare {

    @LetCodeCommit(title = "473. Matchsticks to Square",
            diff = "m",
            selfRemark = "没办法就用遍历，这次是回溯",
    related = {""})
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int matchstick : matchsticks) {
            sum += matchstick;
        }
        if (sum % 4 != 0) {
            return false;
        }
        //如果不加倒排，就会超时。这说明什么，说明这个问题本身是有偏向性的
        Arrays.sort(matchsticks);
        int l = 0, h = matchsticks.length - 1;
        while (l < h) {
            int temp = matchsticks[l];
            matchsticks[l++] = matchsticks[h];
            matchsticks[h--] = temp;
        }

        return backtrack(matchsticks, new int[4], sum / 4, 0);
    }

    /**
     * 这绝对是回溯而不是dfs.
     *
     * @param matchsticks
     * @param sums
     * @param target
     * @param curIndex
     * @return
     */
    public boolean backtrack(int[] matchsticks, int[] sums, int target, int curIndex) {
        if (curIndex == matchsticks.length) {
            // 这里无需检查sums[0]==sums[1]==sums[2]==target，因为递归调用dfs时已经ok了
            return true;
        }
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] + matchsticks[curIndex] <= target) {
                sums[i] += matchsticks[curIndex];
                if (backtrack(matchsticks, sums, target, curIndex + 1)) {
                    return true;
                }
                sums[i] -= matchsticks[curIndex];
            }
        }
        return false;
    }

    @Parameter
    public int[]   matchsticks;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,1,1,1,1,1,1,1,1,1,1,1,1,1,102]"), false},
                {ArrayUtils.buildArray("[5,5,5,5,4,4,4,4,3,3,3,3]"), true},
                {ArrayUtils.buildArray("[2,2,2,2]"), true},
                {ArrayUtils.buildArray("[1,1,2,2,2]"), true},
                {ArrayUtils.buildArray("[3,3,3,3,4]"), false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, makesquare(matchsticks));
    }
}