/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.greedy;

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
 * @version : BagOfTokens.java, v 0.1 2022年11月19日 09:57 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BagOfTokens {

    @LetCodeCommit(title = "948. Bag of Tokens")
    public int bagOfTokensScore(int[] tokens, int power) {

        int maxScore = 0;
        int curScore = 0;
        Arrays.sort(tokens);
        int n = tokens.length;
        int l = 0, h = n - 1;
        while (l <= h && (curScore > 0 || power >= tokens[l])) {
            while (l <= h && power >= tokens[l]) {
                curScore++;
                power -= tokens[l];
                l++;
            }
            maxScore = Math.max(maxScore, curScore);
            if (l <= h && curScore > 0) {
                //这里要用if，不能是while，因为得分优先
                power += tokens[h];
                h--;
                curScore--;
            }
        }
        return maxScore;
    }

    @Parameter
    public int[] tokens;
    @Parameter(1)
    public int   power;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[33,4,28,24,96]"), 35, 3},
                {ArrayUtils.buildArray("[100]"), 50, 0},
                {ArrayUtils.buildArray("[100,200]"), 150, 1},
                {ArrayUtils.buildArray("[100,200,300,400]"), 200, 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, bagOfTokensScore(tokens, power));
    }

}