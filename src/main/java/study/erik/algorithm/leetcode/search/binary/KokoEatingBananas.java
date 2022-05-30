/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

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
 * @version : KokoEatingBananas.java, v 0.1 2022年05月30日 08:01 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class KokoEatingBananas {

    @LetCodeCommit(title = "875. Koko Eating Bananas",
            diff = "m",
            selfRemark = "一开始思路错了，按照一个操作题去想的。"
                    + "而brute force解法，就是搜索解空间。"
                    + "而binary search解法是，更高效的搜索解空间.")
    public int minEatingSpeed(int[] piles, int h) {

        int l = 1;
        int r = Arrays.stream(piles).max().orElse(1);
        // r是个可行解，而l是个不可行解。最后返回的也是l，因为跳出循环的时候，l已经==r了.返回r也是对的.
        while (l < r) {
            int m = l + (r - l) / 2;
            int spendHours = 0;
            for (int pile : piles) {
                spendHours += (pile / m + (pile % m != 0 ? 1 : 0));
            }
            if (spendHours <= h) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return r; //也是对的
        //return l;
    }

    @Parameter
    public int[] piles;
    @Parameter(1)
    public int   h;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[3,6,7,11]"), 8, 4},
                {ArrayUtils.buildArray("[30,11,23,4,20]"), 5, 30},
                {ArrayUtils.buildArray("[30,11,23,4,20]"), 6, 23},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minEatingSpeed(piles, h));
    }
}