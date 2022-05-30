/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

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
 * @version : FairCandySwap.java, v 0.1 2022年05月30日 18:13 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FairCandySwap {

    @LetCodeCommit(title = "888. Fair Candy Swap")
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sum1 = Arrays.stream(aliceSizes).sum();
        int sum2 = Arrays.stream(bobSizes).sum();
        int ave = (sum1 + sum2) / 2;
        for (int i = 0; i < aliceSizes.length; i++) {
            int aliceSize = aliceSizes[i];
            int change = aliceSize + (ave - sum1);
            for (int j = 0; j < bobSizes.length; j++) {
                if (change == bobSizes[j]) {
                    return new int[] {aliceSize, change};
                }
            }
        }
        return null;
    }

    @Parameter
    public int[] aliceSizes;
    @Parameter(1)
    public int[] bobSizes;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,1]"), ArrayUtils.buildArray("[2,2]")},
                {ArrayUtils.buildArray("[1,2]"), ArrayUtils.buildArray("[2,3]")},
                {ArrayUtils.buildArray("[2]"), ArrayUtils.buildArray("[1,3]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(fairCandySwap(aliceSizes, bobSizes)));
    }
}